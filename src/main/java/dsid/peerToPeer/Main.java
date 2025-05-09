package dsid.peer;

import java.io.File;
import java.util.List;

<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/Main.java
import dsid.peerToPeer.controller.InterfaceUsuario;
import dsid.peerToPeer.model.No;
import dsid.peerToPeer.utils.NoUtil;
=======
import dsid.peer.model.Peer;
import dsid.peer.util.PeerFileUtil;

>>>>>>> Stashed changes:src/main/java/dsid/peer/Main.java
public class Main {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: java Main <address:port> <neighbors.txt> <shared_directory>");
			return;
		}

		String addressPort = args[0];
		String neighborsFile = args[1];
		String sharedDir = args[2];

<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/Main.java
    public static void main(String[] args) {
    	
    	String argumento0 = (args[ZERO] != null) ? args[ZERO] : null;
    	String argumento1 = (args[UM] != null) ? args[UM] : null;
    	String argumento2 = (args[DOIS] != null) ? args[DOIS] : null;
    	
    	String endereco = getEndereco(argumento0);
    	Integer porta = getPorta(argumento0);
    	String arquivoVizinhos = argumento1;
    	String diretorioCompartilhado = argumento2;
    	
    	List<No> vizinhos = noUtil.decoderListaVizinhos(arquivoVizinhos);
    	
    	No no = new No(endereco, porta, vizinhos);

        File diretorio = new File(diretorioCompartilhado);
        if (!diretorio.exists() || !diretorio.isDirectory()) {
            System.err.println(ERRO_NA_LEITURA_DO_DIRETORIO);
            return;
        }
        
    	InterfaceUsuario interfaceUsuario = new InterfaceUsuario(no, diretorioCompartilhado);
    	interfaceUsuario.iniciar(no);
    }	
=======
		List<Peer> neighbors = PeerFileUtil.parseNeighbors(neighborsFile);
		Peer localPeer = new Peer(addressPort, neighbors);
		File sharedDirectory = new File(sharedDir);

		if (!sharedDirectory.exists() || !sharedDirectory.isDirectory()) {
        	System.err.println("Error: Shared directory does not exist or is not a valid directory.");
        return;
		}
>>>>>>> Stashed changes:src/main/java/dsid/peer/Main.java

		PeerService peerService = new PeerService(localPeer, sharedDirectory);
		PeerConsoleUI ui = new PeerConsoleUI(peerService);
		ui.start();
	}
}
