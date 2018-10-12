package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CTFGameWon extends Stage{

	private Pane pane = new Pane();
	private GameManager gManager;
	private CTFGame ctfGame;
	public CTFGameWon(GameManager gManager,CTFGame ctfGame) {
		this.gManager = gManager;
		this.ctfGame = ctfGame;
		addQuickButtons();
		Scene scene = new Scene(pane);
		setScene(scene);
		setTitle("Game Over?");
		show();
	}


	private void addQuickButtons() {
		
		Button reset = new Button("Reset The Game");
		reset.setOnAction(e ->{
			gManager.reset();
			//ctfGame.reset();
		});
		
		VBox quickButtons  = new VBox(20);
		quickButtons.getChildren().addAll(reset);
		pane.getChildren().add(quickButtons);
	}

}
