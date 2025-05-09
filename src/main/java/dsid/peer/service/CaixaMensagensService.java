package dsid.peerToPeer.service;

import dsid.peerToPeer.model.rede.CaixaDeMensagens;
import dsid.peerToPeer.model.rede.Mensagem;

public class CaixaMensagensService {

    public CaixaMensagensService() {
    }

    public void novaMensagemRecebida(Mensagem mensagem, CaixaDeMensagens caixaMensagens) {
        caixaMensagens.getRecebidas().put(quantidadeMensagensRecebidas(caixaMensagens), mensagem);
    }

    public void adicionarNovaMensagemEnviada(Mensagem mensagem, CaixaDeMensagens caixaMensagens) {
        caixaMensagens.getEnviadas().put(quantidadeMensagemEnviadas(caixaMensagens), mensagem);
    }

    public int quantidadeMensagensRecebidas(CaixaDeMensagens caixaMensagens) {
        return caixaMensagens.getRecebidas().size();
    }

    public int quantidadeMensagemEnviadas(CaixaDeMensagens caixaMensagens) {
        return caixaMensagens.getEnviadas().size();
    }
}

