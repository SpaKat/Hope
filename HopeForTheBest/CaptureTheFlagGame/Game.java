package CaptureTheFlagGame;

import java.util.ArrayList;

public class Game {

	private Gameboard gameboard;
	private ArrayList<Team> teams;
	private int maxTeams = 4;
	private int pointsToWin = 1;
	private boolean gameWon = false; 
	private Team WinningTeam; 

	public Game(Gameboard gameboard) {
		this.gameboard = gameboard;
		teams = new ArrayList<>();
		for (int i = 0; i < maxTeams; i++) {
			teams.add(new Team(i));
		}
		gameWon = false; 
	}

	public void spawn() {
		teams.forEach(team ->{
			team.spawnHomeBase(gameboard.getX(), gameboard.getY());
			team.spawnFlags(gameboard.getX(), gameboard.getY());
			team.spawnPlayers(gameboard.getX(), gameboard.getY());
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
				if(team != otherTeam) {
					if(team.scorePoint(otherTeam)) {
						team.addPoint();
						otherTeam.resetFlag(gameboard.getX(), gameboard.getY());
					}
				}
			});
		});
	}

	public void move() {
		teams.forEach(team ->{
			team.movePlayers(gameboard.getX(), gameboard.getY());
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

	public void addPlayer(Player player, int teamID) {
		teams.forEach(team ->{
			if (team.getId() == teamID && team.getMaxPlayers() >= team.size()) {
				team.add(player);
				player.setColor(team.getColor());
			}
		});
	}
	public Team getWinningTeam() {
		return WinningTeam;
	}
	public ArrayList<Team> getTeams() {
		return teams;
	}
}
