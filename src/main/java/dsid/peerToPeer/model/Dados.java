package dsid.peerToPeer.model;

import java.util.HashMap;

import lombok.Data;

@Data
public class Dados {
	
	private HashMap<String, Integer> listaChaveValor;
	
	public Dados(HashMap<String, Integer> listaChaveValor) {
		this.listaChaveValor = listaChaveValor;
	}

	public Dados() {
		this.listaChaveValor = new HashMap<>();
	}
	
}
