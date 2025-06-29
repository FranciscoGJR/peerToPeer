package dsid.peerToPeer.model.rede;

import static dsid.peerToPeer.utils.MensagemUtil.formartarArgumentos;

import java.util.ArrayList;
import java.util.List;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.TipoMensagemEnum;
import lombok.Data;

@Data
public class Mensagem {
	
	private Integer numeroDeSequencia;

	private No origem;

	private No destino;
	
	private Integer clock;

	private Integer chunk;

	private TipoMensagemEnum tipo;

    private List<String> argumentos = new ArrayList<>();
    

    public Mensagem(No origem, No destino, TipoMensagemEnum tipoMensagem) {
    	this.origem = origem;
    	this.clock = origem.getRede().getClock();
    	this.destino = destino;
    	this.tipo = tipoMensagem;
    	this.numeroDeSequencia = 1;
    }


    public Mensagem(No origem, No destino, TipoMensagemEnum tipoMensagem, Integer clock) {
    	this.origem = origem;
    	this.clock = origem.getRede().getClock();
    	this.destino = destino;
    	this.tipo = tipoMensagem;
    	this.numeroDeSequencia = 1;
    }


    // Mensagem com argumentos
    public Mensagem(No noOrigem, No noDestino, TipoMensagemEnum peerList, List<String> argumentos) {
    	this.origem = noOrigem;
    	this.clock = noOrigem.getRede().getClock();
		this.chunk = noOrigem.getRede().getChunk();
    	this.destino = noDestino;
    	this.tipo = peerList;
    	this.argumentos = argumentos;
	}


	@Override
    public String toString() {
    	String enderecoPortaOrigem = origem.getRede().getEnderecoIP() + ":" + origem.getRede().getPorta();
    	return enderecoPortaOrigem + " " + getClock() + " " + getTipo() + formartarArgumentos(origem, this.argumentos);
    }
	
}