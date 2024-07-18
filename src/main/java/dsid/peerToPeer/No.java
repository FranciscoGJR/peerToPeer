package dsid.peerToPeer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dsid.peerToPeer.model.Dados;
import dsid.peerToPeer.model.rede.Rede;
import dsid.peerToPeer.service.RedeService;
import lombok.Data;

@Data
public class No {
	
	private Dados dados;
	private RedeService redeService;  // Alteração para RedeService

	public No(String endereco, Integer porta, List<No> vizinhos, HashMap<String, Integer> listaChaveValor) {
		this.dados = new Dados(listaChaveValor);
		Rede rede = new Rede(endereco, porta, vizinhos);
		this.redeService = new RedeService(rede);
	}

	public No(String endereco, int porta) {
		Rede rede = new Rede(endereco, porta);
		this.redeService = new RedeService(rede); 
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
