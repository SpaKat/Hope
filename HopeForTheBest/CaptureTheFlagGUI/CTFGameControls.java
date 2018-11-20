package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class CTFGameControls extends Menu{


	private GameManager gManager;
	private CTFGame ctfGame;
	public CTFGameControls(GameManager gManager, CTFGame ctfGame) {
		this.gManager = gManager;
		this.ctfGame = ctfGame;
		setText("Controls");
		addQuickButtons();
	}

	private void addQuickButtons() {
		MenuItem reset = new MenuItem("Reset The Game");
		reset.setOnAction(e ->{
			gManager.reset();
			ctfGame.reset();
		});
		getItems().add(reset);
	}

}
