package dsid.peer;

import java.io.File;
import java.util.List;

import dsid.peer.model.Peer;
import dsid.peer.util.PeerFileUtil;

public class Main {
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Usage: java Main <address:port> <neighbors.txt> <shared_directory>");
			return;
		}

		String addressPort = args[0];
		String neighborsFile = args[1];
		String sharedDir = args[2];

		List<Peer> neighbors = PeerFileUtil.parseNeighbors(neighborsFile);
		Peer localPeer = new Peer(addressPort, neighbors);
		File sharedDirectory = new File(sharedDir);

		if (!sharedDirectory.exists() || !sharedDirectory.isDirectory()) {
        	System.err.println("Error: Shared directory does not exist or is not a valid directory.");
        return;
		}

		PeerService peerService = new PeerService(localPeer, sharedDirectory);
		PeerConsoleUI ui = new PeerConsoleUI(peerService);
		ui.start();
	}
}
