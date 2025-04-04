package dsid.peerToPeer.model.rede;
import static dsid.peerToPeer.utils.Constantes.ERRO_ACEITAR_CONECAO;
import static dsid.peerToPeer.utils.Constantes.ERRO_AO_INICIAR_SERVIDOR;
import static dsid.peerToPeer.utils.Constantes.SOCKET_ENCERRADO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.Status;
import lombok.Data;

@Data
public class Rede {

    private ServerSocket serverSocket;

    private String enderecoIP;

    private Integer porta;

    private List<No> vizinhos;

    private CaixaDeMensagens caixaDeMensagens;

    private Status status = Status.ONLINE;

    private volatile boolean running = true;


    public Rede(String enderecoIP, Integer porta, List<No> vizinhos) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.vizinhos = vizinhos;
        this.caixaDeMensagens = new CaixaDeMensagens();
        this.status = Status.ONLINE;
        this.iniciarConexao();
        this.threadEscuta();
    }


    // Construtor para classe Rede de um vizinho
    public Rede(String enderecoIP, Integer porta) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.caixaDeMensagens = null;
        this.vizinhos = null;
        this.status = Status.OFFLINE;
    }
    

    public void iniciarConexao() {
        try {
            this.serverSocket = new ServerSocket(this.getPorta());
        } catch (IOException e) {
            System.err.println(ERRO_AO_INICIAR_SERVIDOR + e.getMessage());
            e.printStackTrace();
        }
    }

    
    private void threadEscuta() {
        new Thread(() -> {
            while (running) {
                try {
                    Socket novoSocket = serverSocket.accept();
                    new ThreadComunicacao(novoSocket, vizinhos, caixaDeMensagens).run();
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

}
