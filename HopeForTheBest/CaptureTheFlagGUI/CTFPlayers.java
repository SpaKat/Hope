package CaptureTheFlagGUI;

import java.util.ArrayList;

import CaptureTheFlagGame.Bullet;
import CaptureTheFlagGame.Player;
import javafx.scene.shape.Circle;

public class CTFPlayers extends Circle{

	
	private Player player;
	private CTFGame ctfGame;
	private ArrayList<CTFBullet> bullets;
	public CTFPlayers(Player player, CTFGame ctfGame) {
		this.player = player;
		this.ctfGame = ctfGame;
		bullets = new ArrayList<CTFBullet>();
		setRadius(player.getRadius());
		setLayoutX(player.getX());
		setLayoutY(player.getY());
		setStyle("-fx-fill: black;"
				+"-fx-stroke:" +new ColorHexConveter(player.getColor()).toString() +";"
				+ "-fx-stroke-width:" + (player.getRadius()-2)  +  ";");
		this.ctfGame.getChildren().add(this);
		setVisible(false);
	}

	public void update() {
		if (player.isDied()) {
			setVisible(false);
		}else {
			setVisible(true);
		}
		if(player.isSpawned()) {
			setLayoutX(player.getX());
			setLayoutY(player.getY());
		}
		if (player.isDisconnect()) {
			ctfGame.getChildren().remove(this);
		}
		for (int i = 0; i < player.getBullets().size(); i++) {
			Bullet b = player.getBullets().get(i);
			try {
				CTFBullet ctfB = bullets.get(i);
				if (ctfB.done()) {
					bullets.remove(ctfB);
			//		bullets.add(new CTFBullet(b,ctfGame));
				}
			}catch (Exception e) {
				bullets.add(new CTFBullet(b,ctfGame));
			}
			
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		//System.out.println("CTFPlayers: " + player.getX() + "    " + player.getY());
	}

}
