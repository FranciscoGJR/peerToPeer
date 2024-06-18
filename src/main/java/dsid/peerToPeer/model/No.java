package dsid.peerToPeer.model;

import java.util.ArrayList;
import java.util.List;

public class No {

    private String enderecoIP;
    private int porta;
    private List<No> vizinhos = new ArrayList<>();
    private List<ParChaveValor> tabelaParChaveValor = new ArrayList<>();

    public No() {
    }

    public No(String enderecoIP, int porta) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
    }

    public String getEnderecoIP() {
        return enderecoIP;
    }

    public void setEnderecoIP(String enderecoIP) {
        this.enderecoIP = enderecoIP;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public List<ParChaveValor> getTabelaParChaveValor() {
        return tabelaParChaveValor;
    }

    public void adicionarParChaveValor(ParChaveValor parChaveValor) {
        tabelaParChaveValor.add(parChaveValor);
    }

    public void removerParChaveValor(ParChaveValor parChaveValor) {
        tabelaParChaveValor.remove(parChaveValor);
    }

	public List<No> getVizinhos() {
		return vizinhos;
	}

	public void setVizinhos(List<No> vizinhos) {
		this.vizinhos = vizinhos;
	}
	
	@Override
    public String toString() {
        return "Par{" +
                "enderecoIP='" + enderecoIP + '\'' +
                ", porta=" + porta +
                ", tabelaParChaveValor=" + tabelaParChaveValor +
                '}';
    }

}
