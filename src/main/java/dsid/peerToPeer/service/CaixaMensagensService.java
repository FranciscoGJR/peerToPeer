package dsid.peerToPeer.service;

import dsid.peerToPeer.model.rede.CaixaMensagens;
import dsid.peerToPeer.model.rede.Mensagem;

public class CaixaMensagensService {

    public CaixaMensagensService() {
    }

    public void novaMensagemRecebida(Mensagem mensagem, CaixaMensagens caixaMensagens) {
        caixaMensagens.getRecebidas().put(quantidadeMensagensRecebidas(caixaMensagens), mensagem);
    }

    public void novaMensagemEnviada(Mensagem mensagem, CaixaMensagens caixaMensagens) {
        caixaMensagens.getEnviadas().put(quantidadeMensagemEnviadas(caixaMensagens), mensagem);
    }

    public int quantidadeMensagensRecebidas(CaixaMensagens caixaMensagens) {
        return caixaMensagens.getRecebidas().size();
    }

    public int quantidadeMensagemEnviadas(CaixaMensagens caixaMensagens) {
        return caixaMensagens.getEnviadas().size();
    }
}

