package dsid.peer.model;

import java.util.ArrayList;
import java.util.List;

public class Network {
	private final String endereco;
	private final int porta;
	private int clock = 0;
	private final List<Peer> vizinhos;
	private boolean statusOnline = false;

	public Network(String endereco, int porta, List<Peer> vizinhos) {
    	this.endereco = endereco;
    	this.porta = porta;
    	this.vizinhos = (vizinhos != null) ? new ArrayList(vizinhos) : new ArrayList();
	}

	public String getEndereco() { return endereco; }
	public int getPorta() { return porta; }
	public int getClock() { return clock; }
	public List<Peer> getVizinhos() { return vizinhos; }
	public boolean isStatusOnline() { return statusOnline; }
	public void setStatusOnline(boolean online) { this.statusOnline = online; }
	
	public int incrementarClock() {
    	return ++clock;
	}
}