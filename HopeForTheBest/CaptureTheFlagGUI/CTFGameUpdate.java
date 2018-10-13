package CaptureTheFlagGUI;

import javafx.application.Platform;

public class CTFGameUpdate extends Thread {

	private CTFGame ctfGame;
	private boolean running = true;
	public CTFGameUpdate(CTFGame ctfGame) {
		this.ctfGame = ctfGame;
		setName("CTFGame Update Thread");
		this.start();
	}
	
	@Override
	public void run() {
		while(running) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					ctfGame.update();
				}
			});
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
