package CaptureTheFlagGUI;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class CTFMenuBar extends MenuBar {

	private GameManager gManager;

	public CTFMenuBar(GameManager gManager) {
		this.gManager = gManager;
		toFront();

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

}
