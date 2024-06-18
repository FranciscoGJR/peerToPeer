package dsid.peerToPeer.service;

import java.util.List;

import dsid.peerToPeer.model.No;

public class NoService {
	
	public void listarVizinhos(No no) {
		List<No> listaDeVizinhos = no.getVizinhos();
		
		System.out.printf("Ha %d vizinhos na tabela:\n", listaDeVizinhos.size());
		
		int iterador = 0;
        for (No vizinho: listaDeVizinhos) {
        	System.out.printf("\t[%d] %s %s\n", iterador, no.getEnderecoIP(), no.getPorta());
            iterador++;
        }
	}
	
	public void adicionarVizinho(No no, No noVizinho) {
	    List<No> listaDeVizinhos = no.getVizinhos();
	    listaDeVizinhos.add(noVizinho);
	}
}
