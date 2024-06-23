package dsid.peerToPeer;

import static dsid.peerToPeer.utils.Constantes.UM;
import static dsid.peerToPeer.utils.Constantes.ZERO;

import dsid.peerToPeer.controller.InterfaceUsuario;

public class Main {
    public static void main(String[] args) {
    	
    	String argumento = args[0];
    	
    	String[] partes = argumento.split(":");
    	String endereco = partes[ZERO];
    	int porta = Integer.parseInt(partes[UM]);
    	
    	No no = new No(endereco, porta);
    	
    	InterfaceUsuario interfaceUsuario = new InterfaceUsuario(no);
    	interfaceUsuario.iniciar(no);
    }
}
