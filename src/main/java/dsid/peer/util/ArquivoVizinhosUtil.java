package dsid.peer.util;

import dsid.peer.model.Peer;

import java.io.*;
import java.util.*;

public class ArquivoVizinhosUtil {
	public static List<Peer> lerVizinhos(String nomeArquivo) {
		List<Peer> nos = new ArrayList();
		try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				nos.add(new Peer(linha.trim()));
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo de vizinhos: " + e.getMessage());
		}
		return nos;
	}
}