package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.application.Platform;

public class CTFGameUpdate extends Thread {

	private CTFGame ctfGame;
	private boolean running = true;
	private GameManager gManager;

	public CTFGameUpdate() {
		setName("CTFGame Update Thread");
	}

	public CTFGameUpdate(CTFGame ctfGame, GameManager gManager) {
		this();
		this.setCtfGame(ctfGame);
		this.setgManager(gManager);
		this.start();
	}



	@Override
	public void run() {
		while(isRunning()) {
			if(!getgManager().isWinner()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						getCtfGame().update();
					}
				});
			}else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						getCtfGame().showWinner();
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
		setRunning(false);
	}



	public CTFGame getCtfGame() {
		return ctfGame;
	}



	public void setCtfGame(CTFGame ctfGame) {
		this.ctfGame = ctfGame;
	}



	public GameManager getgManager() {
		return gManager;
	}



	public void setgManager(GameManager gManager) {
		this.gManager = gManager;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

}
