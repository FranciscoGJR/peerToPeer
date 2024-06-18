package dsid.peerToPeer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dsid.peerToPeer.model.No;

public class NoServiceTest {
	
    private NoService noService;

    @Before
    public void setUp() {
        noService = new NoService();
    }

    @Test
    public void testAdicionarVizinho() {
        No no1 = new No("192.168.0.1", 8080);
        No no2 = new No("192.168.0.2", 8081);

        noService.adicionarVizinho(no1, no2);
        List<No> vizinhos = no1.getVizinhos();

        assertEquals(1, vizinhos.size());
        assertTrue(vizinhos.contains(no2));
    }
    
}
