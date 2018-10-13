package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Flag;

public class FlagInfo extends ColorObjectInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1963375347639902757L;
	private boolean taken;
	
	public FlagInfo(Flag flag) {
		setColor(flag.getColor());
		setRadius(flag.getRadius());
		setSpawned(flag.isSpawned());
		setX(flag.getX());
		setY(flag.getY());
		taken = (flag.isTaken());
	}
	public boolean isTaken() {
		return taken;
	}
}
