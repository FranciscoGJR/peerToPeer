package dsid.peerToPeer.utils;

public class Constantes {
	
	// INTERFACE
	public static final String OPCAO_VOLTAR_MENU = "\t[0] voltar para o menu anterior";
	public static final String MENSAGEM_CABECALHO_MENU = "\nEscolha o comando";
	public static final String OPCAO_LISTAR_PEERS = "\t[1] Listar peers";
	public static final String OPCAO_OBTER_PEERS = "\t[2] Obter peers";
	public static final String OPCAO_LISTAR_ARQUIVOS_LOCAIS = "\t[3] Listar arquivos locais";
	public static final String OPCAO_BUSCAR_ARQUIVOS = "\t[4] Buscar arquivos";
	public static final String OPCAO_EXIBIR_ESTATISTICAS = "\t[5] Exibir Estatísticas";
	public static final String OPCAO_ALTERAR_CHUNK = "\t[6] Alterar tamanho de chunk";
	public static final String OPCAO_SAIR = "\t[9] Sair";

	
    public static final String MENU_COMPLETO = new StringBuilder()
            .append(MENSAGEM_CABECALHO_MENU).append("\n")
            .append(OPCAO_LISTAR_PEERS).append("\n")
            .append(OPCAO_OBTER_PEERS).append("\n")
            .append(OPCAO_LISTAR_ARQUIVOS_LOCAIS).append("\n")
            .append(OPCAO_BUSCAR_ARQUIVOS).append("\n")
            .append(OPCAO_EXIBIR_ESTATISTICAS).append("\n")
            .append(OPCAO_ALTERAR_CHUNK).append("\n")
            .append(OPCAO_SAIR).append("\n")
            .append("> ")
            .toString();
	
	public static final int LISTAR_PEERS = 1;
	public static final int OBTER_PEERS = 2;
	public static final int LISTAR_ARQUIVOS_LOCAIS = 3;
	public static final int BUSCAR_ARQUIVOS = 4;
	public static final int EXIBIR_ESTATISTICAS = 5;
	public static final int ALTERAR_CHUNK = 6;
	public static final int SAIR = 9;
	public static final String OPCAO_INVALIDA = "Opção inválida!";
	
	public static final String DIGITAR_CHAVE_DE_BUSCA = "Digite a chave a ser buscada: ";
	public static final String CHAVE = "chave: ";
	public static final String VALOR = "valor: ";	
	public static final String ERRO_NA_LEITURA_DO_DIRETORIO = "Erro: O diretório compartilhado especificado não existe ou não é um diretório válido.";	
	
	
	// TEMPO
	public static final int UM_SEGUNDO = 1000;
	
	
	// MENSAGEM
	public static final String HELLO = "HELLO";
	public static final String GET_PEERS = "GET_PEERS";
	public static final String PEER_LIST = "PEER_LIST";
	public static final String ENCAMINHANDO_MENSAGEM = "\n\tEncaminhando mensagem ";
	public static final String PARA = " para ";
	
	
	// VALORES INTEIROS (int)
	public static final int ZERO = 0;
	public static final int UM = 1;
	public static final int DOIS = 2;	
	public static final int MIL = 200;
	
	// REDE
	public static final String LISTA_DE_PEERS = "\nLista de peers:";
	public static final String SOCKET_ENCERRADO = "Socket encerrado.";
	public static final String ERRO_ACEITAR_CONECAO = "Erro ao aceitar conexão:";
	public static final String SERVIDOR_INICIADO = "Servidor iniciado na porta ";
	public static final String ERRO_AO_INICIAR_SERVIDOR = "Erro ao iniciar o servidor: ";
	public static final String QUANTIDADE_VIZINHOS = "Quantidade de vizinhos: ";
	public static final String VIZINHO_ADICIONADO = "Adicionando vizinho na tabela: ";
	
	// THREAD_COMUNICACAO
	public static final String ERRO_AO_COMUNICAR_COM_VIZINHO = "Erro ao comunicar com vizinho: ";
	public static final String MENSAGEM_RECEBIDA = "\n\n\tMensagem recebida: ";
	
	// OUTROS
	public static final String NULL = "null";
	public static final String VIZINHOS = "vizinhos";
	public static final String ESPACO_EM_BRANCO = " ";
	public static final String STRING_VAZIA = "";
	
}
