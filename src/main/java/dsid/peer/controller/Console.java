package dsid.peer.controller;

import static dsid.peer.utils.Constantes.ALTERAR_CHUNK;
import static dsid.peer.utils.Constantes.BUSCAR_ARQUIVOS;
import static dsid.peer.utils.Constantes.EXIBIR_ESTATISTICAS;
import static dsid.peer.utils.Constantes.LISTAR_ARQUIVOS_LOCAIS;
import static dsid.peer.utils.Constantes.LISTAR_PEERS;
import static dsid.peer.utils.Constantes.LISTA_DE_PEERS;
import static dsid.peer.utils.Constantes.MENU_COMPLETO;
import static dsid.peer.utils.Constantes.MIL;
import static dsid.peer.utils.Constantes.OBTER_PEERS;
import static dsid.peer.utils.Constantes.OPCAO_INVALIDA;
import static dsid.peer.utils.Constantes.OPCAO_VOLTAR_MENU;
import static dsid.peer.utils.Constantes.SAIR;
import static dsid.peer.utils.Constantes.TRES;
import static dsid.peer.utils.MensagemUtil.exibirMensagemPeerAtualizado;
import static dsid.peer.utils.Status.ONLINE;

import java.io.File;
import java.util.Scanner;

import dsid.peer.model.Peer;
import dsid.peer.model.rede.Mensagem;
import dsid.peer.service.RedeService;
import dsid.peer.utils.Status;
import dsid.peer.utils.TipoMensagemEnum;

public class Console {

	private RedeService redeService = new RedeService();

	private Peer no;

	private Scanner scanner = new Scanner(System.in);

	private String diretorioCompartilhado;

	public Console(Peer no, String diretorioCompartilhado) {
		this.no = no;
		this.diretorioCompartilhado = diretorioCompartilhado;
	}

	public void iniciar(Peer no) {
		int opcao;

		while (true) {
			esperaEmSegundos(TRES);
			exibirMenu();

			opcao = scanner.nextInt();
			scanner.nextLine();
			switch (opcao) {
			case LISTAR_PEERS:
				this.listarVizinhos();
				System.out.print("> ");
				opcao = scanner.nextInt();

				if (opcao == 0) {
					continue;
				}

				this.enviarHello(opcao);
				break;
			case OBTER_PEERS:
				this.enviarGetPeers();
				break;
			case LISTAR_ARQUIVOS_LOCAIS:
				this.listarArquivosLocais();
				break;
			case BUSCAR_ARQUIVOS:
				break;
			case EXIBIR_ESTATISTICAS:
				break;
			case ALTERAR_CHUNK:
				break;
			case SAIR:
				encerrarNo();
				return;
			default:
				System.out.println(OPCAO_INVALIDA);
			}
		}
	}

	private void enviarHello(int numeroVizinho) {
		Peer noDestinatario = no.getRede().getVizinhos().get(numeroVizinho - 1);
		Mensagem mensagem = new Mensagem(this.no, noDestinatario, TipoMensagemEnum.HELLO);

		boolean mensagemEnviadaComSucesso = this.redeService.enviarMensagem(mensagem,
				this.no.getRede().getCaixaDeMensagens());
		if (mensagemEnviadaComSucesso) {
			this.no.getRede().incrementarClock();
			noDestinatario.getRede().setStatus(ONLINE);
			exibirMensagemPeerAtualizado(mensagem);
			return;
		}

		noDestinatario.getRede().setStatus(Status.OFFLINE);
	}

	private void enviarGetPeers() {
		for (Peer noDestinatario : this.no.getRede().getVizinhos()) {
			this.no.getRede().incrementarClock();
			Mensagem mensagem = new Mensagem(this.no, noDestinatario, TipoMensagemEnum.GET_PEERS);

			this.redeService.enviarMensagem(mensagem, this.no.getRede().getCaixaDeMensagens());
		}
	}

	private void listarArquivosLocais() {
		File diretorio = new File(this.diretorioCompartilhado);
		File[] arquivos = diretorio.listFiles();
		for (File arquivo : arquivos) {
			if (arquivo.isFile()) {
				System.out.println(arquivo.getName());
			}
		}
	}

	private void listarVizinhos() {
		System.out.println(LISTA_DE_PEERS);
		System.out.println(OPCAO_VOLTAR_MENU);
		this.redeService.listarVizinhos(no.getRede());
	}

	private void encerrarNo() {
		this.redeService.pararEscuta(no.getRede());
	}

	public static void exibirMenu() {
		System.out.print(MENU_COMPLETO);
	}

	// TO-DO: adicionar em um util separado
	public static void esperaEmSegundos(Integer segundos) {
		try {
			Thread.sleep(segundos * MIL);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
