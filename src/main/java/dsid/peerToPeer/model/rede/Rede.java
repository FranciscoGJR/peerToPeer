package dsid.peerToPeer.model.rede;

import dsid.peerToPeer.model.EstatisticasDownload;
import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.Status;
import lombok.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import static dsid.peerToPeer.utils.Constantes.*;

@Data
public class Rede {
	
	private No noPrincipal;

    private ServerSocket serverSocket;

    private String enderecoIP;

    private Integer porta;
    
    private Integer clock;

    private Integer chunk = CHUNK_INICIAL;

    private List<No> vizinhos;

    private CaixaDeMensagens caixaDeMensagens;

    private Status status = Status.ONLINE;
    
	private String diretorioCompartilhado;

    private volatile boolean running = true;

    private EstatisticasDownload estatisticasDownload = new EstatisticasDownload();


    public Rede(String enderecoIP, Integer porta, List<No> vizinhos, String diretorioCompartilhado) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.clock = ZERO;
        this.vizinhos = vizinhos;
        this.caixaDeMensagens = new CaixaDeMensagens();
        this.status = Status.ONLINE;
        this.diretorioCompartilhado = diretorioCompartilhado;
        this.iniciarConexao();
        this.threadEscuta();
    }
    
    
    // Construtor um vizinho
    public Rede(String enderecoIP, Integer porta, Integer clock) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.caixaDeMensagens = null;
        this.clock = clock;
        this.vizinhos = null;
        this.status = Status.OFFLINE;
    }
    

	// Consttrutor um vizinho enviado por LIST_PEER
	public Rede(String enderecoIP, String porta, Status status, Integer clock) {
        this.enderecoIP = enderecoIP;
        this.porta = Integer.decode(porta);
        this.caixaDeMensagens = null;
        this.vizinhos = null;
        this.clock = clock;
        this.status = status;
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
                    new ThreadComunicacao(novoSocket, this.noPrincipal, this.vizinhos, this.caixaDeMensagens, this.diretorioCompartilhado).run();
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
    
    public void incrementarClock() {
    	this.clock++;
    	System.out.println(ATUALIZANDO_RELOGIO_PARA + getClock());
    }


    public EstatisticasDownload getEstatisticasDownload() {
        return this.estatisticasDownload;
    }
}