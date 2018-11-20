package Server;

import java.io.ObjectOutputStream;

import CaptureTheFlagGame.GameManager;
import Message.GameInfo;

public class SendGameInfo extends Thread {

	private ObjectOutputStream out;
	private GameManager gManager;
	private boolean running = true;
	private int queue = 0;
	public SendGameInfo(ObjectOutputStream out, GameManager gManager) {
		this.out = out;
		this.gManager = gManager;
		setName("SEND GameInfo");
		start();
	}
	@Override
	public void run() {
		while(running) {
			if(queue>0) {
				try {
					out.writeObject(new GameInfo(gManager.getGame()));
					out.flush();
				}catch (Exception e) {
					// TODO: handle exception
				}
				queue--;
			}
			try {Thread.sleep(10000);}catch (Exception e) {}
		//	try {wait();} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	public void send() {
		queue++;
	}
	public void end() {
		running = false;
		interrupt();
	}
}
