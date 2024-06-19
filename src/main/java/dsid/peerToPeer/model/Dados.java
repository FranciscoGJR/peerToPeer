package dsid.peerToPeer.model;

import java.util.ArrayList;
import java.util.List;

public class Dados {
	
	private List<ParChaveValor> tabelaParChaveValor = new ArrayList<>();

	public List<ParChaveValor> getTabelaParChaveValor() {
		return tabelaParChaveValor;
	}

	public void setTabelaParChaveValor(List<ParChaveValor> tabelaParChaveValor) {
		this.tabelaParChaveValor = tabelaParChaveValor;
	}
	
}
