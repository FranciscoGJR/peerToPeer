package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.ALTERAR_CHUNK;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_ARQUIVOS;
import static dsid.peerToPeer.utils.Constantes.EXIBIR_ESTATISTICAS;
import static dsid.peerToPeer.utils.Constantes.LISTAR_ARQUIVOS_LOCAIS;
import static dsid.peerToPeer.utils.Constantes.LISTAR_PEERS;
import static dsid.peerToPeer.utils.Constantes.LISTA_DE_PEERS;
import static dsid.peerToPeer.utils.Constantes.MENU_COMPLETO;
import static dsid.peerToPeer.utils.Constantes.OBTER_PEERS;
import static dsid.peerToPeer.utils.Constantes.OPCAO_INVALIDA;
import static dsid.peerToPeer.utils.Constantes.OPCAO_VOLTAR_MENU;
import static dsid.peerToPeer.utils.Constantes.SAIR;
import static dsid.peerToPeer.utils.Constantes.UM;

import java.io.File;
import java.util.Scanner;

import dsid.peerToPeer.utils.ThreadComunicacaoUtil;
import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.service.RedeService;
import dsid.peerToPeer.utils.Status;
import dsid.peerToPeer.utils.TipoMensagemEnum;

public class InterfaceUsuario {
	
	private RedeService redeService = new RedeService();

	private No no;

	private Scanner scanner = new Scanner(System.in);
	
	private String diretorioCompartilhado;
	
	public InterfaceUsuario(No no, String diretorioCompartilhado) {
		this.no = no;
		this.diretorioCompartilhado = diretorioCompartilhado;
	}
	
    public void iniciar(No no) {
    	ThreadComunicacaoUtil.esperaEmSegundos(UM);
        int opcao;

        while(true) {
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
    	No noDestinatario = no.getRede().getVizinhos().get(numeroVizinho - 1);
    	Mensagem mensagem = new Mensagem(this.no, noDestinatario, UM, TipoMensagemEnum.HELLO);

    	boolean mensagemEnviadaComSucesso = this.redeService.enviarMensagem(mensagem, this.no.getRede().getCaixaDeMensagens());
    	if (mensagemEnviadaComSucesso) {
    		return;
    	}
    	
    	noDestinatario.getRede().setStatus(Status.OFFLINE);
    }


    private void enviarGetPeers() {
    	for (No vizinho : this.no.getRede().getVizinhos()) {
    		No noDestinatario = vizinho;
    		Mensagem mensagem = new Mensagem(this.no, noDestinatario, UM, TipoMensagemEnum.GET_PEERS);

    		boolean mensagemEnviadaComSucesso = this.redeService.enviarMensagem(mensagem, this.no.getRede().getCaixaDeMensagens());
    		if (mensagemEnviadaComSucesso) {
    			noDestinatario.getRede().setStatus(Status.ONLINE);
    			return;
    		}
    	
    		noDestinatario.getRede().setStatus(Status.OFFLINE);
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
	
}
