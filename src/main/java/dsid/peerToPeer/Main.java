package dsid.peerToPeer;

import dsid.peerToPeer.controller.*;

import static dsid.peerToPeer.utils.Constantes.*;

public class Main {
    public static void main(String[] args) {
    	
    	String argumento = args[0];
    	
    	String[] partes = argumento.split(":");
    	String endereco = partes[ZERO];
    	int porta = Integer.parseInt(partes[UM]);
    	
    	No no = new No(endereco, porta);
    	
    	InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
    	interfaceUsuario.iniciar(no);
    }
}
