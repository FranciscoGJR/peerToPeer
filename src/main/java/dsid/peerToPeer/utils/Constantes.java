package dsid.peerToPeer.utils;

public class Constantes {
	
	// MENU

	public static final String MENSAGEM_CABECALHO_MENU = "Escolha o comando";
	public static final String OPCAO_LISTAR_VIZINHOS = "\t0. Listar Vizinhos";
	public static final String OPCAO_ENVIAR_HELLO = "\t1. Enviar HELLO";
	public static final String OPCAO_BUSCAR_CHAVE_FLOODING = "\t2. Buscar por Chave (Flooding)";
	public static final String OPCAO_BUSCAR_CHAVE_RANDOM_WALK = "\t3. Buscar por Chave (Random Walk)";
	public static final String OPCAO_BUSCAR_CHAVE_DEPTH_SEARCH = "\t4. Buscar por Chave (Busca em Profundidade)";
	public static final String OPCAO_EXIBIR_ESTATISTICAS = "\t5. Exibir Estatísticas";
	public static final String OPCAO_ALTERAR_TTL_PADRAO = "\t6. Alterar Valor Padrão de TTL";
	public static final String OPCAO_SAIR = "\t9. Sair";
	public static final String MENSAGEM_DIGITE_OPCAO = "Digite a opção desejada: ";
    public static final String MENU_COMPLETO = new StringBuilder()
            .append(MENSAGEM_CABECALHO_MENU).append("\n")
            .append(OPCAO_LISTAR_VIZINHOS).append("\n")
            .append(OPCAO_ENVIAR_HELLO).append("\n")
            .append(OPCAO_BUSCAR_CHAVE_FLOODING).append("\n")
            .append(OPCAO_BUSCAR_CHAVE_RANDOM_WALK).append("\n")
            .append(OPCAO_BUSCAR_CHAVE_DEPTH_SEARCH).append("\n")
            .append(OPCAO_EXIBIR_ESTATISTICAS).append("\n")
            .append(OPCAO_ALTERAR_TTL_PADRAO).append("\n")
            .append(OPCAO_SAIR).append("\n")
            .append(MENSAGEM_DIGITE_OPCAO)
            .toString();
	
	public static final int LISTAR_VIZINHOS = 0;
	public static final int ENVIAR_HELLO = 1;
	public static final int BUSCAR_CHAVE_FLOODING = 2;
	public static final int BUSCAR_CHAVE_RANDOM_WALK = 3;
	public static final int BUSCAR_CHAVE_DEPTH_SEARCH = 4;
	public static final int EXIBIR_ESTATISTICAS = 5;
	public static final int ALTERAR_TTL_PADRAO = 6;
	public static final int SAIR = 9;
}
