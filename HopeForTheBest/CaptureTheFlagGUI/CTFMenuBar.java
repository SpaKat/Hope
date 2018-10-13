package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import Server.ServerCommunication;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CTFMenuBar extends MenuBar {

	private GameManager gManager;
	private ServerCommunication server;
	private GameManagerGUI gameManagerGUI;
	public CTFMenuBar(GameManager gManager, GameManagerGUI gameManagerGUI) {
		this.gManager = gManager;
		toFront();

		//testFuncations();
		Menu Server = new Menu("Server");
		MenuItem startServer = new MenuItem("Start Server");
		startServer.setOnAction(e->{
			 server =new ServerCommunication(gManager);
		});
		MenuItem host = new MenuItem("Host Mode");
		host.setOnAction(e ->{
			gameManagerGUI.hostMode();
		});
		Server.getItems().addAll(startServer,host);
	
		Menu Client = new Menu("Client");
		MenuItem client = new MenuItem("Client Mode");
		client.setOnAction(e->{
			gameManagerGUI.clientMode();
		});
		Client.getItems().addAll(client);
		getMenus().addAll(Server,Client,new CTFGameControls(gManager));
	}

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

		//	this.gManager.addPlayer(new Player(new Statistics(5, 5, 5, 5)), 0);
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

	
	public void end() {
		try {
		server.end();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
