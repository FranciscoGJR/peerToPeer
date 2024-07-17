package dsid.peerToPeer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dsid.peerToPeer.model.Dados;
import dsid.peerToPeer.rede.Rede;
import lombok.Data;

@Data
public class No {
	
	private Dados dados;

	private Rede rede;
	
	public No(String endereco, Integer porta, List<No> vizinhos, HashMap<String, Integer> listaChaveValor) {
		this.dados = new Dados(listaChaveValor);
		this.rede = new Rede(endereco, porta, vizinhos);
	}

	public No(String endereco, int porta) {
		this.rede = new Rede(endereco, porta);
		this.dados = new Dados();
	}

	public Integer buscarLocalmente(String chave) {
        for (Map.Entry<String, Integer> entrada : this.getDados().getListaChaveValor().entrySet()) {
        	if (chave.equals(entrada.getKey())) {
        		return entrada.getValue();
        	}
        }
		return null;
	}

}
