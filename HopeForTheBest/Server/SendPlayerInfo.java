package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import CaptureTheFlagGame.Player;
import Message.PlayerInfo;

public class SendPlayerInfo extends Thread{

	private ObjectOutputStream out;
	private Player player;
	
	public SendPlayerInfo(ObjectOutputStream out, Player player) {
		this.out = out;
		this.player = player;
		start();
	}
	@Override
	public void run() {
		try {
			out.writeObject(new PlayerInfo(player));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
