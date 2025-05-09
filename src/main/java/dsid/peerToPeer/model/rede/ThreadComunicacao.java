<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/model/rede/ThreadComunicacao.java
package dsid.peerToPeer.model.rede;
import static dsid.peerToPeer.utils.Constantes.ERRO_AO_COMUNICAR_COM_VIZINHO;
import static dsid.peerToPeer.utils.MensagemUtil.serializarMensagem;
import static dsid.peerToPeer.utils.Status.ONLINE;
import static dsid.peerToPeer.utils.TipoMensagemEnum.GET_PEERS;
import static dsid.peerToPeer.utils.TipoMensagemEnum.PEER_LIST;
=======
package dsid.peer.model.rede;
import static dsid.peer.utils.Constantes.ERRO_AO_COMUNICAR_COM_VIZINHO;
import static dsid.peer.utils.MensagemUtil.serializarMensagem;
import static dsid.peer.utils.Status.ONLINE;
import static dsid.peer.utils.TipoMensagemEnum.GET_PEERS;
import static dsid.peer.utils.TipoMensagemEnum.HELLO;
import static dsid.peer.utils.TipoMensagemEnum.PEER_LIST;
>>>>>>> Stashed changes:src/main/java/dsid/peer/model/rede/ThreadComunicacao.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/model/rede/ThreadComunicacao.java
import dsid.peerToPeer.controller.InterfaceUsuario;
import dsid.peerToPeer.model.No;
import dsid.peerToPeer.service.MensagemService;
import dsid.peerToPeer.service.RedeService;
import dsid.peerToPeer.utils.Status;
import dsid.peerToPeer.utils.ThreadComunicacaoUtil;
import dsid.peerToPeer.utils.TipoMensagemEnum;
=======
import dsid.peer.model.Peer;
import dsid.peer.service.MensagemService;
import dsid.peer.service.RedeService;
import dsid.peer.utils.Status;
import dsid.peer.utils.ThreadComunicacaoUtil;
import dsid.peer.utils.TipoMensagemEnum;
>>>>>>> Stashed changes:src/main/java/dsid/peer/model/rede/ThreadComunicacao.java
import lombok.Data;

@Data
public class ThreadComunicacao implements Runnable{
	
	No no;

	RedeService redeService = new RedeService();
	
	MensagemService mensagemService = new MensagemService();
	
	private Socket socket;

	private List<No> vizinhos;

    private CaixaDeMensagens caixaDeMensagens;
	
    public ThreadComunicacao(Socket socket, No no, List<No> vizinhos, CaixaDeMensagens caixaDeMensagens) {
        this.socket = socket;
        this.no = no;
        this.vizinhos = vizinhos;
        this.caixaDeMensagens = caixaDeMensagens;
    }
    

    @Override
    public void run() {
        try {
            Mensagem mensagemRecebida = receberMensagem();
            System.out.println(mensagemRecebida.toString());
            caixaDeMensagens.adicionarMensagemRecebida(mensagemRecebida);
            No noOrigem = mensagemRecebida.getOrigem();
            noOrigem.getRede().setStatus(ONLINE);;
            
            if (!vizinhoConhecido(noOrigem.getRede())) {
            	redeService.adicinarVizinho(noOrigem, vizinhos);
            }
            
            if (mensagemRecebida.getTipo().equals(GET_PEERS)) {
            	List<String> argumentos = this.mensagemService.preencherArgumentosParaMensagemListPeer(this.vizinhos, noOrigem);
            	Mensagem mensagemDeResposta = new Mensagem(this.no, noOrigem, TipoMensagemEnum.PEER_LIST, argumentos);
            	this.redeService.enviarMensagem(mensagemDeResposta, caixaDeMensagens);
            }
            
            if (mensagemRecebida.getTipo().equals(PEER_LIST)) {
            	for (int iterador = 1; iterador < mensagemRecebida.getArgumentos().size(); iterador++) {
            		String[] tokens = mensagemRecebida.getArgumentos().get(iterador).split(":");
            		String enderecoIP = tokens[0];
            		String porta = tokens[1];
            		Status status = (tokens[2].equals("ONLINE")) ? Status.ONLINE : Status.OFFLINE;

            		this.redeService.adicinarVizinho(new No(enderecoIP, porta, status), this.vizinhos);
            	}
            }

    		InterfaceUsuario.exibirMenu();
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
