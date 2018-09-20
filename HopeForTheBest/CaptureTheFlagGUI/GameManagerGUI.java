package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameManagerGUI extends BorderPane {

	private GameManager gManager;
	private CTFGame ctfGame;
	public GameManagerGUI() {
		double x = 500; 
		double y = 500;
		gManager = new GameManager(x, y);
		CTFMenuBar d = new CTFMenuBar(gManager);
		
		
		ctfGame = new CTFGame(gManager);
		setCenter(ctfGame);
		setTop(d);
	}

	public void end() {
		ctfGame.end();		
	}	


}
