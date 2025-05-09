package dsid.peer.service;

<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/service/RedeService.java
import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.ERRO_AO_INICIAR_SERVIDOR;
import static dsid.peerToPeer.utils.MensagemUtil.peerAdicionado;
import static dsid.peerToPeer.utils.MensagemUtil.peerAtualizado;
import static dsid.peerToPeer.utils.ThreadComunicacaoUtil.esperaEmSegundos;
=======
import static dsid.peer.utils.Constantes.ATUALIZANDO_RELOGIO_PARA;
import static dsid.peer.utils.Constantes.ERRO_AO_INICIAR_SERVIDOR;
import static dsid.peer.utils.MensagemUtil.peerAtualizado;
>>>>>>> Stashed changes:src/main/java/dsid/peer/service/RedeService.java

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/service/RedeService.java
import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.CaixaDeMensagens;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.model.rede.Rede;
=======
import dsid.peer.model.Peer;
import dsid.peer.model.rede.CaixaDeMensagens;
import dsid.peer.model.rede.Mensagem;
import dsid.peer.model.rede.Rede;
import dsid.peer.utils.Status;
>>>>>>> Stashed changes:src/main/java/dsid/peer/service/RedeService.java

public class RedeService {

    private CaixaMensagensService caixaMensagensService;

    private MensagemService mensagemService;

    public RedeService() {
        this.mensagemService = new MensagemService();
        this.caixaMensagensService = new CaixaMensagensService();
    }


    public void iniciarConexao(No no) {
        try {
            no.getRede().setServerSocket(new ServerSocket(no.getRede().getPorta()));
        } catch (IOException e) {
            System.err.println(ERRO_AO_INICIAR_SERVIDOR + e.getMessage());
            e.printStackTrace();
        }
    }


    public void listarVizinhos(Rede rede) {
        int iterador = 1;
        for (No vizinho : rede.getVizinhos()) {
            System.out.printf("\t[%d] %s:%s %s\n", iterador, vizinho.getRede().getEnderecoIP(), vizinho.getRede().getPorta(), vizinho.getRede().getStatus());
            iterador++;
        }
    }


    public boolean enviarMensagem(Mensagem mensagemEnviada, CaixaDeMensagens caixaMensagens) {
        mensagemEnviada.setNumeroDeSequencia(caixaMensagens.quantidadeEnviadas());
        this.caixaMensagensService.adicionarNovaMensagemEnviada(mensagemEnviada, caixaMensagens);
        System.out.println(mensagemService.encaminhandoMensagem(mensagemEnviada));
        try (
        	Socket socket = new Socket(mensagemEnviada.getDestino().getRede().getEnderecoIP(),
                                        mensagemEnviada.getDestino().getRede().getPorta())) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(mensagemEnviada.toString());

            System.out.println(peerAtualizado(mensagemEnviada));
            esperaEmSegundos(DOIS);
            return true;
        } catch (IOException e) {
        	return false;
        }
    }


    public void adicinarVizinho(No novoNo, List<No> vizinhos) {
        if (vizinhos.contains(novoNo)) {
            return;
        }
        vizinhos.add(novoNo);
      	System.out.println(peerAdicionado(novoNo.getRede()));
    }


    public void pararEscuta(Rede rede) {
        rede.setRunning(false);
        try {
            rede.getServerSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public String getEnderecoIP(Rede rede) {
    	return rede.getEnderecoIP();
    }
    
    
    public Integer getPorta(Rede rede) {
    	return rede.getPorta();
    }
    
    
    public List<No> getVizinhos(Rede rede) {
    	return rede.getVizinhos();
    }
}
