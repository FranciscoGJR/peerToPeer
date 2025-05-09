package dsid.peer;

import java.io.File;
import java.util.List;

import dsid.peer.model.Peer;
import dsid.peer.util.ArquivoVizinhosUtil;

public class Main {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Uso: java Main <endereco:porta> <vizinhos.txt> <diretorio_compartilhado>");
			return;
		}

		String enderecoPorta = args[0];
		String arquivoVizinhos = args[1];
		String diretorioCompartilhado = args[2];

		List<Peer> vizinhos = ArquivoVizinhosUtil.lerVizinhos(arquivoVizinhos);
		Peer noLocal = new Peer(enderecoPorta, vizinhos);
		File pastaCompartilhada = new File(diretorioCompartilhado);

		if (!pastaCompartilhada.exists() || !pastaCompartilhada.isDirectory()) {
			System.err.println("Erro: Diretório compartilhado não existe ou não é válido.");
			return;
		}

		ServicePeer servicoPeer = new ServicePeer(noLocal, pastaCompartilhada);
		Console ui = new Console(servicoPeer);
		ui.iniciar();
	}
}