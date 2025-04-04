package dsid.peerToPeer.utils;

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

	
	public static Mensagem serializarMensagem(String mensagemEmTexto) {
        String edereco = getEndereco(getEnderecoEPorta(mensagemEmTexto));
        Integer porta = getPorta(getEnderecoEPorta(mensagemEmTexto));
        return new Mensagem(new No(edereco, porta), null, 0, TipoMensagemEnum.HELLO);
	}


	public static String getEnderecoEPorta(String mensagemCompleta) {
		String[] tokens = mensagemCompleta.split(" ");
		return tokens[0];
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
