package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CTFMenuBar extends MenuBar {

	private GameManager gManager;

	public CTFMenuBar(GameManager gManager) {
		this.gManager = gManager;
		
		
		Menu test = new Menu("Test");
		MenuItem oneturn = new MenuItem ("One Turn");
		oneturn.setOnAction(e ->{
			this.gManager.OneTurn();
		});
		
		MenuItem spawn = new MenuItem ("just spawn");
		spawn.setOnAction(r ->{
			this.gManager.spawntest();
		});
		test.getItems().addAll(oneturn,spawn);
		getMenus().add(test);
	}

}
