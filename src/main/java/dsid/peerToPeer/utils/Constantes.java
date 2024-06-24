package dsid.peerToPeer.utils;

public class Constantes {
	
	// MENU
	public static final String MENSAGEM_CABECALHO_MENU = "Escolha o comando";
	public static final String OPCAO_LISTAR_VIZINHOS = "\t[0] Listar Vizinhos";
	public static final String OPCAO_ENVIAR_HELLO = "\t[1] Enviar HELLO";
	public static final String OPCAO_BUSCAR_CHAVE_FLOODING = "\t[2] Buscar por Chave (Flooding)";
	public static final String OPCAO_BUSCAR_CHAVE_RANDOM_WALK = "\t[3] Buscar por Chave (Random Walk)";
	public static final String OPCAO_BUSCAR_CHAVE_DEPTH_SEARCH = "\t[4] Buscar por Chave (Busca em Profundidade)";
	public static final String OPCAO_EXIBIR_ESTATISTICAS = "\t[5] Exibir Estatísticas";
	public static final String OPCAO_ALTERAR_TTL_PADRAO = "\t[6] Alterar Valor Padrão de TTL";
	public static final String OPCAO_SAIR = "\t[9] Sair";
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
            .toString();
	
	public static final int LISTAR_VIZINHOS = 0;
	public static final int ENVIAR_HELLO = 1;
	public static final int BUSCAR_CHAVE_FLOODING = 2;
	public static final int BUSCAR_CHAVE_RANDOM_WALK = 3;
	public static final int BUSCAR_CHAVE_DEPTH_SEARCH = 4;
	public static final int EXIBIR_ESTATISTICAS = 5;
	public static final int ALTERAR_TTL_PADRAO = 6;
	public static final int SAIR = 9;
	public static final String OPCAO_INVALIDA = "Opção inválida!";
	
	
	// TEMPO
	public static final int UM_SEGUNDO = 1000;
	
	
	// MENSAGEM
	public static final String HELLO = "HELLO";
	
	// VALORES INTEIROS (int)
	public static final int ZERO = 0;
	public static final int UM = 1;
	public static final int Dois = 2;	
	
	// REDE
	public static final String SOCKET_ENCERRADO = "Socket encerrado.";
	public static final String ERRO_ACEITAR_CONECAO = "Erro ao aceitar conexão:";
	public static final String SERVIDOR_INICIADO = "Servidor iniciado na porta ";
	public static final String ERRO_AO_INICIAR_SERVIDOR = "Erro ao iniciar o servidor: ";
	public static final String QUANTIDADE_VIZINHOS = "Quantidade de vizinhos: ";
	public static final String VIZINHO_JA_ADICIONADA = "Vizinho ja esta na tabela: ";
	public static final String VIZINHO_ADICIONADO = "Adicionando vizinho na tabela: ";
	
	// THREAD_COMUNICACAO
	public static final String ERRO_AO_COMUNICAR_COM_VIZINHO = "Erro ao comunicar com vizinho: ";
	
	// OUTROS
	public static final String VIZINHOS = "vizinhos";
}
