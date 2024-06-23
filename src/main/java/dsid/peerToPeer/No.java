package dsid.peerToPeer;

import java.util.List;

import dsid.peerToPeer.model.Dados;
import dsid.peerToPeer.rede.Rede;
import lombok.Data;

@Data
public class No {
	
	private Dados dados;

	private Rede rede;
	
	public No(String endereco, Integer porta) {
		this.dados = new Dados();
		this.rede = new Rede(endereco, porta);
	}
	
	public No(String endereco, Integer porta, List<No> vizinhos) {
		this.dados = new Dados();
		this.rede = new Rede(endereco, porta, vizinhos);
	}

}
