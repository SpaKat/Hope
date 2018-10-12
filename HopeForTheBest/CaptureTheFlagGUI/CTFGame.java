package CaptureTheFlagGUI;

import java.util.ArrayList;

import CaptureTheFlagGame.Game;
import CaptureTheFlagGame.GameManager;
import javafx.scene.layout.Pane;

public class CTFGame extends Pane {


	private Game game;
	private CTFGameUpdate backgroundUpdate;
	private ArrayList<CTFTeams> teams;
	private GameManager gManager;
	private boolean GameOver = false;
	public CTFGame(GameManager gManager) {
		this.game = gManager.getGame();
		this.gManager = gManager;
		setUpGame();
		backgroundUpdate = new CTFGameUpdate(this);
		setStyle("-fx-background-color: black");
		toBack();
	}

	private void setUpGame() {
		teams = new ArrayList<CTFTeams>();
		game.getTeams().forEach(team ->{
			teams.add(new CTFTeams(team,this));
		});
	}

	public void update() {
		teams.forEach(team ->{
			team.update();
		});
		setHeight(gManager.getX());
		setWidth(gManager.getY());
		if(gManager.isWinner() && !GameOver) {
			new CTFGameWon(gManager,this);
			GameOver = true;
		}
	}

	public void end() {
		backgroundUpdate.end();
	}

	public void reset() {
		GameOver = false;
		setUpGame();
	}


}
