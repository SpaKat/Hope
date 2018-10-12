package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class CTFGameControls extends Menu{


	private GameManager gManager;

	public CTFGameControls(GameManager gManager) {
		this.gManager = gManager;
		setText("Controls");
		addQuickButtons();
		


	
	}


	private void addQuickButtons() {
		
		MenuItem reset = new MenuItem("Reset The Game");
		reset.setOnAction(e ->{
			gManager.reset();
		});
		getItems().add(reset);
	}

}
