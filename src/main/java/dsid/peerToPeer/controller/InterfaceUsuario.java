package dsid.peerToPeer.controller;

import static dsid.peerToPeer.utils.Constantes.ALTERAR_CHUNK;
import static dsid.peerToPeer.utils.Constantes.BUSCAR_ARQUIVOS;
import static dsid.peerToPeer.utils.Constantes.EXIBIR_ESTATISTICAS;
import static dsid.peerToPeer.utils.Constantes.LISTAR_ARQUIVOS_LOCAIS;
import static dsid.peerToPeer.utils.Constantes.LISTAR_PEERS;
import static dsid.peerToPeer.utils.Constantes.MENU_COMPLETO;
import static dsid.peerToPeer.utils.Constantes.OBTER_PEERS;
import static dsid.peerToPeer.utils.Constantes.OPCAO_INVALIDA;
import static dsid.peerToPeer.utils.Constantes.SAIR;

import java.util.Scanner;

import dsid.peerToPeer.No;
import dsid.peerToPeer.service.RedeService;

public class InterfaceUsuario {
	
	private RedeService redeService = new RedeService();

	private No no;

	private Scanner scanner = new Scanner(System.in);
	
	public InterfaceUsuario(No no) {
		this.no = no;
	}
	
    public void iniciar(No no) {
		this.redeService.iniciarConexao(no);
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
		this.redeService.listarVizinhos(no.getRede());
	}
	
	
    private void encerrarNo() {
	}


	public static void exibirMenu() {
        System.out.print(MENU_COMPLETO);
    }
	
}
