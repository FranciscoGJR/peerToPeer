package dsid.peerToPeer.rede;

import java.util.List;

import dsid.peerToPeer.utils.TipoMensagemEnum;

public class Mensagem {

	private String origem;
	private int sequencia; 
    private int ttl;
	private TipoMensagemEnum tipo;
    private List<String> argumentos;

    public Mensagem(TipoMensagemEnum tipo, String origem, int sequencia, int ttl, List<String> argumentos) {
        this.tipo = tipo;
        this.origem = origem;
        this.sequencia = sequencia;
        this.ttl = ttl;
        this.argumentos = argumentos;
    }

    
    public TipoMensagemEnum getTipo() {
        return tipo;
    }

    
    public void setTipo(TipoMensagemEnum tipo) {
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
