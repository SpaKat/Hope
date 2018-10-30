package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Message.GameInfo;
import Message.Heading;
import Message.NewPlayer;

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


		int newPlayer = 1;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			while (running) {
				try {
					Object message = in.readObject();
					switch (message.getClass().getSimpleName()) {
					case "NewPlayer":
						if (newPlayer >0) {
							Statistics stats = new Statistics(((NewPlayer) message).getAttack(),
									((NewPlayer) message).getDefense(), 
									((NewPlayer) message).getHealth(), 
									((NewPlayer) message).getMovespeed());
							if (stats.getRateing()) {
								player = new Player(stats);
								if(!gManager.addPlayer(player, ((NewPlayer )message).getId())) {
									socket.close();
								}
							}else {
								socket.close();
							}
						}
						newPlayer--;
						break;
					case "Heading":
						new ReadHeading(message,player);
						break;
					case "Fire":
						new ReadFireBullet(player);
						break;
					case "ReQuestGameInfo":
						new SendGameInfo(out,gManager);
						break;
					default:
						
						break;
					}

				} catch (Exception e) {
					running = false;
				}
				try {Thread.sleep(0, 1);} catch (InterruptedException e) {	}	
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("SERVER CLIENT ENDED");
		try {
			player.setDisconnect(true);
			System.out.println("SERVER CLIENT PLAYER ENDED");

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void end() {
		running = false;
	}

}
