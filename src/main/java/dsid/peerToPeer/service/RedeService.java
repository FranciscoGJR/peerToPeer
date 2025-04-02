package dsid.peerToPeer.service;

import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.VIZINHO_ADICIONADO;
import static dsid.peerToPeer.utils.Constantes.VIZINHO_JA_ADICIONADA;
import static dsid.peerToPeer.utils.ThreadComunicacaoUtil.esperaEmSegundos;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.CaixaMensagens;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.model.rede.Rede;

public class RedeService {

    private CaixaMensagensService caixaMensagensService;

    private MensagemService mensagemService;


    public RedeService(Rede rede) {
        this.caixaMensagensService = new CaixaMensagensService();
        this.mensagemService = new MensagemService();
    }
    

    public RedeService() {
        this.mensagemService = new MensagemService();
        this.caixaMensagensService = new CaixaMensagensService();
    }


    public void listarVizinhos(Rede rede) {
        int iterador = 1;
        for (No vizinho : rede.getVizinhos()) {
            System.out.printf("\t[%d] %s:%s %s\n", iterador, vizinho.getRede().getEnderecoIP(), vizinho.getRede().getPorta(), vizinho.getRede().getStatus());
            iterador++;
        }
    }
    

    public void enviarMensagem(Mensagem mensagem, CaixaMensagens caixaMensagens) {
        mensagem.setNumeroDeSequencia(caixaMensagens.getEnviadas().size());
        this.caixaMensagensService.novaMensagemEnviada(mensagem, caixaMensagens);
        System.out.println(mensagemService.encaminhandoMensagem(mensagem));
        try (Socket socket = new Socket(mensagem.getDestino().getRede().getEnderecoIP(),
                                        mensagem.getDestino().getRede().getPorta())) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(mensagem.toString());

            System.out.println(mensagemService.encaminhadoComSucesso(mensagem));
            esperaEmSegundos(DOIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void pararEscuta(Rede rede) {
        rede.setRunning(false);
        try {
            rede.getServerSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void adicinarVizinho(Socket socket, Rede rede) {
        String endereco = socket.getInetAddress().getHostAddress();
        int porta = socket.getLocalPort();
        No novoNo = new No(endereco, porta);
        if (rede.getVizinhos().contains(novoNo)) {
            System.out.println(VIZINHO_JA_ADICIONADA + endereco + ":" + porta);
            return;
        }
        rede.getVizinhos().add(novoNo);
        System.out.println(VIZINHO_ADICIONADO + endereco + ":" + porta);
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
