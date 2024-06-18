package dsid.peerToPeer.model;

import java.util.List;

public class Mensagem {

    private String tipo;
    private String origem;
    private int sequencia;
    private int ttl;
    private List<String> argumentos;

    public Mensagem(String tipo, String origem, int sequencia, int ttl, List<String> argumentos) {
        this.tipo = tipo;
        this.origem = origem;
        this.sequencia = sequencia;
        this.ttl = ttl;
        this.argumentos = argumentos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int getSequencia() {
        return sequencia;
    }

    public void setSequencia(int sequencia) {
        this.sequencia = sequencia;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public List<String> getArguments() {
        return argumentos;
    }

    public void setArguments(List<String> argumentos) {
        this.argumentos = argumentos;
    }

    @Override
    public String toString() {
        return "Mensagem{" +
                "tipo='" + tipo + '\'' +
                ", origem='" + origem + '\'' +
                ", sequencia=" + sequencia +
                ", ttl=" + ttl +
                ", argumentos=" + argumentos +
                '}';
    }
}
