package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import CaptureTheFlagGame.Statistics;
import Message.Fire;
import Message.GameInfo;
import Message.Heading;
import Message.NewPlayer;
import Message.ReQuestGameInfo;

public class Client extends Thread{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private int port;
	private String ip;
	private GameInfo gameInfo;
	private boolean running = true; 

	public Client(String ip,int port) {
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void askForGameInfo() throws Exception{
		out.writeObject(new ReQuestGameInfo());
		out.flush();

	}
	public GameInfo getGameInfo() {
		return gameInfo;
	}
	public void read() {
		new ReadThread(this);
	}
	public void end() throws IOException{
			socket.close();
		
	}
	public void setUpPlayer(Statistics stats, int teamId) throws Exception {
		out.writeObject(new NewPlayer(teamId,stats.getAttack(), stats.getDefense(), stats.getHealth(), stats.getMovespeed()));
		out.flush();
		
	}
	public void sendHeading(double heading) throws IOException{
			out.writeObject(new Heading(heading));
			out.flush();
	}

	public void fire() throws IOException{
			out.writeObject(new Fire());
			out.flush();
	}
	public ObjectInputStream getIn() {
		return in;
	}
	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
}
