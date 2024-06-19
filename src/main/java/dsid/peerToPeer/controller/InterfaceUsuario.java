package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.*;

import java.util.Scanner;

public class InterfaceUsuario {

	private Scanner scanner = new Scanner(System.in);
	
    public void iniciar() {
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case LISTAR_VIZINHOS:
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
