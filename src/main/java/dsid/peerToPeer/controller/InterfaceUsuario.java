package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.*;

import java.util.Scanner;

import dsid.peerToPeer.No;

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
                case LISTAR_PEERS:
                	listarVizinhos();
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
    

	private void listarVizinhos() {
    	no.getRedeService().listarVizinhos();
	}
	
	
    private void encerrarNo() {
		no.getRedeService().pararEscuta();
	}


	public static void exibirMenu() {
        System.out.print(MENU_COMPLETO);
    }
	
}
