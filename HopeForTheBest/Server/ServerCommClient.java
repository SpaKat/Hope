package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Message.Heading;
import Message.StatsMessage;
import Message.Teamid;

public class ServerCommClient extends Thread {

	private GameManager gManager;
	private boolean running = true;
	private Socket socket;

	private Player player;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public ServerCommClient(GameManager gManager,Socket socket) {
		this.gManager = gManager;
		this.socket = socket;
		setName("SERVER Client");
		this.start();
	}

	@Override
	public void run() {


		int changeStats = 1;
		int changeTeam = 1;
		try {
			InputStream i = socket.getInputStream();
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(i);
			while (running) {

				try {
					Object message = in.readObject();
					switch (message.getClass().getSimpleName()) {
					case "StatsMessage":
						if (changeStats-- >0) {
							Statistics stats = new Statistics(((StatsMessage) message).getAttack(),
									((StatsMessage) message).getDefense(), 
									((StatsMessage) message).getHealth(), 
									((StatsMessage) message).getMovespeed());
							player = new Player(stats);
						}
						break;
					case "Teamid":
						if (changeStats <=0 && changeTeam-- > 0 ) {
							if(!gManager.addPlayer(player, ((Teamid )message).getId())) {
								socket.close();
							}
						}
						break;
					case "Heading":
						if(changeStats <=0 && changeTeam<= 0) {
							player.setHeading(((Heading) message).getHeading());
						}
						break;
					case "Fire":
						player.fireBullet();
						//System.out.println("fire");
					default:
						break;
					}

				//	System.out.println(player.getX() + "___________" + player.getY());
				} catch (SocketException e) {
					running = false;
				}
				try {Thread.sleep(1);} catch (InterruptedException e) {	}	
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("failed");
		}
		System.out.println("SERVER CLIENT ENDED");
		try {
			player.setDisconnect(true);
		}catch (Exception e) {
			e.printStackTrace();	
		}

	}

	public void end() {
		running = false;
	}

}
