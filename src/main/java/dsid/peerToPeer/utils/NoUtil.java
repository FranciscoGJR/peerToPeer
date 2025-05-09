package dsid.peer.utils;

import static dsid.peer.utils.Constantes.NULL;
import static dsid.peer.utils.Constantes.UM;
import static dsid.peer.utils.Constantes.ZERO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

<<<<<<< Updated upstream:src/main/java/dsid/peerToPeer/utils/NoUtil.java
import dsid.peerToPeer.model.No;
=======
import dsid.peer.model.Peer;
>>>>>>> Stashed changes:src/main/java/dsid/peer/utils/NoUtil.java

public class NoUtil {

    public List<No> decoderListaVizinhos(String arquivoVizinhos) {
    	List<No> listaVizinhos = new ArrayList<>();
    	if (arquivoVizinhos.equalsIgnoreCase(NULL)) {
    		return listaVizinhos;
    	}

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoVizinhos))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(":");
                No no = new No(partes[ZERO], Integer.parseInt(partes[UM]));
                System.out.println("Adicionando novo peer " + no.getRede().getEnderecoIP() + ":" + no.getRede().getPorta() + " status " + no.getRede().getStatus());
                listaVizinhos.add(no);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return listaVizinhos;
    }
}
