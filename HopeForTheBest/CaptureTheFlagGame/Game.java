package CaptureTheFlagGame;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1330880928306231504L;
	private Gameboard gameboard;
	private ArrayList<Team> teams;
	private int maxTeams = 2;
	private int pointsToWin = maxTeams-1+80000;
	private boolean gameWon = false; 
	private Team WinningTeam;  

	public Game(Gameboard gameboard) {
		this.gameboard = gameboard;
		teams = new ArrayList<>();
		for (int i = 0; i < maxTeams; i++) {
			teams.add(new Team(i,gameboard));
		}
		gameWon = false; 
	}

	public void spawn() {
		teams.forEach(team ->{
			team.spawnHomeBase();
			team.spawnFlags();
			team.spawnPlayers();
		});
	}

	public void checkForKill() {
		teams.forEach(team ->{
			for (int i = 0; i < team.size(); i++) {
				Player player = team.get(i);
				teams.forEach(otherTeam ->{
					if(team != otherTeam) {
						for (int j = 0; j < otherTeam.size(); j++) {
							Player otherPlayer = otherTeam.get(j);
							player.hit(otherPlayer);
						}
					}
				});
			}
		});
	}

	public void checkForPoint() {
		teams.forEach(team ->{
			teams.forEach(otherTeam ->{
				if(team.getId() != otherTeam.getId()) {
					if(team.scorePoint(otherTeam)) {
						team.addPoint();
						otherTeam.resetFlag();
					}
				}
			});
		});
	}

	public void move() {
		teams.forEach(team ->{
			team.movePlayers();
			team.moveFlag();
		});
		teams.forEach(team ->{
			teams.forEach(otherTeam ->{
				if(team != otherTeam) {
					if (!otherTeam.takenFlag()) {
						team.forEach(player ->{
							otherTeam.takeTheFlag(player);
						});
					}
				}
			});
		});

	}

	public boolean checkForWin() {
		teams.forEach(team ->{
			if (team.getPoints() >= pointsToWin) {
				gameWon = true;
				WinningTeam = team;
			}
		});
		return gameWon;
	}

	public void respawn() {
		teams.forEach(team ->{
			for (int i = 0; i < team.size(); i++) {
				if(team.get(i).isDied()) {
					team.get(i).setX(-500);
					team.get(i).setY(-500);
				}
			}
		});
	}

	public boolean addPlayer(Player player, int teamID) {
		boolean added = false;
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			if (team.getId() == teamID && team.getMaxPlayers() >= team.size()) {
				team.add(player);
				player.setColor(team.getColor());
				added = true;
			}
		}
		return added;
	}
	public Team getWinningTeam() {
		return WinningTeam;
	}
	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void removePlayer(Player player) {
		teams.forEach(team ->{
			
			if(team.remove(player)) {
				player.setDisconnect(true);
			}
		});
	}

	public void reset() {
		teams.forEach(team->{
			team.reset();
		});
		WinningTeam = null;
		gameWon = false;
	}

	public void resetForSize() {
		teams.forEach(team->{
			team.resetForSize();
		});
	}

	public Gameboard getGameboard() {
		return gameboard;
	}

	public void removeDisconnectedPlayes() {
		teams.forEach(team ->{
			team.cleanPlayers();
		});
	}

	public void fill() {
		teams.forEach(team->{
			team.fill();
		});
	}
}
