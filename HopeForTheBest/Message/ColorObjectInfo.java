package Message;

import java.io.Serializable;

public class ColorObjectInfo extends ObjectInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1715712474019195509L;
	private int color ;
	private boolean spawned;
	private double radius; 
	public boolean isSpawned() {
		return spawned;
	}
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}
