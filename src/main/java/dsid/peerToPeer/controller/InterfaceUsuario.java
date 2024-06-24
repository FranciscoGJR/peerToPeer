package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.ALTERAR_TTL_PADRAO;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_DEPTH_SEARCH;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_FLOODING;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_RANDOM_WALK;
import static dsid.peerToPeer.utils.Constantes.ENVIAR_HELLO;
import static dsid.peerToPeer.utils.Constantes.EXIBIR_ESTATISTICAS;
import static dsid.peerToPeer.utils.Constantes.HELLO;
import static dsid.peerToPeer.utils.Constantes.LISTAR_VIZINHOS;
import static dsid.peerToPeer.utils.Constantes.MENU_COMPLETO;
import static dsid.peerToPeer.utils.Constantes.OPCAO_INVALIDA;
import static dsid.peerToPeer.utils.Constantes.SAIR;
import static dsid.peerToPeer.utils.Constantes.UM;

import java.util.Scanner;

import dsid.peerToPeer.No;
import dsid.peerToPeer.rede.Mensagem;
import dsid.peerToPeer.utils.TipoMensagemEnum;

public class InterfaceUsuario {
	
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
                case LISTAR_VIZINHOS:
                	this.listarVizinhos();
                    break;
                case ENVIAR_HELLO:
                	this.enviarHello();
                    break;
                case BUSCAR_CHAVE_FLOODING:
                    break;
                case BUSCAR_CHAVE_RANDOM_WALK:
                    break;
                case BUSCAR_CHAVE_DEPTH_SEARCH:
                    break;
                case EXIBIR_ESTATISTICAS:
                    break;
                case ALTERAR_TTL_PADRAO:
                    break;
                case SAIR:
                	encerrarNo();
                    return;
                default:
                    System.out.println(OPCAO_INVALIDA);
            }
        }
    }
    
    
	private void enviarHello() {
    	listarVizinhos();
    	int escolha = scanner.nextInt();
    	No vizinho = no.getRede().getVizinhos().get(escolha);
    	Mensagem mensagem = new Mensagem(no, vizinho, UM, TipoMensagemEnum.HELLO);
    	no.getRede().enviarMensagem(mensagem);
	}

	
	private void listarVizinhos() {
    	no.getRede().listarVizinhos();
	}
	
	
    private void encerrarNo() {
		no.getRede().pararEscuta();
	}


	public static void exibirMenu() {
        System.out.print(MENU_COMPLETO);
    }
	
}
