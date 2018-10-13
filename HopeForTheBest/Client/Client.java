package Client;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import CaptureTheFlagGame.Flag;
import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;
import Message.GameInfo;
import Message.PlayerInfo;
import Message.ReQuestGameInfo;

public class Client extends Thread {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private int port;
	private String ip;
	private boolean running =true;
	private GameInfo gameInfo; 
	private GameManager gameManager;
	public Client(String ip,int port) {
		this.ip = ip;
		this.port = port;
		start();
	}

	public Client(GameManager gameManager, String text, int parseInt) {
		this(text, parseInt);
		this.gameManager = gameManager;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(ip, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			while (running) {
				read();
				try{Thread.sleep(1);}catch(Exception d ) {}
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	private void read() throws Exception{
		Object o = in.readObject();
		switch (o.getClass().getSimpleName()) {
		case "GameInfo":
			gameInfo = (GameInfo) o;
			//updateGameManger();
			break;
		default:
			break;
		}
	}
	/*private void updateGameManger() {
		
		gameManager.setheight(gameInfo.getGameboard().getY());
		gameManager.setwidth(gameInfo.getGameboard().getX());
		
		gameInfo.getTeam().forEach(teamInfo ->{
			Team team = gameManager.getGame().getTeams().get(teamInfo.getId());
			
			team.getFlag().setRadius(teamInfo.getFlag().getRadius());
			team.getFlag().setX(teamInfo.getFlag().getX());
			team.getFlag().setY(teamInfo.getFlag().getY());
			team.getFlag().setColor(teamInfo.getFlag().getColor());
			
			team.getHomeBase().setColor(teamInfo.getHomeBase().getColor());
			team.getHomeBase().setRadius(teamInfo.getHomeBase().getRadius());
			team.getHomeBase().setX(teamInfo.getHomeBase().getX());
			team.getHomeBase().setY(teamInfo.getHomeBase().getY());
			
			teamInfo.forEach(playerInfo ->{
				Statistics stats = new Statistics(playerInfo.getStats().getAttack(),
						playerInfo.getStats().getDefense(), 
						playerInfo.getStats().getHealth(),
						playerInfo.getStats().getMovespeed());
				stats.setHealth(playerInfo.getStats().getHealth());
				stats.setMaxHealth(playerInfo.getStats().getMaxHealth());
				Player player = new Player(stats);
				player.setColor(playerInfo.getColor());
				player.setHeading(playerInfo.get);
				if (!team.contains(player)) {
					
				}
			});
			
		});
	}*/

	public GameInfo getGameInfo() {
		return gameInfo;
	}
	public void end() {
		running = false;
	}
}
