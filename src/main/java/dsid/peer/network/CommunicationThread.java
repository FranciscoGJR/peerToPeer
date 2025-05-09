package dsid.peer.network;

import dsid.peer.model.Peer;
import dsid.peer.util.MessageUtil;

import java.io.*;
import java.net.Socket;

public class CommunicationThread implements Runnable {
private final Socket socket;
private final Peer noLocal;

public CommunicationThread(Socket socket, Peer noLocal) {
    this.socket = socket;
    this.noLocal = noLocal;
}

@Override
public void run() {
    try (BufferedReader leitor = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
        String msgStr = leitor.readLine();
        if (msgStr != null) {
            Message msg = MessageUtil.parse(msgStr, noLocal);
            tratarMessage(msg);
        }
    } catch (IOException e) {
        System.err.println("Erro na thread de comunicação: " + e.getMessage());
    }
}

private void tratarMessage(Message msg) {
    // TODO: implementar tratamento das mensagens (HELLO, GET_PEERS, etc.)
    System.out.println("Recebido: " + msg.toProtocolString());
}
}