package CaptureTheFlagGame;



public class GameManager{

	private Gameboard gameboard;
	private Game game;

	public GameManager(double x,double y) {
		gameboard = new Gameboard(x, y);
		game = new Game(gameboard);
	}

	public boolean OneTurn() {
		//spawn
		game.spawn();
		// check for kill & kill
		game.checkForKill();
		//check for point
		game.checkForPoint();
		// move
		game.move();
		// respawn
		game.respawn();
		// check for win
		return game.checkForWin();
	}
	
	public void setheight(double newHieght) {
		gameboard.setY(newHieght);
	}
	public void setwidth(double newWidth) {
		gameboard.setX(newWidth);
	}

	public void addPlayer(Player player, int teamID) {
		game.addPlayer(player,teamID);
	}
	
	public Team getWinningTeam() {
		return game.getWinningTeam();
	}

	public void removePlayer(Player player) {
		game.removePlayer(player);
	}
	
	public void gameOver() {
		
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
		// TODO Auto-generated method stub
		return game.checkForWin();
	}
	// ----------------------------------------- NEEDED FOR INTERNET ----------------------------------//
/*
	public GameInfo sendInfo() {
		GameInfo gameInfo = new GameInfo(game.getTeams(),game.getGameboard()); 
		return gameInfo;
	}
*/

	

	

	





}
