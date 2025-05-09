package dsid.peer.network;

import dsid.peer.model.Peer;

import java.util.List;

public class Message {
	private final Peer origem;
	private final Peer destino;
	private final MessageType tipo;
	private final int clock;
	private final List<String> argumentos;

	public Message(Peer origem, Peer destino, MessageType tipo, int clock) {
    	this(origem, destino, tipo, clock, null);
	}

	public Message(Peer origem, Peer destino, MessageType tipo, int clock, List<String> argumentos) {
    	this.origem = origem;
    	this.destino = destino;
    	this.tipo = tipo;
    	this.clock = clock;
    	this.argumentos = argumentos;
	}

	public Peer getOrigem() { return origem; }
	public Peer getDestino() { return destino; }
	public MessageType getTipo() { return tipo; }
	public int getClock() { return clock; }
	public List<String> getArgumentos() { return argumentos; }

	public String toProtocolString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(origem.getNetwork().getEndereco()).append(":").append(origem.getNetwork().getPorta())
      	.append(" ").append(clock).append(" ").append(tipo);
    	if (argumentos != null && !argumentos.isEmpty()) {
        	for (String arg : argumentos) sb.append(" ").append(arg);
    	}
    	return sb.toString();
	}
}