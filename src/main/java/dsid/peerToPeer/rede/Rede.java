package dsid.peerToPeer.rede;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import dsid.peerToPeer.No;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
public class Rede {
    
    private ServerSocket serverSocket;

    private String enderecoIP;

    private int porta;

    private List<No> vizinhos;

    private List<Mensagem> caixaDeMensagens;


    public Rede(String enderecoIP, int porta) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.vizinhos = new ArrayList<>();
    }

    
    public void listarVizinhos() {
    	System.out.printf("Ha %d vizinhos na tabela\n", this.vizinhos.size());
        
        int iterador = 0;
        for (No vizinho : this.vizinhos) {
            System.out.printf("\t[%d] %s %s\n", iterador, this.getEnderecoIP(), this.getPorta());
            iterador++;
        }
    }


    public void iniciarConexao() {
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Servidor iniciado na porta " + porta);
        } catch (IOException e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void threadEscuta() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket novoSocket = serverSocket.accept();
                    System.out.println("Nova conexão recebida de: " + novoSocket.getInetAddress().getHostAddress());
                    new ThreadComunicacao(novoSocket).run();
                } catch (IOException e) {
                    System.err.println("Erro ao aceitar conexão: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
