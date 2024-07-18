package dsid.peerToPeer.service;

import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.ERRO_ACEITAR_CONECAO;
import static dsid.peerToPeer.utils.Constantes.ERRO_AO_INICIAR_SERVIDOR;
import static dsid.peerToPeer.utils.Constantes.QUANTIDADE_VIZINHOS;
import static dsid.peerToPeer.utils.Constantes.SERVIDOR_INICIADO;
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

import dsid.peerToPeer.No;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.model.rede.Rede;
import dsid.peerToPeer.model.rede.ThreadComunicacao;

public class RedeService {

    private Rede rede;
    private CaixaMensagensService caixaMensagensService;
    private MensagemService mensagemService;

    public RedeService(Rede rede) {
        this.rede = rede;
        this.caixaMensagensService = new CaixaMensagensService(rede.getCaixaDeMensagens());
        this.mensagemService = new MensagemService();
        iniciarConexao(rede.getPorta());
        threadEscuta();
    }

    public void iniciarConexao(Integer porta) {
        try {
            rede.setServerSocket(new ServerSocket(porta));
            System.out.println(SERVIDOR_INICIADO + porta);
        } catch (IOException e) {
            System.err.println(ERRO_AO_INICIAR_SERVIDOR + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listarVizinhos() {
        System.out.println(QUANTIDADE_VIZINHOS + rede.getVizinhos().size());

        int iterador = 0;
        for (No vizinho : rede.getVizinhos()) {
            System.out.printf("\t[%d] %s %s\n", iterador, vizinho.getRedeService().getEnderecoIP(), vizinho.getRedeService().getPorta());
            iterador++;
        }
    }
    

    public void enviarMensagem(Mensagem mensagem) {
        mensagem.setNumeroDeSequencia(caixaMensagensService.quantidadeRecebidas());
        caixaMensagensService.novaEnviada(mensagem);
        System.out.println(mensagemService.encaminhandoMensagem(mensagem));
        try (Socket socket = new Socket(mensagem.getDestino().getRedeService().getEnderecoIP(),
                                        mensagem.getDestino().getRedeService().getPorta())) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(mensagem.toString());

            System.out.println(mensagemService.encaminhadoComSucesso(mensagem));
            esperaEmSegundos(DOIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void threadEscuta() {
        new Thread(() -> {
            while (rede.isRunning()) {
                try {
                    Socket novoSocket = rede.getServerSocket().accept();
                    new ThreadComunicacao(novoSocket).run();
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
    
    
    public String getEnderecoIP() {
    	return rede.getEnderecoIP();
    }
    
    
    public Integer getPorta() {
    	return rede.getPorta();
    }
    
    
    public List<No> getVizinhos() {
    	return rede.getVizinhos();
    }
    

    private void adicinarVizinho(Socket socket) {
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

    public void pararEscuta() {
        rede.setRunning(false);
        try {
            rede.getServerSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
