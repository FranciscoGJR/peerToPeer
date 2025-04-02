package dsid.peerToPeer.model.rede;

import static dsid.peerToPeer.utils.Constantes.*;

import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.TipoMensagemEnum;
import lombok.Data;

@Data
public class Mensagem {
	
	private Integer numeroDeSequencia;
	private String texto;
	private No origem;
	private No destino;
    private int ttl;
	private TipoMensagemEnum tipo;
    private List<String> argumentos;

    // Mensagem HELOO
    public Mensagem(No origem, No destino, int ttl, TipoMensagemEnum tipoMensagem) {
    	this.texto = HELLO;
    	this.origem = origem;
    	this.destino = destino;
    	this.ttl = ttl;
    	this.tipo = tipoMensagem;
    	this.numeroDeSequencia = 1;
    }

    @Override
    public String toString() {
    	String enderecoPortaOrigem = origem.getRede().getEnderecoIP() + ":" + origem.getRede().getPorta();
    	return enderecoPortaOrigem + " " + getNumeroDeSequencia() + " " + getTtl() + " " + getTipo();
    }
}
