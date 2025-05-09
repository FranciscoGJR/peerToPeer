package dsid.peerToPeer.model.rede;

import java.util.HashMap;
import lombok.Data;

@Data
public class CaixaDeMensagens {

	private HashMap<Integer, Mensagem> recebidas;

	private HashMap<Integer, Mensagem> enviadas;


	public CaixaDeMensagens() {
		this.recebidas = new HashMap<>();
		this.enviadas = new HashMap<>();
	}
	

	public void adicionarMensagemRecebida(Mensagem mensagemRecebida) {
		recebidas.put(quantidadeRecebidas() + 1, mensagemRecebida);
	}
	
	
	public void adicionarMensagemEnviada(Mensagem mensagemRecebida) {
		enviadas.put(quantidadeEnviadas(), mensagemRecebida);
	}

	
	public Integer quantidadeRecebidas() {
		return recebidas.size();
	}
	
	
	public Integer quantidadeEnviadas() {
		return enviadas.size();
	}
}
