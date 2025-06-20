package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.*;
import static dsid.peerToPeer.utils.MensagemUtil.exibirMensagemPeerAtualizado;
import static dsid.peerToPeer.utils.Status.ONLINE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.service.RedeService;
import dsid.peerToPeer.utils.Status;
import dsid.peerToPeer.utils.ThreadComunicacaoUtil;
import dsid.peerToPeer.utils.TipoMensagemEnum;

public class Console {

	private RedeService redeService = new RedeService();

	private No no;

	private Scanner scanner = new Scanner(System.in);

	private String diretorioCompartilhado;

	public Console(No no, String diretorioCompartilhado) {
		this.no = no;
		this.diretorioCompartilhado = diretorioCompartilhado;
	}

	public void iniciar(No no) {
		int opcao;

		while (true) {
			esperaEmSegundos(UM);
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
				buscarArquivos();
				break;
			case EXIBIR_ESTATISTICAS:
				break;
			case ALTERAR_CHUNK:
				alterarChunk();
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
		No noDestinatario = no.getRede().getVizinhos().get(numeroVizinho - 1);
		Mensagem mensagem = new Mensagem(this.no, noDestinatario, TipoMensagemEnum.HELLO);
		this.no.getRede().incrementarClock();

		boolean mensagemEnviadaComSucesso = this.redeService.enviarMensagem(mensagem,
				this.no.getRede().getCaixaDeMensagens());
		if (mensagemEnviadaComSucesso) {
			noDestinatario.getRede().setStatus(ONLINE);
			exibirMensagemPeerAtualizado(mensagem);
			return;
		}

		noDestinatario.getRede().setStatus(Status.OFFLINE);
	}

	private void enviarGetPeers() {
		for (No vizinho : this.no.getRede().getVizinhos()) {
			this.no.getRede().incrementarClock();
			Mensagem mensagem = new Mensagem(this.no, vizinho, TipoMensagemEnum.GET_PEERS);

			this.redeService.enviarMensagem(mensagem, this.no.getRede().getCaixaDeMensagens());
		}
	}

	private void buscarArquivos() {
		List<ArquivoDisponivel> arquivosEncontrados = new ArrayList();
		for (No vizinho : this.no.getRede().getVizinhos()) {
			if (vizinho.getRede().getStatus() == Status.ONLINE) {
				this.no.getRede().incrementarClock();
				Mensagem msg = new Mensagem(this.no, vizinho, TipoMensagemEnum.LS);
				Mensagem resposta = redeService.enviarMensagemEsperandoResposta(msg,
						this.no.getRede().getCaixaDeMensagens());
				if (resposta != null && resposta.getTipo() == TipoMensagemEnum.LS_LIST) {
					ThreadComunicacaoUtil.exibirMensagem(resposta.toString());
					this.no.getRede().incrementarClock();
					int qtd = Integer.parseInt(resposta.getArgumentos().get(0));
					for (int i = 1; i <= qtd; i++) {
						String[] tokens = resposta.getArgumentos().get(i).split(":");
						String nome = tokens[0];
						long tamanho = Long.parseLong(tokens[1]);
						arquivosEncontrados.add(new ArquivoDisponivel(nome, tamanho, vizinho));
					}
				}
			}
		}
		exibirMenuArquivos(arquivosEncontrados);
	}

	private void exibirMenuArquivos(List<ArquivoDisponivel> arquivos) {
		System.out.println();
		System.out.printf("%-20s %-10s %-21s\n", "Nome", "Tamanho", "Peer");
		System.out.printf("%-4s %-20s %-10s %-21s\n", "[ 0]", "<Cancelar>", "", "");
		int idx = 1;
		for (ArquivoDisponivel arq : arquivos) {
			System.out.printf("[%2d] %-16s %-10d %-21s\n", idx, arq.nome, arq.tamanho,
					arq.peer.getRede().getEnderecoIP() + ":" + arq.peer.getRede().getPorta());
			idx++;
		}
		System.out.print("Digite o numero do arquivo para fazer o download:\n> ");
		int opcao = scanner.nextInt();
		scanner.nextLine();
		if (opcao > 0 && opcao <= arquivos.size()) {
			System.out.println("arquivo escolhido " + arquivos.get(opcao - 1).nome);
			baixarArquivo(arquivos.get(opcao - 1));
		}
	}

	private void baixarArquivo(ArquivoDisponivel arq) {
		this.no.getRede().incrementarClock();
		List<String> args = Arrays.asList(arq.nome, "0", "0");
		Mensagem dlMsg = new Mensagem(this.no, arq.peer, TipoMensagemEnum.DL, args);
		Mensagem resposta = redeService.enviarMensagemEsperandoResposta(dlMsg, this.no.getRede().getCaixaDeMensagens());
		if (resposta != null && resposta.getTipo() == TipoMensagemEnum.FILE) {
			ThreadComunicacaoUtil.exibirMensagem(resposta.toString());
			this.no.getRede().incrementarClock();
			String nome = resposta.getArgumentos().get(0);
			String conteudoBase64 = resposta.getArgumentos().get(3);
			byte[] conteudo = Base64.getDecoder().decode(conteudoBase64);
			Path path = Paths.get(diretorioCompartilhado, nome);
			try {
				Files.write(path, conteudo);
				System.out.println("\n\nDownload do arquivo " + nome + " finalizado.");
			} catch (IOException e) {
				System.err.println("Erro ao salvar arquivo: " + e.getMessage());
			}
		}
	}

	public static class ArquivoDisponivel {
		String nome;
		long tamanho;
		No peer;

		public ArquivoDisponivel(String nome, long tamanho, No peer) {
			this.nome = nome;
			this.tamanho = tamanho;
			this.peer = peer;
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


	private void alterarChunk() {
		System.out.printf("Digite o novo tamanho de chunk:\n> ");
		int novoChunk = scanner.nextInt();

		if (novoChunk <= 0) {
			System.out.println("Valor inválido. O chunk deve ser maior que zero.");
			return;
		}

		this.redeService.alterarChunk(this.no.getRede(), novoChunk);
		System.out.println("\tTamanho de chunk alterado: " + novoChunk);
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
