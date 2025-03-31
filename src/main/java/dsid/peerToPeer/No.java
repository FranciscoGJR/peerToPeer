package dsid.peerToPeer;

import java.util.List;

import dsid.peerToPeer.model.rede.Rede;
import dsid.peerToPeer.service.RedeService;
import lombok.Data;

@Data
public class No {
	
	private RedeService redeService;

	public No(String endereco, Integer porta, List<No> vizinhos) {
		Rede rede = new Rede(endereco, porta, vizinhos);
		this.redeService = new RedeService(rede);
	}

	public No(String endereco, int porta) {
		Rede rede = new Rede(endereco, porta);
		this.redeService = new RedeService(rede); 
	}

}
