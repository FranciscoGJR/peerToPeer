package dsid.peerToPeer.model;

import java.util.ArrayList;
import java.util.List;

public class Par {

    private String enderecoIP;
    private int porta;
    private String nome;
    private List<ParChaveValor> tabelaParChaveValor;

    public Par() {
        this.tabelaParChaveValor = new ArrayList<>();
    }

    public Par(String enderecoIP, int porta, String nome) {
        this.enderecoIP = enderecoIP;
        this.porta = porta;
        this.nome = nome;
        this.tabelaParChaveValor = new ArrayList<>();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "Par{" +
                "enderecoIP='" + enderecoIP + '\'' +
                ", porta=" + porta +
                ", nome='" + nome + '\'' +
                ", tabelaParChaveValor=" + tabelaParChaveValor +
                '}';
    }
}
