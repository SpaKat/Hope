package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import Client.Client;
import javafx.application.Platform;

public class CTFClientGameUpdate extends CTFGameUpdate {
	private CTFClientGame client;
	
	public CTFClientGameUpdate(CTFGame ctfGame, GameManager gManager, Client client) {
		this.client = new CTFClientGame(client,gManager);
		this.setCtfGame(ctfGame);
		this.setgManager(gManager);
		setName("CTFGame Update Thread");
		
		this.start();
	}
	@Override
	public void run() {
		while(isRunning()&&client.isAlive()) {
			if(!getgManager().isWinner()) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						getCtfGame().update();
						getCtfGame().updateClient();
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
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void end() {
		super.end();
		client.end();
	}
}
