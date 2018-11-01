package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.application.Platform;

public class CTFGameUpdate extends Thread {

	private CTFGame ctfGame;
	private boolean running = true;
	private GameManager gManager;
	public CTFGameUpdate(CTFGame ctfGame, GameManager gManager) {
		this.ctfGame = ctfGame;
		this.gManager = gManager;
		setName("CTFGame Update Thread");
		this.start();
	}
	
	@Override
	public void run() {
		while(running) {
			if(!gManager.isWinner()) {
				Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ctfGame.update();
				}
			});
		}else {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ctfGame.showWinner();
				}
			});
			end();
		}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void end() {
		running = false;
	}
	
}
