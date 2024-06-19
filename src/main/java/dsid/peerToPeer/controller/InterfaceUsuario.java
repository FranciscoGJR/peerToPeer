package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.ALTERAR_TTL_PADRAO;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_DEPTH_SEARCH;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_FLOODING;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_RANDOM_WALK;
import static dsid.peerToPeer.utils.Constantes.ENVIAR_HELLO;
import static dsid.peerToPeer.utils.Constantes.EXIBIR_ESTATISTICAS;
import static dsid.peerToPeer.utils.Constantes.LISTAR_VIZINHOS;
import static dsid.peerToPeer.utils.Constantes.MENU_COMPLETO;
import static dsid.peerToPeer.utils.Constantes.SAIR;

import java.util.Scanner;

import dsid.peerToPeer.No;

public class InterfaceUsuario {

	private Scanner scanner = new Scanner(System.in);
	
    public void iniciar(No no) {
    	
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case LISTAR_VIZINHOS:
                	no.getRede().listarVizinhos();
                    break;
                case ENVIAR_HELLO:
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
                    System.out.println("Saindo do menu...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);
    }
    
    
    private void exibirMenu() {
        System.out.print(MENU_COMPLETO);
    }
	
}
