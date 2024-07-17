package dsid.peerToPeer;

import static dsid.peerToPeer.utils.Constantes.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dsid.peerToPeer.controller.InterfaceUsuario;

public class Main {
	
    public static void main(String[] args) {
    	
    	String argumento0 = args[0];
    	String argumento1 = args[1];
    	String argumento2 = args[2];
    	
    	System.out.println(argumento0);
    	System.out.println(argumento1);
    	
    	String endereco = getEndereco(argumento0);
    	Integer porta = getPorta(argumento0);
    	String arquivoVizinhos = argumento1;
    	String arquivoListaChaveValor = argumento2;
    	
    	List<No> vizinhos = decoderListaVizinhos(arquivoVizinhos);
    	HashMap<String, Integer> listaChaveValor = decoderListaChaveValor(arquivoListaChaveValor);
    	
    	No no = new No(endereco, porta, vizinhos, listaChaveValor);
    	
    	InterfaceUsuario interfaceUsuario = new InterfaceUsuario(no);
    	interfaceUsuario.iniciar(no);
    }	


	private static String getEndereco(String argumentoEnderecoPorta) {
    	String[] partes = argumentoEnderecoPorta.split(":");
    	return partes[ZERO];
    }
    
    
    private static Integer getPorta(String argumentoEnderecoPorta) {
    	String[] partes = argumentoEnderecoPorta.split(":");
    	return Integer.parseInt(partes[UM]);
    }
    
    
    private static List<No> decoderListaVizinhos(String arquivoVizinhos) {
    	List<No> listaVizinhos = new ArrayList<>();
    	if (arquivoVizinhos.equalsIgnoreCase(NULL)) {
    		return listaVizinhos;
    	}
    	
    	String nomeArquivo = VIZINHOS;

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(":");
                No no = new No(partes[ZERO], Integer.parseInt(partes[UM]));
                listaVizinhos.add(no);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    	return listaVizinhos;
    }
    
	private static HashMap<String, Integer> decoderListaChaveValor(String arquivoListaChaveValor) {
        HashMap<String, Integer> listaChaveValor = new HashMap<>();
        if (arquivoListaChaveValor.equals(NULL)) {
        	return listaChaveValor;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoListaChaveValor))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(ESPACO_EM_BRANCO);
                listaChaveValor.put(partes[ZERO], Integer.parseInt(partes[UM]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaChaveValor;
	}
    
}
