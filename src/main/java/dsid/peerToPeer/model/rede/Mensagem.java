package dsid.peerToPeer.model.rede;

import static dsid.peerToPeer.utils.Constantes.*;
import static dsid.peerToPeer.utils.MensagemUtil.formartarArgumentos;

import java.util.ArrayList;
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

	private TipoMensagemEnum tipo;

    private List<String> argumentos = new ArrayList<>();


    // Mensagem
    public Mensagem(No origem, No destino, int ttl, TipoMensagemEnum tipoMensagem) {
    	this.texto = HELLO;
    	this.origem = origem;
    	this.destino = destino;
    	this.tipo = tipoMensagem;
    	this.numeroDeSequencia = 1;
    }


    // Mensagem com argumentos
    public Mensagem(No noOrigem, No noDestino, TipoMensagemEnum peerList, List<String> argumentos) {
    	this.origem = noOrigem;
    	this.destino = noDestino;
    	this.tipo = peerList;
    	this.argumentos = argumentos;
	}

	@Override
    public String toString() {
    	String enderecoPortaOrigem = origem.getRede().getEnderecoIP() + ":" + origem.getRede().getPorta();
    	return enderecoPortaOrigem + " " + "CLOCK " + getTipo() + formartarArgumentos(origem, this.argumentos);
    }
	
}