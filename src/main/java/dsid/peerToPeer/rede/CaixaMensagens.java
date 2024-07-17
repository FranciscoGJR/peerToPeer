package dsid.peerToPeer.rede;

import java.util.HashMap;

import lombok.Data;

@Data
public class CaixaMensagens {
	
	private HashMap<Integer, Mensagem> recebidas;
	
	private HashMap<Integer, Mensagem> enviadas;
	
	
	public CaixaMensagens() {
		this.recebidas = new HashMap<>();
		this.enviadas = new HashMap<>();
	}
	

	public void novaRecebida(Mensagem mensagem) {
		recebidas.put(quantidadeRecebidas(), mensagem);
	}
	

	public void novaEnviada(Mensagem mensagem) {
		enviadas.put(quantidadeEnviadas(), mensagem);
	}
	
	
	public int quantidadeRecebidas() {
		return recebidas.size();
	}
	
	
	public int quantidadeEnviadas() {
		return enviadas.size();
	}
}
