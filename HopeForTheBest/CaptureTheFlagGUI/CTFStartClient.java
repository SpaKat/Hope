package CaptureTheFlagGUI;



import java.io.IOException;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Statistics;
import Client.Client;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CTFStartClient extends VBox {

	private Client client;
	private double heading = 0;
	private Statistics stats = new Statistics(5, 5, 5, 5);
	private GameManagerGUI gameManagerGUI;
	public CTFStartClient(GameManagerGUI gameManagerGUI) {
		this.gameManagerGUI = gameManagerGUI;
		setAlignment(Pos.CENTER);
		Text askforIP = new Text("Enter Ip Address");
		TextField enterIP = new TextField("127.0.0.1");

		Text askforPort = new Text("Enter Port");
		TextField enterPort = new TextField("26539");

		Button enter = new Button("Enter");
		enter.setOnAction(e->{
			try {
				client = new Client(enterIP.getText(),Integer.parseInt(enterPort.getText()));
				setUpPlayer();
			} catch (Exception e2) {}
		});
		this.getChildren().addAll(askforIP,enterIP, askforPort,enterPort,enter);
	}
	
	private void setUpPlayer() {
		getChildren().clear();


		Text health = new Text("Health");
		health.setFont(new Font(16));
		Slider healthBar = new Slider();
		setupStatBars(healthBar);
		healthBar.valueProperty().addListener((arg,oldV,newV)->{
			stats.setHealth(newV.doubleValue());
			if (!stats.getRateing()) {
				stats.setHealth(oldV.doubleValue());
				healthBar.setValue(oldV.doubleValue());
			}
		});

		Text attack = new Text("Attack");
		attack.setFont(new Font(16));
		Slider attackBar = new Slider();
		setupStatBars(attackBar);
		attackBar.valueProperty().addListener((arg,oldV,newV)->{
			stats.setAttack(newV.doubleValue());
			if (!stats.getRateing()) {
				stats.setAttack(oldV.doubleValue());
				attackBar.setValue(oldV.doubleValue());
			}
		});

		Text defense = new Text("Defense");
		defense.setFont(new Font(16));
		Slider defenseBar = new Slider();
		setupStatBars(defenseBar);
		defenseBar.valueProperty().addListener((arg,oldV,newV)->{
			stats.setDefense(newV.doubleValue());
			if (!stats.getRateing()) {
				stats.setDefense(oldV.doubleValue());
				defenseBar.setValue(oldV.doubleValue());
			}
		});

		Text movespeed = new Text("Movespeed");
		movespeed.setFont(new Font(16));
		Slider movespeedBar = new Slider();
		setupStatBars(movespeedBar);
		movespeedBar.valueProperty().addListener((arg,oldV,newV)->{
			stats.setMovespeed(newV.doubleValue()/stats.getSCALE());
			if (!stats.getRateing()) {
				stats.setMovespeed(oldV.doubleValue());
				movespeedBar.setValue(oldV.doubleValue());
			}
		});

		Text TeamId = new Text("TeamId");
		TeamId.setFont(new Font(16));
		Slider TeamIdBar = new Slider();
		TeamIdBar.setMin(0);
		TeamIdBar.setMax(3);
		TeamIdBar.setValue(0);
		TeamIdBar.setMinorTickCount(0);
		TeamIdBar.setMajorTickUnit(1);
		TeamIdBar.setSnapToTicks(true);
		TeamIdBar.setShowTickMarks(true);
		TeamIdBar.setShowTickLabels(true);

		Button enter = new Button("Enter");
		enter.setOnAction(e->{
			try {
				client.setUpPlayer(stats,(int)TeamIdBar.getValue());
				makeControls();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		this.getChildren().addAll(health,healthBar,attack,attackBar,defense,defenseBar,movespeed,movespeedBar,TeamId,TeamIdBar,enter);
	}

	private void setupStatBars(Slider statBar) {
		statBar.setMin(0);
		statBar.setMax(100);
		statBar.setValue(5);
		statBar.setShowTickMarks(true);
		statBar.setShowTickLabels(true);
	}
	private void makeControls() {
		//Label info = new Label(" W - up \n A - down \n S - left \n D - right \n fire - Space");
		getChildren().clear();
		//getChildren().add(info);
		
		
		getScene().setOnKeyPressed(e->{
			
			if(e.getCode() == KeyCode.W) {
				heading = 3*Math.PI/2.0;
			}
			if(e.getCode() == KeyCode.A) {
				heading = Math.PI;
			}
			if(e.getCode() == KeyCode.S) {
				heading = Math.PI/2.0;
			}
			if(e.getCode() == KeyCode.D) {
				heading = 2.0*Math.PI;
			}
			if(e.getCode() == KeyCode.SPACE) {
				try {client.fire();} catch (IOException e1) {}
			}
			try {client.sendHeading(heading);} catch (IOException e1) {}
		});
		gameManagerGUI.makeClient();
	}

	public void end() {
		try {
			client.end();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Pane makeClient() {
		CTFGame ctfGame = new CTFGame(client);
		
		return ctfGame;
	}

}
