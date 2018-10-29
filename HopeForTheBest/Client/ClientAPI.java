package Client;

import java.io.IOException;

import CaptureTheFlagGame.Statistics;
import Message.AllTeamInfo;
import Message.GameboardInfo;

public class ClientAPI {

	
	private Client client;
	public ClientAPI(String ip) {
		client = new Client(ip, 8008);
	}
	public ClientAPI(String ip, int port) {
		client = new Client(ip, port);
	}
	
	public void connect(Statistics stats, int teamId) throws Exception{
			client.setUpPlayer(stats, teamId);
			try {Thread.sleep(17);}catch (Exception e) {}
	}
	public void refreshGameInfo() throws Exception{
			client.askForGameInfo();
			try {Thread.sleep(17);}catch (Exception e) {}
	}
	public GameboardInfo gameBoard() {
		return client.getGameInfo().getGameboard();
	}
	public AllTeamInfo allTeams() {
		return client.getGameInfo().getTeam();
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
}
