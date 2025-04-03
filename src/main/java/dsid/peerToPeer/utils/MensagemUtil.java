package dsid.peerToPeer.utils;

import static dsid.peerToPeer.utils.Constantes.UM;
import static dsid.peerToPeer.utils.Constantes.ZERO;

public class MensagemUtil {

	public static String getEndereco(String enderecoEPorta) {
    	String[] partes = enderecoEPorta.split(":");
    	return partes[ZERO];
    }
    
    
    public static Integer getPorta(String enderecoEPorta) {
    	String[] partes = enderecoEPorta.split(":");
    	return Integer.parseInt(partes[UM]);
    }
}
