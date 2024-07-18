package dsid.peerToPeer.model.rede;

import static dsid.peerToPeer.utils.Constantes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import dsid.peerToPeer.utils.ThreadComunicacaoUtil;
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
            
            ThreadComunicacaoUtil.novaMensagem(mensagemRecebida);
            
            fecharConexao(socket);
        } catch (IOException e) {
            System.err.println(ERRO_AO_COMUNICAR_COM_VIZINHO + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void fecharConexao(Socket socket) {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
