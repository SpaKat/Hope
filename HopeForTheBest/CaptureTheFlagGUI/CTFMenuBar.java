package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import Server.ServerCommunication;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CTFMenuBar extends MenuBar {

	//private GameManager gManager;
	private ServerCommunication server;
	private GameManagerGUI gameManagerGUI;
	public CTFMenuBar(GameManager gManager, GameManagerGUI gameManagerGUI, CTFGame ctfGame) {
	//	this.gManager = gManager;
		this.gameManagerGUI = gameManagerGUI;
		toFront();

		//testFuncations();
		Menu Server = new Menu("Server");
		MenuItem startServer = new MenuItem("Start Server");
		startServer.setOnAction(e->{
			server = new ServerCommunication(gManager);
		});

		Server.getItems().addAll(startServer);

		Menu Client = new Menu("Client");
		MenuItem client = new MenuItem("Client Mode");
		client.setOnAction(e->{
			this.gameManagerGUI.clientMode();
		});
		Client.getItems().addAll(client);
		getMenus().addAll(Server,Client,new CTFGameControls(gManager,ctfGame));
	}
/*
	private void testFuncations() {
		Menu test = new Menu("Test");
		MenuItem oneturn = new MenuItem ("One Turn");
		oneturn.setOnAction(e ->{
			this.gManager.OneTurn();
		});
		MenuItem hundredturn = new MenuItem ("100 Turn");
		hundredturn.setOnAction(e ->{
			for (int i = 0; i < 100; i++) {
				this.gManager.OneTurn();
			}
		});
		MenuItem spawn = new MenuItem ("just spawn");
		spawn.setOnAction(r ->{
			this.gManager.spawntest();
		});
		MenuItem killplayer = new MenuItem ("kill player");
		Player p = new Player(new Statistics(5, 5, 5, 5));
		MenuItem spawnkill = new MenuItem (" spawnkill");
		spawnkill.setOnAction(r ->{
			this.gManager.addPlayer(p, 0);

		});
		killplayer.setOnAction(e ->{

			this.gManager.removePlayer(p);
		});
		MenuItem firebullet = new MenuItem ("fire bullet");
		firebullet.setOnAction(e ->{
			p.fireBullet();
		});
		test.getItems().addAll(oneturn,spawn,hundredturn,spawnkill,killplayer,firebullet);
		getMenus().add(test);
	}
*/
	public void end() {
		try {
			server.end();
		}catch (Exception e) {
		}
	}

}
