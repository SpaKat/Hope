package Message;

import java.io.Serializable;

public class ObjectInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -666000639909710817L;
	private double x; 
	private double y;
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
}
