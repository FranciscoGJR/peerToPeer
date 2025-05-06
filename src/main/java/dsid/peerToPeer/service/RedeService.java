package dsid.peerToPeer.service;

import static dsid.peerToPeer.utils.Constantes.ERRO_AO_INICIAR_SERVIDOR;
import static dsid.peerToPeer.utils.MensagemUtil.peerAdicionado;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.CaixaDeMensagens;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.model.rede.Rede;

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
