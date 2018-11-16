package Client;

import java.io.IOException;

import CaptureTheFlagGame.Statistics;
import Message.AllTeamInfo;
import Message.GameboardInfo;
import Message.PlayerInfo;
import Message.TeamInfo;

public class ClientAPI {
	private Client client;
	public ClientAPI(String ip) {
		client = new Client(ip, 26539);
	}
	public ClientAPI(String ip, int port) {
		client = new Client(ip, port);
	}

	public ClientAPI(Client client2) {
		this.client = client2;
	}
	public void connect(Statistics stats, int teamId) throws Exception{
		client.setUpPlayer(stats, teamId);
		try {Thread.sleep(17);}catch (Exception e) {}
	}
	public void requestGameInfo() throws Exception{
		client.askForGameInfo();
		try {Thread.sleep(17);}catch (Exception e) {}
	}
	public GameboardInfo gameBoard() {
		return client.getGameInfo().getGameboard();
	}
	public AllTeamInfo allTeams() {
		return client.getGameInfo().getTeam();
	}
	public TeamInfo getTeam(int index) throws IndexOutOfBoundsException{
		return client.getGameInfo().getTeam().get(index);
	}
	public void sendHeading(double heading) throws IOException{
		client.sendHeading(heading);
		try {Thread.sleep(17);}catch (Exception e) {}
	}
	public void fire() throws IOException{
		client.fire();
		try {Thread.sleep(17);}catch (Exception e) {}
	}
	public void end() throws IOException{
		client.end();
	}
	public PlayerInfo getPlayerInfo() {
		return client.getPlayerInfo();
	}
	public void requestPlayerInfo() throws IOException{
		client.askForPlayerInfo();
		try {Thread.sleep(17);}catch (Exception e) {}
	}
	public boolean isAlive() {
		return client.isAlive();
	}
}
