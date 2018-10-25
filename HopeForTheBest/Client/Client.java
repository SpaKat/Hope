package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.sun.corba.se.impl.transport.ReaderThreadImpl;

import CaptureTheFlagGame.Statistics;
import Message.Fire;
import Message.GameInfo;
import Message.Heading;
import Message.ReQuestGameInfo;
import Message.StatsMessage;
import Message.Teamid;

public class Client{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private int port;
	private String ip;
	private GameInfo gameInfo; 

	public Client(String ip,int port) {
		this.ip = ip;
		this.port = port;
		try {
			socket = new Socket(ip, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public void askForGameInfo() throws Exception{
		out.writeObject(new ReQuestGameInfo());
		out.flush();
		Thread.sleep(17);
	}
	public GameInfo getGameInfo() {
		return gameInfo;
	}
	public void read() {
		new ReadThread(this);
	}
	public void end() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	public void setUpPlayer(Statistics stats, int teamId) throws Exception {
		out.writeObject(new StatsMessage(stats.getAttack(), stats.getDefense(), stats.getHealth(), stats.getMovespeed()));
		out.flush();
		out.writeObject(new Teamid(teamId));
		out.flush();
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
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ObjectInputStream getIn() {
		return in;
	}
	public void setGameInfo(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
	}
}
