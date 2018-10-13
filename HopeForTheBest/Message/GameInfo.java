package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Game;

public class GameInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1585886393950597409L;
	private GameboardInfo gameboard;
	private AllTeamInfo teams;
	
	public GameInfo(Game game) {
		gameboard = new GameboardInfo(game.getGameboard());
		teams = new AllTeamInfo(game.getTeams());
	}
	
	public GameboardInfo getGameboard() {
		return gameboard;
	}
	public AllTeamInfo getTeam() {
		return teams;
	}
}
