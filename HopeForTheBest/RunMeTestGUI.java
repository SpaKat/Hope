import CaptureTheFlagGame.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RunMeTestGUI extends Application {

	@Override
	public void start(Stage stage) {
		GameManager gManager = new GameManager(500, 500);
		Pane pane = new Pane();
		
		
		
		
		Scene scene = new Scene(pane,500, 500);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
