package dsid.peerToPeer.service;

import static dsid.peerToPeer.utils.Constantes.ENCAMINHANDO_MENSAGEM;
import static dsid.peerToPeer.utils.Constantes.PARA;

import dsid.peerToPeer.model.rede.Mensagem;

public class MensagemService {
	
    public String encaminhandoMensagem(Mensagem mensagem) {
        String enderecoPortaDestino = mensagem.getDestino().getRede().getEnderecoIP() + ":" + mensagem.getDestino().getRede().getPorta();
        return ENCAMINHANDO_MENSAGEM + mensagem.toString() + PARA + enderecoPortaDestino;
    }


    public String encaminhadoComSucesso(Mensagem mensagem) {
        return "\tEnvio feito com sucesso: '" + mensagem.toString() + "'";
    }
}

