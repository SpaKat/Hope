package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import CaptureTheFlagGame.Statistics;
import Message.AllTeamInfo;
import Message.Fire;
import Message.GameboardInfo;
import Message.Heading;
import Message.NewPlayer;
import Message.PlayerInfo;
import Message.ReQuestGameInfo;
import Message.ReQuestPlayerInfo;

public class Client{

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private AllTeamInfo allTeamInfo;
	private GameboardInfo gameboardInfo;
	private PlayerInfo playerInfo;
	private boolean running = true; 
	public Client(String ip,int port) {
		try {
			socket = new Socket(ip, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			new ReadThread(this);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isRunning() {
		return running;
	}
	public void askForGameInfo() throws Exception{
		out.writeObject(new ReQuestGameInfo());
		out.flush();
	}
	public void end() throws IOException{
			running = false;
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
	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}
	public void askForPlayerInfo() throws IOException {
		out.writeObject(new ReQuestPlayerInfo());
		out.flush();
	}
	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}
	public boolean isAlive() {
		return !socket.isClosed();
	}
	public AllTeamInfo getAllTeamInfo() {
		return allTeamInfo;
	}
	public void setAllTeamInfo(AllTeamInfo allTeamInfo) {
		this.allTeamInfo = allTeamInfo;
	}
	public GameboardInfo getGameboardInfo() {
		return gameboardInfo;
	}
	public void setGameboardInfo(GameboardInfo gameboardInfo) {
		this.gameboardInfo = gameboardInfo;
	}
	
}
