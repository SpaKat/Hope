package CaptureTheFlagGUI;

import java.io.IOException;

import CaptureTheFlagGame.Bullet;
import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import CaptureTheFlagGame.Team;
import Client.Client;
import Client.ClientAPI;
import Message.AllTeamInfo;
import Message.BulletInfo;
import Message.GameboardInfo;
import Message.PlayerInfo;
import Message.TeamInfo;

public class CTFClientGame extends Thread {

	private ClientAPI client;
	private GameManager gManager;
	private boolean running = true;
	public CTFClientGame(Client client, GameManager gManager) {
		this.client = new ClientAPI(client);
		this.gManager = gManager;
		this.start();
	}

	@Override
	public void run() {
		while (client.isAlive() && running ) {
			try {client.requestGameInfo();} catch (Exception e) {}
			try {
				GameboardInfo gameboard = client.gameBoard();
				gManager.getGame().getGameboard().setX(gameboard.getX());
				gManager.getGame().getGameboard().setY(gameboard.getY());
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				AllTeamInfo teams = client.allTeams();
				for (int i = 0; i < teams.size(); i++) {
					try {
						TeamInfo teamInfo = teams.get(i);
						Team team = gManager.getGame().getTeams().get(teamInfo.getId());
					
						team.getHomeBase().setColor(teamInfo.getHomeBase().getColor());
						team.getHomeBase().setSpawned(teamInfo.getHomeBase().isSpawned());
						team.getHomeBase().setX(teamInfo.getHomeBase().getX());
						team.getHomeBase().setY(teamInfo.getHomeBase().getY());
						team.getHomeBase().setRadius(teamInfo.getHomeBase().getRadius());
						
						
						team.getFlag().setColor(teamInfo.getFlag().getColor());
						team.getFlag().setSpawned(teamInfo.getFlag().isSpawned());
						team.getFlag().setX(teamInfo.getFlag().getX());
						team.getFlag().setY(teamInfo.getFlag().getY());
						team.getFlag().setRadius(teamInfo.getFlag().getRadius());
						
						for (int j = 0; j < teamInfo.size(); j++) {
							PlayerInfo playerInfo = teamInfo.get(j);
							
								team.get(j).setColor(playerInfo.getColor());
								team.get(j).setSpawned(playerInfo.isSpawned());
								team.get(j).setX(playerInfo.getX());
								team.get(j).setY(playerInfo.getY());
								team.get(j).setRadius(playerInfo.getRadius());
								team.get(j).setStats(new Statistics(playerInfo.getStats().getAttack(),
										playerInfo.getStats().getDefense(),
										playerInfo.getStats().getHealth(),
										playerInfo.getStats().getMovespeed()));
								/*team.get(j).getBullets().clear();
								for (int k = 0; k < playerInfo.getBullets().size(); k++) {
									BulletInfo bulletInfo = playerInfo.getBullets().get(k);
									Bullet bullet = new Bullet(bulletInfo.getX(), bulletInfo.getY());
									team.get(j).getBullets().add(bullet);
								}*/
								
								
						}
					
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {Thread.sleep(17);}catch (Exception e) {};
		}
	}

	public void end() {
		try {
			client.end();
			running = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
	}

}
