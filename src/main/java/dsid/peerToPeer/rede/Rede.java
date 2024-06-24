package dsid.peerToPeer.rede;

import static dsid.peerToPeer.utils.Constantes.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dsid.peerToPeer.No;
import lombok.Data;


@Data
public class Rede {
    
    private ServerSocket serverSocket;

    private String enderecoIP;

    private Integer porta;

    private List<No> vizinhos;

    private CaixaMensagens caixaDeMensagens;
    
    private volatile boolean running = true;


    public Rede(String enderecoIP, Integer porta, List<No> vizinhos) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.vizinhos = vizinhos;
        this.caixaDeMensagens = new CaixaMensagens();
        this.iniciarConexao(porta);
        this.threadEscuta();
    }
    
    
    // Construtor para classe Rede de um vizinho
    public Rede(String enderecoIP, Integer porta) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.caixaDeMensagens = null;
        this.vizinhos = null;
	}

    
    public void iniciarConexao(Integer porta) {
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println(SERVIDOR_INICIADO + porta);
        } catch (IOException e) {
            System.err.println(ERRO_AO_INICIAR_SERVIDOR + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void listarVizinhos() {
    	System.out.println(QUANTIDADE_VIZINHOS + this.vizinhos.size());
        
        int iterador = 0;
        for (No vizinho : this.vizinhos) {
            System.out.printf("\t[%d] %s %s\n", iterador, vizinho.getRede().getEnderecoIP(), vizinho.getRede().getPorta());
            iterador++;
        }
    }
    
    
	public void enviarMensagem(Mensagem mensagem) {
        try (Socket socket = new Socket(mensagem.getDestino().getRede().getEnderecoIP(), 
        								mensagem.getDestino().getRede().getPorta())) {
        	
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(mensagem.getTexto());
            this.caixaDeMensagens.novaEnviada(mensagem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void threadEscuta() {
        new Thread(() -> {
            while (running) {
                try {
                    Socket novoSocket = serverSocket.accept();
                    new ThreadComunicacao(novoSocket).run();
                    adicinarVizinho(novoSocket);
                } catch (IOException e) {
                    if (!running) {
                        System.out.println(SOCKET_ENCERRADO);
                    } else {
                        System.err.println(ERRO_ACEITAR_CONECAO + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    
    private void adicinarVizinho(Socket socket) {
        String endereco = socket.getInetAddress().getHostAddress();
        int porta = socket.getPort();
        No novoNo = new No(endereco, porta);
        if (this.vizinhos.contains(novoNo)) {
        	System.out.println(VIZINHO_JA_ADICIONADA + endereco + ":" + porta);
        	return;
        }
        vizinhos.add(novoNo);
        System.out.println(VIZINHO_ADICIONADO + endereco + ":" + porta);
	}


	public void pararEscuta() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
