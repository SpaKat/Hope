package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import CaptureTheFlagGame.Player;
import Message.PlayerInfo;

public class SendPlayerInfo extends Thread{

	private ObjectOutputStream out;
	private Player player;
	private boolean running = true;
	private int queue = 0;
	public SendPlayerInfo(ObjectOutputStream out, Player player) {
		this.out = out;
		this.player = player;
		setName("Send PlayerINFO");
		start();
	}
	@Override
	public void run() {
		while(running) {
			if(queue>0) {
				try {
					out.writeObject(new PlayerInfo(player));
					out.flush();
				} catch (IOException e) {
				//	e.printStackTrace();
				}
				queue--;
			}
			try {Thread.sleep(100007);}catch (Exception e) {}
		//	try {wait();} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	public void send() {
		queue++;
	}
	public void end() {
		running = false;
	}
}
