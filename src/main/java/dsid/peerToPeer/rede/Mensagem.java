package dsid.peerToPeer.rede;

import static dsid.peerToPeer.utils.Constantes.HELLO;

import java.util.List;

import dsid.peerToPeer.No;
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
    
    public String encaminhandoMensagem() {
    	String enderecoPortaDestino = destino.getRede().getEnderecoIP() + ":" + destino.getRede().getPorta();
    	return "Encaminhando mensagem '" + toString() + "' para " + enderecoPortaDestino;
    }
    
    public String encaminhadoComSucesso() {
    	return "\tEnvio feito com sucesso: '" + this.toString() + "'";
    }
    
    public String toString() {
    	String enderecoPortaOrigem = origem.getRede().getEnderecoIP() + ":" + origem.getRede().getPorta();
    	return enderecoPortaOrigem + " " + getNumeroDeSequencia() + " " + getTtl() + " " + getTipo();
    }

}
