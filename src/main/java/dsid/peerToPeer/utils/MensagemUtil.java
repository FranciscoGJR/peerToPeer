package dsid.peerToPeer.utils;

import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.GET_PEERS;
import static dsid.peerToPeer.utils.Constantes.HELLO;
import static dsid.peerToPeer.utils.Constantes.PEER_LIST;
import static dsid.peerToPeer.utils.Constantes.UM;
import static dsid.peerToPeer.utils.Constantes.ZERO;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.model.rede.Rede;

public class MensagemUtil {
	
	public static String peerAtualizado(Mensagem mensagemEnviada) {
		String enderecoIP = mensagemEnviada.getDestino().getRede().getEnderecoIP();
		Integer porta = mensagemEnviada.getDestino().getRede().getPorta();
		Status status = mensagemEnviada.getDestino().getRede().getStatus();
		return new String("\tAtualizando peer " + enderecoIP + ":" + porta + " status " + status);
	}
	
	
	public static String peerAdicionado(Rede rede) {
		String enderecoIP = rede.getEnderecoIP();
		Integer porta = rede.getPorta();
		Status status = rede.getStatus();
		return new String("\tAtualizando peer " + enderecoIP + ":" + porta + " status " + status);
	}
	

	public static String desserializarArgumentoListPeer(Rede rede) {
		String endereco = rede.getEnderecoIP();
		String porta = String.valueOf(rede.getPorta());
		String status = String.valueOf(rede.getStatus());
		return new String(endereco + ":" + porta + ":" + status + ":0");
	}

	
	public static Mensagem serializarMensagem(String mensagemEmTexto) {
        String edereco = getEndereco(getEnderecoEPorta(mensagemEmTexto));
        Integer porta = getPorta(getEnderecoEPorta(mensagemEmTexto));
        TipoMensagemEnum tipoMensagem = getTipoMensagem(mensagemEmTexto);
        return new Mensagem(new No(edereco, porta), null, ZERO, tipoMensagem);
	}


	public static TipoMensagemEnum getTipoMensagem(String mensagemCompleta) {
		String[] tokens = mensagemCompleta.split(" ");
		switch (tokens[DOIS]) {
        	case HELLO:
        		return TipoMensagemEnum.HELLO;
        	case GET_PEERS:
        		return TipoMensagemEnum.GET_PEERS;
        	case PEER_LIST:
        		return TipoMensagemEnum.PEER_LIST;
		}
		
		return null;
	}


	public static String getEnderecoEPorta(String mensagemCompleta) {
		String[] tokens = mensagemCompleta.split(" ");
		return tokens[ZERO];
	}


	public static String getEndereco(String enderecoEPorta) {
    	String[] partes = enderecoEPorta.split(":");
    	return partes[ZERO];
    }
    
    
    public static Integer getPorta(String enderecoEPorta) {
    	String[] partes = enderecoEPorta.split(":");
    	return Integer.parseInt(partes[UM]);
    }
}
