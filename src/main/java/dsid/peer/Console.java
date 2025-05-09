package dsid.peer;

import dsid.peer.model.Peer;

import java.util.List;
import java.util.Scanner;

public class Console {
	private final ServicePeer servicoPeer;
	private final Scanner scanner = new Scanner(System.in);

	public Console(ServicePeer servicoPeer) {
		this.servicoPeer = servicoPeer;
	}

	public void iniciar() {
		while (true) {
			exibirMenu();
			int opcao = lerInteiro();
			switch (opcao) {
			case 1:
				menuListarVizinhos();
				break;
			case 2:
				servicoPeer.obterVizinhos();
				break;
			case 3:
				servicoPeer.listarArquivosLocais();
				break;
			case 9:
				servicoPeer.encerrar();
				System.out.println("Saindo...");
				return;
			default:
				System.out.println("Opção inválida!");
			}
		}
	}

	private void exibirMenu() {
		System.out.println("\nEscolha um comando:");
		System.out.println("[1] Listar peers");
		System.out.println("[2] Obter peers");
		System.out.println("[3] Listar arquivos locais");
		System.out.println("[9] Sair");
		System.out.print("> ");
	}

	private void menuListarVizinhos() {
		List<Peer> vizinhos = servicoPeer.listarVizinhos();
		for (int i = 0; i < vizinhos.size(); i++) {
			System.out.printf("[%d] %s\n", i + 1, vizinhos.get(i));
		}
		System.out.println("[0] Voltar ao menu principal");
		System.out.print("> ");
		int escolha = lerInteiro();
		if (escolha > 0 && escolha <= vizinhos.size()) {
			servicoPeer.enviarHello(vizinhos.get(escolha - 1));
		}
	}

	private int lerInteiro() {
		while (!scanner.hasNextInt()) {
			scanner.nextLine();
			System.out.print("> ");
		}
		int valor = scanner.nextInt();
		scanner.nextLine();
		return valor;
	}
}