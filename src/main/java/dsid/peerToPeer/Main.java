package dsid.peerToPeer;
import static dsid.peerToPeer.utils.Constantes.DOIS;
import static dsid.peerToPeer.utils.Constantes.ERRO_NA_LEITURA_DO_DIRETORIO;
import static dsid.peerToPeer.utils.Constantes.UM;
import static dsid.peerToPeer.utils.Constantes.ZERO;
import static dsid.peerToPeer.utils.MensagemUtil.getEndereco;
import static dsid.peerToPeer.utils.MensagemUtil.getPorta;

import java.io.File;
import java.util.List;

import dsid.peerToPeer.controller.Console;
import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.NoUtil;
public class Main {
	
	static NoUtil noUtil = new NoUtil();

    public static void main(String[] args) {
    	
    	String argumento0 = (args[ZERO] != null) ? args[ZERO] : null;
    	String argumento1 = (args[UM] != null) ? args[UM] : null;
    	String argumento2 = (args[DOIS] != null) ? args[DOIS] : null;
    	
    	String endereco = getEndereco(argumento0);
    	Integer porta = getPorta(argumento0);
    	String arquivoVizinhos = argumento1;
    	String diretorioCompartilhado = argumento2;
    	
    	List<No> vizinhos = noUtil.decoderListaVizinhos(arquivoVizinhos);
    	
    	No no = new No(endereco, porta, vizinhos, diretorioCompartilhado);
    	no.getRede().setNoPrincipal(no);

        File diretorio = new File(diretorioCompartilhado);
        if (!diretorio.exists() || !diretorio.isDirectory()) {
            System.err.println(ERRO_NA_LEITURA_DO_DIRETORIO);
            return;
        }
        
    	Console interfaceUsuario = new Console(no, diretorioCompartilhado);
    	interfaceUsuario.iniciar(no);
    }	

}
