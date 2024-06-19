package dsid.peerToPeer;

import dsid.peerToPeer.model.*;
import dsid.peerToPeer.rede.*;
import lombok.*;

@Data
public class No {
	
	private Dados dados;

	private Rede rede;
	
	public No(String endereco, Integer porta) {
		this.dados = new Dados();
		this.rede = new Rede(endereco, porta);
	}

}
