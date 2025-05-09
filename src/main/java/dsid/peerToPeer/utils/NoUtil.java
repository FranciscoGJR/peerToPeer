package dsid.peerToPeer.utils;

import static dsid.peerToPeer.utils.Constantes.NULL;
import static dsid.peerToPeer.utils.Constantes.UM;
import static dsid.peerToPeer.utils.Constantes.ZERO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dsid.peerToPeer.model.No;

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
                No no = new No(partes[ZERO], Integer.parseInt(partes[UM]), ZERO);
                System.out.println("Adicionando novo peer " + no.getRede().getEnderecoIP() + ":" + no.getRede().getPorta() + " status " + no.getRede().getStatus());
                listaVizinhos.add(no);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return listaVizinhos;
    }
}
