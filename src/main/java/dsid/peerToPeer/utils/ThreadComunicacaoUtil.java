package dsid.peerToPeer.utils;

import dsid.peerToPeer.controller.InterfaceUsuario;

public class ThreadComunicacaoUtil {

	public static void novaMensagem(String mensagem) {
		System.out.println("\n\t==== NOVA MENSAGEM ====");
		System.out.println("\t" + mensagem);
		System.out.println("\t=======================\n");
		
		InterfaceUsuario.exibirMenu();
	}
}
