package dsid.peerToPeer.utils;

import static dsid.peerToPeer.utils.Constantes.*;

import dsid.peerToPeer.controller.InterfaceUsuario;

public class ThreadComunicacaoUtil {


	public static void novaMensagem(String mensagem) {
		System.out.println(MENSAGEM_RECEBIDA + mensagem);
		esperaEmSegundos(DOIS);
		InterfaceUsuario.exibirMenu();
	}
	

	public static void esperaEmSegundos(Integer segundos) {
		try {
			Thread.sleep(segundos * MIL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
