package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.layout.BorderPane;

public class GameManagerGUI extends BorderPane {

	private GameManager gManager;
	private CTFGame ctfGame;
	public GameManagerGUI() {
		double x = 500; 
		double y = 500;
		gManager = new GameManager(x, y);
		setTop(new CTFMenuBar(gManager));
		ctfGame = new CTFGame(gManager);
		setCenter(ctfGame);
	}

	public void end() {
		ctfGame.end();		
	}	


}
