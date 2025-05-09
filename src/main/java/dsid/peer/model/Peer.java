package dsid.peer.model;
import java.util.List;

public class Peer {
	private final Network network;

	public Peer(String enderecoPorta, List<Peer> vizinhos) {
    	String[] partes = enderecoPorta.split(":");
    	this.network = new Network(partes[0], Integer.parseInt(partes[1]), vizinhos);
	}

	public Peer(String enderecoPorta) {
    	String[] partes = enderecoPorta.split(":");
    	this.network = new Network(partes[0], Integer.parseInt(partes[1]), null);
	}

	public Network getNetwork() {
    	return network;
	}

	@Override
	public String toString() {
    	return network.getEndereco() + ":" + network.getPorta() + " " + (network.isStatusOnline() ? "ONLINE" : "OFFLINE");
	}
}