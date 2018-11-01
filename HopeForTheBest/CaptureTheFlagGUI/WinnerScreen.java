package CaptureTheFlagGUI;

import CaptureTheFlagGame.Team;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class WinnerScreen extends Stage {

	public WinnerScreen(Team team) {
		
		Label winner = new Label("team: "+team.getId() + " Won!" );
		
		winner.setStyle("-fx-text-fill: " + new ColorHexConveter(team.getColor()).toString()+";"+"-fx-font-size: 60");
		
		Scene scene = new Scene(new BorderPane(winner));
		setScene(scene);
		show();
	}

}
