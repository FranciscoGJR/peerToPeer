package dsid.peerToPeer.model.rede;
import static dsid.peerToPeer.utils.Constantes.ERRO_AO_COMUNICAR_COM_VIZINHO;
import static dsid.peerToPeer.utils.MensagemUtil.serializarMensagem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.Status;
import dsid.peerToPeer.utils.ThreadComunicacaoUtil;
import lombok.Data;
import static dsid.peerToPeer.service.RedeService.adicinarVizinho;

@Data
public class ThreadComunicacao implements Runnable{
	
	private Socket socket;

	private List<No> vizinhos;

    private CaixaDeMensagens caixaDeMensagens;
	
    public ThreadComunicacao(Socket socket, List<No> vizinhos, CaixaDeMensagens caixaDeMensagens) {
        this.socket = socket;
        this.vizinhos = vizinhos;
        this.caixaDeMensagens = caixaDeMensagens;
    }
    

    @Override
    public void run() {
        try {
            Mensagem mensagemRecebida = receberMensagem();
            caixaDeMensagens.adicionarMensagemRecebida(mensagemRecebida);
            No noOrigem = mensagemRecebida.getOrigem();
            noOrigem.getRede().setStatus(Status.ONLINE);;
            
            if (!vizinhoConhecido(noOrigem.getRede())) {
            	adicinarVizinho(noOrigem, vizinhos);
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
