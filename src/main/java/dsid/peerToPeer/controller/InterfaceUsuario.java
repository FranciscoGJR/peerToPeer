package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.ALTERAR_TTL_PADRAO;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_DEPTH_SEARCH;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_FLOODING;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_CHAVE_RANDOM_WALK;
import static dsid.peerToPeer.utils.Constantes.ENVIAR_HELLO;
import static dsid.peerToPeer.utils.Constantes.EXIBIR_ESTATISTICAS;
import static dsid.peerToPeer.utils.Constantes.LISTAR_VIZINHOS;
import static dsid.peerToPeer.utils.Constantes.MENSAGEM_CABECALHO_MENU;
import static dsid.peerToPeer.utils.Constantes.MENSAGEM_DIGITE_OPCAO;
import static dsid.peerToPeer.utils.Constantes.OPCAO_ALTERAR_TTL_PADRAO;
import static dsid.peerToPeer.utils.Constantes.OPCAO_BUSCAR_CHAVE_DEPTH_SEARCH;
import static dsid.peerToPeer.utils.Constantes.OPCAO_BUSCAR_CHAVE_FLOODING;
import static dsid.peerToPeer.utils.Constantes.OPCAO_BUSCAR_CHAVE_RANDOM_WALK;
import static dsid.peerToPeer.utils.Constantes.OPCAO_ENVIAR_HELLO;
import static dsid.peerToPeer.utils.Constantes.OPCAO_EXIBIR_ESTATISTICAS;
import static dsid.peerToPeer.utils.Constantes.OPCAO_LISTAR_VIZINHOS;
import static dsid.peerToPeer.utils.Constantes.OPCAO_SAIR;
import static dsid.peerToPeer.utils.Constantes.SAIR;

import java.util.Scanner;

import dsid.peerToPeer.No;

public class InterfaceUsuario {

	private Scanner scanner = new Scanner(System.in);
	
	private No no = new No();
	
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
        System.out.println(MENSAGEM_CABECALHO_MENU);
        System.out.println(OPCAO_LISTAR_VIZINHOS);
        System.out.println(OPCAO_ENVIAR_HELLO);
        System.out.println(OPCAO_BUSCAR_CHAVE_FLOODING);
        System.out.println(OPCAO_BUSCAR_CHAVE_RANDOM_WALK);
        System.out.println(OPCAO_BUSCAR_CHAVE_DEPTH_SEARCH);
        System.out.println(OPCAO_EXIBIR_ESTATISTICAS);
        System.out.println(OPCAO_ALTERAR_TTL_PADRAO);
        System.out.println(OPCAO_SAIR);
        System.out.print(MENSAGEM_DIGITE_OPCAO);
    }
	
	
	private void entrada() {
		decodificarMensagem();
	}
	
	private void decodificarMensagem() {
		
	}
}
