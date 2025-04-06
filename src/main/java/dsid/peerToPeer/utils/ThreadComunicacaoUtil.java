package dsid.peerToPeer.utils;

import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.MENSAGEM_RECEBIDA;
import static dsid.peerToPeer.utils.Constantes.MIL;

public class ThreadComunicacaoUtil {


	public static void exibirMensagem(String mensagem) {
		System.out.println(MENSAGEM_RECEBIDA + mensagem);
		esperaEmSegundos(DOIS);
	}
	
	
	public static void esperaEmSegundos(Integer segundos) {
		try {
			Thread.sleep(segundos * MIL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
