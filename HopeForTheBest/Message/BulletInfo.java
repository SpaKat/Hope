package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Bullet;

public class BulletInfo extends ColorObjectInfo implements Serializable {

	public BulletInfo(Bullet bullet) {
		setColor(bullet.getColor());
		setRadius(bullet.getRadius());
		setSpawned(bullet.isSpawned());
		setX(bullet.getX());
		setY(bullet.getY());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 9134741899437091558L;

}
