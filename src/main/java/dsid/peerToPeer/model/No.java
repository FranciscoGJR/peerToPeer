package dsid.peerToPeer.model;

import java.util.List;

import dsid.peerToPeer.model.rede.Rede;
import dsid.peerToPeer.service.RedeService;
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

}
