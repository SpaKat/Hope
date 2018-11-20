package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.layout.BorderPane;

public class GameManagerGUI extends BorderPane {

	private GameManager gManager;
	private CTFGame ctfGame;
	private CTFMenuBar CTFmenu;
	private CTFStartClient CTFclient;
	public GameManagerGUI() {
		double x = 500; 
		double y = 500;
		gManager = new GameManager(x, y);
		CTFclient = new CTFStartClient(this);
		ctfGame = new CTFGame(gManager);
		CTFmenu = new CTFMenuBar(gManager,this,ctfGame);
		setCenter(ctfGame);
		setTop(CTFmenu);
	}

	public void end() {
		try {
			ctfGame.end();	
			CTFmenu.end();
			CTFclient.end();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void clientMode() {
		setCenter(CTFclient);
	}

	
	public void makeClient() {
		getChildren().clear();	
		setCenter(CTFclient.makeClient());
			CTFmenu.toFront();
			setTop(CTFmenu);
	}	


}
