package dsid.peerToPeer.service;

import dsid.peerToPeer.model.rede.Mensagem;

public class MensagemService {

    public String encaminhandoMensagem(Mensagem mensagem) {
        String enderecoPortaDestino = mensagem.getDestino().getRedeService().getEnderecoIP() + ":" + mensagem.getDestino().getRedeService().getPorta();
        return "Encaminhando mensagem '" + mensagem.toString() + "' para " + enderecoPortaDestino;
    }

    public String encaminhadoComSucesso(Mensagem mensagem) {
        return "\tEnvio feito com sucesso: '" + mensagem.toString() + "'";
    }
}

