package dsid.peerToPeer.model.rede;

import java.net.ServerSocket;
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
    }

    // Construtor para classe Rede de um vizinho
    public Rede(String enderecoIP, Integer porta) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.caixaDeMensagens = null;
        this.vizinhos = null;
    }
}
