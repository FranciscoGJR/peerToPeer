package dsid.peerToPeer.model;

import java.util.List;

import dsid.peerToPeer.model.rede.Rede;
import dsid.peerToPeer.utils.Status;
import lombok.Data;

@Data
public class Peer {
	
	private Rede rede;

	public Peer(String endereco, Integer porta, List<Peer> vizinhos) {
		this.rede = new Rede(endereco, porta, vizinhos);
	}

    // Construtor para classe No de um vizinho
	public Peer(String endereco, int porta, Integer clock) {
		this.rede = new Rede(endereco, porta, clock);
	}

	// Consttrutor para classe No de um vizinho enviado por LIST_PEER
	public Peer(String enderecoIP, String porta, Status status, Integer clock) {
		this.rede = new Rede(enderecoIP, porta, status, clock);
	}

}
