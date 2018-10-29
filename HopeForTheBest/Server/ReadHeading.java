package Server;

import CaptureTheFlagGame.Player;
import Message.Heading;

public class ReadHeading extends Thread {

	private Object message;
	private Player player;
	
	public ReadHeading(Object message, Player player) {
		this.message = message;
		this.player = player;
		setName("READING HEADING");
		start();
	}
	@Override
	public void run() {
		player.setHeading(((Heading)message).getHeading() );
	}
}
