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
import Message.GameInfo;
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
							if (stats.getRateing()) {
								player = new Player(stats);
							}else {
								socket.close();
							}
							
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
					case "ReQuestGameInfo":
						out.writeObject(new GameInfo(gManager.getGame()));
						out.flush();
					default:
						break;
					}

				} catch (Exception e) {
					running = false;
				}
				try {Thread.sleep(1);} catch (InterruptedException e) {	}	
			}
		} catch (Exception e1) {
			
		}
		System.out.println("SERVER CLIENT ENDED");
		try {
			player.setDisconnect(true);
			System.out.println("SERVER CLIENT PLAYER ENDED");

		}catch (Exception e) {
		}

	}

	public void end() {
		running = false;
	}

}
