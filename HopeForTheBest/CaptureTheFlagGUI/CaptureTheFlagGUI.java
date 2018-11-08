package CaptureTheFlagGUI;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CaptureTheFlagGUI extends Application {

	@Override
	public void start(Stage stage) {
		
		GameManagerGUI guiManager = new GameManagerGUI();	
		Scene scene = new Scene(guiManager,500,520);
		stage.setScene(scene);
		try {
			stage.setTitle(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			stage.setTitle("Capture The Flag");

		}
		stage.setOnCloseRequest(close ->{
			guiManager.end();
		});
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
