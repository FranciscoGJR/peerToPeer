package dsid.peer.util;

import dsid.peer.model.Peer;
import dsid.peer.network.Message;
import dsid.peer.network.MessageType;

import java.util.*;

public class MessageUtil {
	public static Message parse(String msgStr, Peer noLocal) {
		String[] tokens = msgStr.split(" ");
		String[] partesEndereco = tokens[0].split(":");
		Peer origem = new Peer(tokens[0]);
		MessageType tipo = MessageType.valueOf(tokens[2]);
		int clock = Integer.parseInt(tokens[1]);
		List<String> args = (tokens.length > 3) ? Arrays.asList(Arrays.copyOfRange(tokens, 3, tokens.length))
				: Collections.emptyList();
		return new Message(origem, noLocal, tipo, clock, args);
	}
}