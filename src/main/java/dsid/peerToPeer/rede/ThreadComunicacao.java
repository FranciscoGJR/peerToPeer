package dsid.peerToPeer.rede;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import dsid.peerToPeer.No;
import lombok.Data;

@Data
public class ThreadComunicacao implements Runnable{
	
	private Socket socket;

	
    public ThreadComunicacao(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        try {
            String mensagemRecebida = receberMensagem(socket);
            System.out.println("Mensagem recebida: " + mensagemRecebida);

            // Processar mensagem recebida (HELLO, busca, etc.)

            enviarResposta(socket, "Mensagem recebida com sucesso!");
            //fecharConexao(socket);
        } catch (IOException e) {
            System.err.println("Erro ao comunicar com vizinho: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
	
	public void enviarMensagem(No vizinho, String mensagem, int ttl) {
        // Implementar método para enviar mensagem para um vizinho
    }
	

    public String receberMensagem(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }
    

    public void enviarResposta(Socket socket, String mensagem) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);
        writer.println(mensagem);
        writer.flush();
    }

}
