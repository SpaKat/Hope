package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameManagerGUI extends BorderPane {

	private GameManager gManager;
	private CTFGame ctfGame;
	private CTFMenuBar CTFmenu;
	public GameManagerGUI() {
		double x = 500; 
		double y = 500;
		gManager = new GameManager(x, y);
		CTFmenu = new CTFMenuBar(gManager);
		
		
		ctfGame = new CTFGame(gManager);
		setCenter(ctfGame);
		setTop(CTFmenu);
	}

	public void end() {
		ctfGame.end();	
		CTFmenu.end();
	}	


}
