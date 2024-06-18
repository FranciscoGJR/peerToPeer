package dsid.peerToPeer.model;

public class ParChaveValor {

    private String chave;
    private String valor;

    public ParChaveValor() {
    }

    public ParChaveValor(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ParChaveValor{" +
                "chave='" + chave + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
