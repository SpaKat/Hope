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
		
	public CTFGame(GameManager gManager) {
		this.game = gManager.getGame();
		this.gManager = gManager;
		setUpGame();
		toBack();
		backgroundUpdate = new CTFGameUpdate(this);
		setStyle("-fx-background-color: black");
		linkSizeToManager(this.gManager);
	}

	private void linkSizeToManager(GameManager gManager) {
		heightProperty().addListener((arg, oldV, newV) ->{
			gManager.setheight(newV.doubleValue());
			gManager.updateSize();
		});
		widthProperty().addListener((arg, oldV, newV) ->{
			gManager.setwidth(newV.doubleValue());
			gManager.updateSize();
		});
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
		//System.out.println(gManager.isWinner());
	}

	public void end() {
		backgroundUpdate.end();
	}


}
