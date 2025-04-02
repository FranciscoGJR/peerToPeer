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
}
