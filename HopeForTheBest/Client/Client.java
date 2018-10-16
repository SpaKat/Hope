package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import CaptureTheFlagGame.Flag;
import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;
import Message.Fire;
import Message.GameInfo;
import Message.Heading;
import Message.PlayerInfo;
import Message.ReQuestGameInfo;
import Message.StatsMessage;
import Message.Teamid;

public class Client extends Thread {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private int port;
	private String ip;
	private boolean running =true;
	private GameInfo gameInfo; 

	public Client(String ip,int port) {
		this.ip = ip;
		this.port = port;
		setName("Client");
		start();
	}

	@Override
	public void run() {
		try {
			socket = new Socket(ip, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			while (running) {
				write();
				read();
				try{Thread.sleep(1);}catch(Exception d ) {}
			}
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}

	private void write() throws Exception{
		out.writeObject(new ReQuestGameInfo());
		out.reset();
	}

	private void read(){
		try {
			Object o = in.readObject();
			switch (o.getClass().getSimpleName()) {
			case "GameInfo":
				gameInfo = (GameInfo) o;
				break;
			default:
				System.out.println(o.getClass().getSimpleName());
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public GameInfo getGameInfo() {
		return gameInfo;
	}
	public void end() {
		running = false;
	}
	public void setUpPlayer(Statistics stats, int teamId) throws Exception {
		out.writeObject(new StatsMessage(stats.getAttack(), stats.getDefense(), stats.getHealth(), stats.getMovespeed()));
		out.reset();
		out.writeObject(new Teamid(teamId));
		out.reset();
		Thread.sleep(200);
	}
	public void sendHeading(double heading) {
		try {
			out.writeObject(new Heading(heading));
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void fire() {
		try {
			out.writeObject(new Fire());
			out.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
