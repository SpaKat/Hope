package Server;

import CaptureTheFlagGame.GameManager;

public class ServerGame extends Thread {
	private GameManager gManager;
	private boolean running = true;
	public ServerGame(GameManager gManager) {
		this.gManager = gManager;
		this.start();
	}
	@Override
	public void run() {
		while (running) {
			gManager.OneTurn();
			try {Thread.sleep(50);} catch (InterruptedException e) {}
		}

	}
	public void end() {
		running  = false;
	}
	

}
