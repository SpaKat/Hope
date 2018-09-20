package CaptureTheFlagGUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CaptureTheFlagGUI extends Application {

	@Override
	public void start(Stage stage) {
	
		GameManagerGUI guiManager = new GameManagerGUI(); 
		Scene scene = new Scene(guiManager,500,520);
		stage.setScene(scene);
		stage.setTitle("Capture the Flag");
		stage.setOnCloseRequest(close ->{
			guiManager.end();
		});
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
