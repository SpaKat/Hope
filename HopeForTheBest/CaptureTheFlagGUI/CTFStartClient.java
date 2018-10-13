package CaptureTheFlagGUI;



import CaptureTheFlagGame.GameManager;
import Client.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CTFStartClient extends VBox {

	private GameManagerGUI gameManagerGUI;
	private Client client;
	public CTFStartClient(GameManagerGUI gameManagerGUI) {
		setAlignment(Pos.CENTER);
		Text askforIP = new Text("Enter Ip Address");
		TextField enterIP = new TextField();
		
		Text askforPort = new Text("Enter Ip Address");
		TextField enterPort = new TextField();
		
		Button enter = new Button("Enter");
		enter.setOnAction(e->{
			try {
				GameManager gameManager = new GameManager(500, 500);
				client = new Client(gameManager,enterIP.getText(),Integer.parseInt(enterPort.getText()));
				gameManagerGUI.setCtfGame(new CTFGame(gameManager));
				gameManagerGUI.hostMode();
			} catch (Exception e2) {}
		});
		this.getChildren().addAll(askforIP,enterIP, askforPort,enterPort,enter);
	}

	public void end() {
		try {
			client.end();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
