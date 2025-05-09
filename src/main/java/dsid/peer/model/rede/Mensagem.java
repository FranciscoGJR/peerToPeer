package dsid.peer.model.rede;

import static dsid.peer.utils.MensagemUtil.formartarArgumentos;

import java.util.ArrayList;
import java.util.List;

import dsid.peer.model.Peer;
import dsid.peer.utils.TipoMensagemEnum;
import lombok.Data;

@Data
public class Mensagem {
	
	private Integer numeroDeSequencia;

	private Peer origem;

	private Peer destino;
	
	private Integer clock;

	private TipoMensagemEnum tipo;

    private List<String> argumentos = new ArrayList<>();
    

    public Mensagem(Peer origem, Peer destino, TipoMensagemEnum tipoMensagem) {
    	this.origem = origem;
    	this.clock = origem.getRede().getClock();
    	this.destino = destino;
    	this.tipo = tipoMensagem;
    	this.numeroDeSequencia = 1;
    }


    public Mensagem(Peer origem, Peer destino, TipoMensagemEnum tipoMensagem, Integer clock) {
    	this.origem = origem;
    	this.clock = origem.getRede().getClock();
    	this.destino = destino;
    	this.tipo = tipoMensagem;
    	this.numeroDeSequencia = 1;
    }


    // Mensagem com argumentos
    public Mensagem(Peer noOrigem, Peer noDestino, TipoMensagemEnum peerList, List<String> argumentos) {
    	this.origem = noOrigem;
    	this.clock = noOrigem.getRede().getClock();
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