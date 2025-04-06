package dsid.peerToPeer.model;

import java.util.List;

import dsid.peerToPeer.model.rede.Rede;
import dsid.peerToPeer.utils.Status;
import lombok.Data;

@Data
public class No {
	
	private Rede rede;

	public No(String endereco, Integer porta, List<No> vizinhos) {
		this.rede = new Rede(endereco, porta, vizinhos);
	}

    // Construtor para classe No de um vizinho
	public No(String endereco, int porta) {
		this.rede = new Rede(endereco, porta);
	}

	// Consttrutor para classe No de um vizinho enviado por LIST_PEER
	public No(String enderecoIP, String porta, Status status) {
		this.rede = new Rede(enderecoIP, porta, status);
	}

}
