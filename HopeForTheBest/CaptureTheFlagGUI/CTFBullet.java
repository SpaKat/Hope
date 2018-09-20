package CaptureTheFlagGUI;

import CaptureTheFlagGame.Bullet;
import javafx.scene.shape.Circle;

public class CTFBullet extends Circle{
	private Bullet bullet;
	private CTFGame ctfGame;
	public CTFBullet(Bullet bullet, CTFGame ctfGame) {
		this.bullet = bullet;
		this.ctfGame = ctfGame;

		setRadius(bullet.getRadius());
		setLayoutX(bullet.getX());
		setLayoutY(bullet.getY());
		setVisible(false);
		setStyle("-fx-fill: white;");
		this.ctfGame.getChildren().add(this);
	}

	public void update() {
		if (bullet.isSpawned()) {
			setLayoutX(bullet.getX());
			setLayoutY(bullet.getY());
			setVisible(true);
		}
		if (bullet.done()) {
			ctfGame.getChildren().remove(this);
		}
	}

}
