package CaptureTheFlagGame;

import java.io.Serializable;

public class GameManager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4587919392088390480L;
	private Gameboard gameboard;
	private Game game;
	private int sendingGameInfo = 1;
	public GameManager(double x,double y) {
		gameboard = new Gameboard(x, y);
		game = new Game(gameboard);
	}

	public void OneTurn() {
		// check for win
		if(!game.checkForWin()) {
			//spawn
			game.spawn();
			// move
			game.move();
			// check for kill & kill
			game.checkForKill();
			//check for point
			game.checkForPoint();
			// respawn
			game.respawn();
			//remove disconnected players
			game.removeDisconnectedPlayes();
		}
	}

	public void setheight(double newHieght) {
		gameboard.setY(newHieght);
	}
	public void setwidth(double newWidth) {
		gameboard.setX(newWidth);
	}

	public boolean addPlayer(Player player, int teamID) {
		return game.addPlayer(player,teamID);
	}

	public Team getWinningTeam() {
		return game.getWinningTeam();
	}

	public void removePlayer(Player player) {
		game.removePlayer(player);
	}
	// ----------------------------------------- NEEDED FOR GUI ----------------------------------//

	public Game getGame() {
		return game;
	}
	public void spawntest() {
		game.spawn();
	}
	public double getX() {
		return gameboard.getX();
	}
	public double getY() {
		return gameboard.getY();
	}
	public boolean isWinner() {
		return game.checkForWin();
	}
	public void reset() {
		game.reset();
	}
	public void updateSize() {
		game.resetForSize();
	}

	// ----------------------------------------- NEEDED FOR INTERNET ----------------------------------//
	
	public boolean canSend() {
		boolean b;
		if(sendingGameInfo-- >0) {
			b = true;
		}else {
			b = false;
		}
		return b;
	}
	public void resetSend() {
		sendingGameInfo = 1;
	}

	
}
