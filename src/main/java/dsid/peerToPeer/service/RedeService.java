package dsid.peerToPeer.service;

import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.ERRO_ACEITAR_CONECAO;
import static dsid.peerToPeer.utils.Constantes.ERRO_AO_INICIAR_SERVIDOR;
import static dsid.peerToPeer.utils.Constantes.SOCKET_ENCERRADO;
import static dsid.peerToPeer.utils.Constantes.VIZINHO_ADICIONADO;
import static dsid.peerToPeer.utils.Constantes.VIZINHO_JA_ADICIONADA;
import static dsid.peerToPeer.utils.ThreadComunicacaoUtil.esperaEmSegundos;

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
import dsid.peerToPeer.model.rede.ThreadComunicacao;

public class RedeService {

    private CaixaMensagensService caixaMensagensService;

    private MensagemService mensagemService;


    public RedeService(Rede rede) {
        this.caixaMensagensService = new CaixaMensagensService();
        this.mensagemService = new MensagemService();
        threadEscuta(rede);
    }


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


    public void enviarMensagem(Mensagem mensagemEnviada, CaixaDeMensagens caixaMensagens) {
        mensagemEnviada.setNumeroDeSequencia(caixaMensagens.quantidadeRecebidas());
        this.caixaMensagensService.novaMensagemEnviada(mensagemEnviada, caixaMensagens);
        System.out.println(mensagemService.encaminhandoMensagem(mensagemEnviada));
        try (Socket socket = new Socket(mensagemEnviada.getDestino().getRede().getEnderecoIP(),
                                        mensagemEnviada.getDestino().getRede().getPorta())) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(mensagemEnviada.toString());

            System.out.println(mensagemService.encaminhadoComSucesso(mensagemEnviada));
            esperaEmSegundos(DOIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void threadEscuta(Rede rede) {
        new Thread(() -> {
            while (rede.isRunning()) {
                try {
                    Socket novoSocket = rede.getServerSocket().accept();
                    new ThreadComunicacao(novoSocket, rede.getVizinhos(), rede.getCaixaDeMensagens()).run();
                } catch (IOException e) {
                    if (!rede.isRunning()) {
                        System.out.println(SOCKET_ENCERRADO);
                    } else {
                        System.err.println(ERRO_ACEITAR_CONECAO + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    

    public void adicinarVizinho(No novoNo, List<No> vizinhos) {
        String endereco = novoNo.getRede().getEnderecoIP();
        int porta = novoNo.getRede().getPorta();

        if (vizinhos.contains(novoNo)) {
            System.out.println(VIZINHO_JA_ADICIONADA + endereco + ":" + porta);
            return;
        }
        vizinhos.add(novoNo);
        System.out.println(VIZINHO_ADICIONADO + endereco + ":" + porta);
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
