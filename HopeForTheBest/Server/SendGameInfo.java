package Server;

import java.io.ObjectOutputStream;

import CaptureTheFlagGame.GameManager;
import Message.GameInfo;

public class SendGameInfo extends Thread {

	private ObjectOutputStream out;
	private GameManager gManager;
	public SendGameInfo(ObjectOutputStream out, GameManager gManager) {
		this.out = out;
		this.gManager = gManager;
		setName("SEND GameInfo");
		if (gManager.canSend()) {
		start();
		}
	}
	@Override
	public void run() {
		try {
		out.writeObject(new GameInfo(gManager.getGame()));
		out.flush();
		gManager.resetSend();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
