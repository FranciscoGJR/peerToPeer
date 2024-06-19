package dsid.peerToPeer.rede;

import java.util.List;

import dsid.peerToPeer.utils.TipoMensagemEnum;
import lombok.Data;

@Data
public class Mensagem {

	private String origem;

	private int sequencia; 

    private int ttl;

	private TipoMensagemEnum tipo;

    private List<String> argumentos;

}
