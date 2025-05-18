package dsid.peerToPeer.model.rede;

import static dsid.peerToPeer.utils.Constantes.ERRO_AO_COMUNICAR_COM_VIZINHO;
import static dsid.peerToPeer.utils.MensagemUtil.serializarMensagem;
import static dsid.peerToPeer.utils.Status.ONLINE;
import static dsid.peerToPeer.utils.TipoMensagemEnum.GET_PEERS;
import static dsid.peerToPeer.utils.TipoMensagemEnum.HELLO;
import static dsid.peerToPeer.utils.TipoMensagemEnum.PEER_LIST;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.service.MensagemService;
import dsid.peerToPeer.service.RedeService;
import dsid.peerToPeer.utils.Status;
import dsid.peerToPeer.utils.ThreadComunicacaoUtil;
import dsid.peerToPeer.utils.TipoMensagemEnum;
import lombok.Data;

@Data
public class ThreadComunicacao implements Runnable{
	
	No no;

	RedeService redeService = new RedeService();
	
	MensagemService mensagemService = new MensagemService();
	
	private Socket socket;

	private List<No> vizinhos;

    private CaixaDeMensagens caixaDeMensagens;
    
    private String diretorioCompartilhado;

    public ThreadComunicacao(Socket socket, No no, List<No> vizinhos, CaixaDeMensagens caixaDeMensagens, String diretorioCompartilhado) {
        this.socket = socket;
        this.no = no;
        this.vizinhos = vizinhos;
        this.caixaDeMensagens = caixaDeMensagens;
        this.diretorioCompartilhado = diretorioCompartilhado;
    }
    

    @Override
    public void run() {
        try {
            Mensagem mensagemRecebida = receberMensagem();
            caixaDeMensagens.adicionarMensagemRecebida(mensagemRecebida);
            No noOrigem = mensagemRecebida.getOrigem();

            redeService.atualizarParaMaiorClock(this.no.getRede(), mensagemRecebida);

            if (!vizinhoConhecido(noOrigem.getRede())) {
            	noOrigem.getRede().setStatus(ONLINE);
            	redeService.adicionarVizinho(noOrigem, this.vizinhos);
            }

            if (mensagemRecebida.getTipo().equals(HELLO)) {
            	noOrigem.getRede().setStatus(ONLINE);
            }
            
            if (mensagemRecebida.getTipo().equals(GET_PEERS)) {
            	List<String> argumentos = this.mensagemService.preencherArgumentosParaMensagemListPeer(this.vizinhos, noOrigem);
            	Mensagem mensagemDeResposta = new Mensagem(this.no, noOrigem, TipoMensagemEnum.PEER_LIST, argumentos);
            	this.no.getRede().incrementarClock();
            	this.redeService.enviarMensagem(mensagemDeResposta, caixaDeMensagens);
            }
            
            if (mensagemRecebida.getTipo().equals(PEER_LIST)) {
            	noOrigem.getRede().setStatus(ONLINE);
            	redeService.adicionarVizinho(noOrigem, this.vizinhos);
            	for (int iterador = 1; iterador < mensagemRecebida.getArgumentos().size(); iterador++) {
            		String[] tokens = mensagemRecebida.getArgumentos().get(iterador).split(":");
            		String enderecoIP = tokens[0];
            		String porta = tokens[1];
            		Status status = (tokens[2].equals("ONLINE")) ? Status.ONLINE : Status.OFFLINE;
            		Integer clock = mensagemRecebida.getClock();

            		this.redeService.adicionarVizinho(new No(enderecoIP, porta, status, clock), this.vizinhos);
            	}
            }
            
            if (mensagemRecebida.getTipo().equals(TipoMensagemEnum.LS)) {
                File dir = new File(this.diretorioCompartilhado);
                File[] arquivos = dir.listFiles();
                List<String> argumentos = new ArrayList();
                int count = 0;
                for (File f : arquivos) {
                    if (f.isFile()) count++;
                }
                argumentos.add(String.valueOf(count));
                for (File f : arquivos) {
                    if (f.isFile())
                        argumentos.add(f.getName() + ":" + f.length());
                }
                Mensagem resposta = new Mensagem(this.no, noOrigem, TipoMensagemEnum.LS_LIST, argumentos);
                this.no.getRede().incrementarClock();
                enviarResposta(resposta.toString());
                return;
            }

            if (mensagemRecebida.getTipo().equals(TipoMensagemEnum.DL)) {
                String nomeArquivo = mensagemRecebida.getArgumentos().get(0);
                File file = new File(this.diretorioCompartilhado, nomeArquivo);
                byte[] conteudo = Files.readAllBytes(file.toPath());
                String base64 = Base64.getEncoder().encodeToString(conteudo);
                List<String> argumentos = Arrays.asList(nomeArquivo, "0", "0", base64);
                Mensagem resposta = new Mensagem(this.no, noOrigem, TipoMensagemEnum.FILE, argumentos);
                this.no.getRede().incrementarClock();
                enviarResposta(resposta.toString());
                return;
            }

            fecharConexao();

        } catch (IOException e) {
            System.err.println(ERRO_AO_COMUNICAR_COM_VIZINHO + e.getMessage());
            e.printStackTrace();
        }
    }

    
    private boolean vizinhoConhecido(Rede rede) {
    	String enderecoBuscado = rede.getEnderecoIP();
    	Integer portaBuscada = rede.getPorta();
    	for (No vizinho : this.vizinhos) {
    		if (enderecoBuscado.equals(vizinho.getRede().getEnderecoIP())  && portaBuscada == vizinho.getRede().getPorta()) {
    			return true;
    		}
    	}
    	
    	return false;
    }


    public Mensagem receberMensagem() throws IOException {
        InputStream inputStream = this.socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String mensagemEmTexto = reader.readLine();
        ThreadComunicacaoUtil.exibirMensagem(mensagemEmTexto);
        return serializarMensagem(mensagemEmTexto);
    }

    
    public void fecharConexao() {
        try {
            if (this.socket != null && !this.socket.isClosed()) {
                this.socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void enviarResposta(String mensagem) throws IOException {
        OutputStream outputStream = this.socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(mensagem);
        writer.flush();
    }

}
