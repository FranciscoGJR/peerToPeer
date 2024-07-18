package dsid.peerToPeer.service;

import dsid.peerToPeer.model.rede.CaixaMensagens;
import dsid.peerToPeer.model.rede.Mensagem;

public class CaixaMensagensService {

    private CaixaMensagens caixaMensagens;

    public CaixaMensagensService(CaixaMensagens caixaMensagens) {
        this.caixaMensagens = caixaMensagens;
    }

    public void novaRecebida(Mensagem mensagem) {
        caixaMensagens.getRecebidas().put(quantidadeRecebidas(), mensagem);
    }

    public void novaEnviada(Mensagem mensagem) {
        caixaMensagens.getEnviadas().put(quantidadeEnviadas(), mensagem);
    }

    public int quantidadeRecebidas() {
        return caixaMensagens.getRecebidas().size();
    }

    public int quantidadeEnviadas() {
        return caixaMensagens.getEnviadas().size();
    }
}

