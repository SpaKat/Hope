package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ServerCommClient(GameManager gManager,Socket socket) {
		this.gManager = gManager;
		this.socket = socket;
		setName("SERVER Client");
		start();
	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			int changeStats = 1;
			int changeTeam = 1;
			while (running) {

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
						gManager.addPlayer(player, ((Teamid )message).getId());
					}
					break;
				case "Heading":
					if(changeStats <=0 && changeTeam<= 0) {
						player.setHeading(((Heading) message).getHeading());
					}
				default:
					break;
				}
				//System.out.println(player.getX() + "         "+ player.getY());
				try {Thread.sleep(1);} catch (InterruptedException e) {	}	
			}
		}catch (Exception e) {
				e.printStackTrace();
		}
		player.setDisconnect(true);
	}

	public void end() {
		running = false;
	}

}
