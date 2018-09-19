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
		teams = new ArrayList<CTFTeams>();
		game.getTeams().forEach(team ->{
			teams.add(new CTFTeams(team,this));
		});
		backgroundUpdate = new CTFGameUpdate(this);
		setStyle("-fx-background-color: black");
		
	}

	public void update() {
		teams.forEach(team ->{
			team.update();
		});
		setHeight(gManager.getX());
		setWidth(gManager.getY());
		
	}

	public void end() {
		backgroundUpdate.end();
	}


}
