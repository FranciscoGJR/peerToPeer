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

import java.util.Scanner;

import dsid.peerToPeer.model.No;
import dsid.peerToPeer.model.rede.Mensagem;
import dsid.peerToPeer.service.RedeService;
import dsid.peerToPeer.utils.Status;
import dsid.peerToPeer.utils.TipoMensagemEnum;

public class InterfaceUsuario {
	
	private RedeService redeService = new RedeService();

	private No no;

	private Scanner scanner = new Scanner(System.in);
	
	public InterfaceUsuario(No no) {
		this.no = no;
	}
	
    public void iniciar(No no) {
        int opcao;

        while(true) {
            exibirMenu();
            
            opcao = scanner.nextInt();
            scanner.nextLine();
             switch (opcao) {
                case LISTAR_PEERS:
                	listarVizinhos();
                	System.out.print("> ");
                	opcao = scanner.nextInt();
                	
                	if (opcao == 0) {
                		continue;
                	}
                	
                	enviarHello(opcao);
                    break;
                case OBTER_PEERS:
                    break;
                case LISTAR_ARQUIVOS_LOCAIS:
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
    	noDestinatario.getRede().setStatus(Status.ONLINE);
    	Mensagem mensagem = new Mensagem(this.no, noDestinatario, UM, TipoMensagemEnum.HELLO);
    	this.redeService.enviarMensagem(mensagem, this.no.getRede().getCaixaDeMensagens());
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
