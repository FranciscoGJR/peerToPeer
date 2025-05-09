package dsid.peerToPeer.service;

import static dsid.peerToPeer.utils.Constantes.ENCAMINHANDO_MENSAGEM;
import static dsid.peerToPeer.utils.Constantes.PARA;
import static dsid.peerToPeer.utils.MensagemUtil.desserializarArgumentoListPeer;

import java.util.ArrayList;
import java.util.List;

import dsid.peerToPeer.model.Peer;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.model.rede.Rede;

public class MensagemService {
	
	public List<String> preencherArgumentosParaMensagemListPeer(List<Peer> vizinhos, Peer noDestinatario) {
		List<String> argumentos = new ArrayList<>();
		argumentos.add(String.valueOf(vizinhos.size()));
   		String noDestinatarioTexto = noDestinatario.getRede().getEnderecoIP() + ":" + noDestinatario.getRede().getPorta();
		for (Peer vizinho : vizinhos) {
			if (nosIguais(vizinho.getRede(), noDestinatarioTexto)) {
				continue;
			}
				
			argumentos.add(desserializarArgumentoListPeer(vizinho.getRede()));
		}

		return argumentos;
	}

	
    public String encaminhandoMensagem(Mensagem mensagem) {
        String enderecoPortaDestino = mensagem.getDestino().getRede().getEnderecoIP() + ":" + mensagem.getDestino().getRede().getPorta();
        return ENCAMINHANDO_MENSAGEM + mensagem.toString() + PARA + enderecoPortaDestino;
    }


    public String encaminhadoComSucesso(Mensagem mensagem) {
        return "\tEnvio feito com sucesso: '" + mensagem.toString() + "'";
    }
    
    private boolean nosIguais(Rede redeUm, String no) {
    	if (no.contains(redeUm.getEnderecoIP()) && no.contains(redeUm.getPorta().toString())) {
    		return true;
    	}
    	return false;
    	
    }
}

