package dsid.peerToPeer.utils;

import static dsid.peerToPeer.utils.Constantes.UM;
import static dsid.peerToPeer.utils.Constantes.ZERO;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.Mensagem;

public class MensagemUtil {
	
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
