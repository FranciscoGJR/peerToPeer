package dsid.peerToPeer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class EstatisticasDownload {

    // Chave: "tamChunk|numPeers|tamArquivo"
    private Map<String, List<Double>> temposDownload = new HashMap();

    public void registrarDownload(int tamChunk, int numPeers, long tamArquivo, double tempoSegundos) {
        String chave = gerarChave(tamChunk, numPeers, tamArquivo);
        List<Double> tempos = temposDownload.getOrDefault(chave, new ArrayList());
        tempos.add(tempoSegundos);
        temposDownload.put(chave, tempos);
    }

    private String gerarChave(int tamChunk, int numPeers, long tamArquivo) {
        return tamChunk + "|" + numPeers + "|" + tamArquivo;
    }

    public List<EstatisticaItem> getEstatisticas() {
        List<EstatisticaItem> estatisticas = new ArrayList();

        for (Map.Entry<String, List<Double>> entry : temposDownload.entrySet()) {
            String[] partes = entry.getKey().split("\\|");
            int tamChunk = Integer.parseInt(partes[0]);
            int numPeers = Integer.parseInt(partes[1]);
            long tamArquivo = Long.parseLong(partes[2]);
            List<Double> tempos = entry.getValue();

            double media = calcularMedia(tempos);
            double desvioPadrao = calcularDesvioPadrao(tempos, media);

            estatisticas.add(new EstatisticaItem(tamChunk, numPeers, tamArquivo, tempos.size(), media, desvioPadrao));
        }

        return estatisticas;
    }

    private double calcularMedia(List<Double> valores) {
        double soma = 0;
        for (Double valor : valores) {
            soma += valor;
        }
        return soma / valores.size();
    }

    private double calcularDesvioPadrao(List<Double> valores, double media) {
        if (valores.size() <= 1) {
            return 0;
        }

        double somaQuadradosDiferencas = 0;
        for (Double valor : valores) {
            double diferenca = valor - media;
            somaQuadradosDiferencas += diferenca * diferenca;
        }

        return Math.sqrt(somaQuadradosDiferencas / (valores.size() - 1));
    }

    @Data
    public static class EstatisticaItem {
        private final int tamChunk;
        private final int numPeers;
        private final long tamArquivo;
        private final int numAmostras;
        private final double tempoMedio;
        private final double desvioPadrao;

        public EstatisticaItem(int tamChunk, int numPeers, long tamArquivo, int numAmostras, double tempoMedio, double desvioPadrao) {
            this.tamChunk = tamChunk;
            this.numPeers = numPeers;
            this.tamArquivo = tamArquivo;
            this.numAmostras = numAmostras;
            this.tempoMedio = tempoMedio;
            this.desvioPadrao = desvioPadrao;
        }
    }
}