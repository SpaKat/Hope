package CaptureTheFlagGUI;

import java.util.ArrayList;

import CaptureTheFlagGame.GameManager;
import Client.Client;
import javafx.scene.layout.Pane;

public class CTFGame extends Pane {



	private CTFGameUpdate backgroundUpdate;
	private ArrayList<CTFTeams> teams;
	private GameManager gManager;
	public CTFGame(GameManager gManager) {
		this.gManager = gManager;
		setUpGame();
		toBack();
		backgroundUpdate = new CTFGameUpdate(this,gManager);
		setStyle("-fx-background-color: black");
		linkSizeToManager(this.gManager);
	}

	public CTFGame(Client client) {
		this.gManager = new GameManager(500, 500);
		gManager.getGame().fill();
		setUpGame();
		toBack();
		backgroundUpdate = new CTFClientGameUpdate(this,gManager,client);
		setStyle("-fx-background-color: black");
		
	}

	private void linkSizeToManager(GameManager gManager) {
		heightProperty().addListener((arg, oldV, newV) ->{
			gManager.setheight(newV.doubleValue());
			gManager.updateSize();
		});
		widthProperty().addListener((arg, oldV, newV) ->{
			gManager.setwidth(newV.doubleValue());
			gManager.updateSize();
		});
	}

	private void setUpGame() {
		teams = new ArrayList<CTFTeams>();
		gManager.getGame().getTeams().forEach(team ->{
			teams.add(new CTFTeams(team,this));
		});
	}

	public void update() {
		teams.forEach(team ->{
			team.update();
		});
		//System.out.println(gManager.isWinner());
	}

	public void end() {
		backgroundUpdate.end();
		//	gManager.gameover();
	}

	public void showWinner() {
		new WinnerScreen(gManager.getWinningTeam());
	}

	public void reset() {
		end();
		backgroundUpdate = new CTFGameUpdate(this,gManager);
	}

	public void updateClient() {
		teams.forEach(team ->{
			team.updateClient();
		});
	}


}
