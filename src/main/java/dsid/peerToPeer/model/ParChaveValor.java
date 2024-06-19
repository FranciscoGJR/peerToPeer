package dsid.peerToPeer.model;

import lombok.Data;

@Data
public class ParChaveValor {

    private String chave;
    private String valor;

    public ParChaveValor() {
    }

    public ParChaveValor(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
    }

}
