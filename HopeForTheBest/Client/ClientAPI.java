package Client;

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
	
	public void connect(Statistics stats, int teamId) {
		try {
			client.setUpPlayer(stats, teamId);
		} catch (Exception e) {
			System.err.println("connection Failed");
		}
	}
	public void refreshGameInfo() {
		try {
			client.askForGameInfo();
		} catch (Exception e) {
			System.err.println("connection Failed");
		}
	}
	public GameboardInfo gameBoard() {
		return client.getGameInfo().getGameboard();
	}
	public AllTeamInfo allTeams() {
		return client.getGameInfo().getTeam();
	}
	public void sendHeading(double heading) {
		client.sendHeading(heading);
	}
	public void fire() {
		client.fire();
	}
	public void end() {
		client.end();
	}
}
