package dsid.peer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

import dsid.peer.model.Peer;
import dsid.peer.network.CommunicationThread;
import dsid.peer.network.MessageType;
import dsid.peer.network.Message;

public class ServicePeer {
	private final Peer PeerLocal;
	private final File pastaCompartilhada;
	private ServerSocket servidor;
	private volatile boolean rodando = true;

	public ServicePeer(Peer PeerLocal, File pastaCompartilhada) {
		this.PeerLocal = PeerLocal;
		this.pastaCompartilhada = pastaCompartilhada;
		iniciarServidor();
	}

	public List<Peer> listarVizinhos() {
		return Collections.unmodifiableList(PeerLocal.getNetwork().getVizinhos());
	}

	public void enviarHello(Peer destiPeer) {
		Message hello = new Message(PeerLocal, destiPeer, MessageType.HELLO, PeerLocal.getNetwork().incrementarClock());
		enviarMessage(hello);
		destiPeer.getNetwork().setStatusOnline(true);
		System.out.printf("Atualizando peer %s status ONLINE\n", destiPeer);
	}

	public void obterVizinhos() {
		for (Peer vizinho : PeerLocal.getNetwork().getVizinhos()) {
			Message getPeers = new Message(PeerLocal, vizinho, MessageType.GET_PEERS,
					PeerLocal.getNetwork().incrementarClock());
			enviarMessage(getPeers);
		}
	}

	public void listarArquivosLocais() {
		File[] arquivos = pastaCompartilhada.listFiles();
		if (arquivos == null)
			return;
		for (File arquivo : arquivos) {
			if (arquivo.isFile()) {
				System.out.println(arquivo.getName());
			}
		}
	}

	public void encerrar() {
		rodando = false;
		try {
			if (servidor != null)
				servidor.close();
		} catch (IOException igPeerred) {
		}
	}

	// Networking
	private void iniciarServidor() {
		new Thread(() -> {
			try {
				servidor = new ServerSocket(PeerLocal.getNetwork().getPorta());
				while (rodando) {
					Socket socket = servidor.accept();
					new Thread(new CommunicationThread(socket, PeerLocal)).start();
				}
			} catch (IOException e) {
				if (rodando)
					System.err.println("Erro Peer servidor: " + e.getMessage());
			}
		}).start();
	}

	private void enviarMessage(Message  message) {
		Peer destiPeer =  message.getDestino();
		try (Socket socket = new Socket(destiPeer.getNetwork().getEndereco(), destiPeer.getNetwork().getPorta());
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
			writer.println( message.toProtocolString());
		} catch (IOException e) {
			System.err.printf("Falha ao enviar  message para %s: %s\n", destiPeer, e.getMessage());
			destiPeer.getNetwork().setStatusOnline(false);
		}
	}
}