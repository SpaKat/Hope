package Message;

import java.io.Serializable;

public class Heading implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9139823635389959120L;
	private double heading;

	public Heading(double heading) {
		this.heading = heading;
	}
	public double getHeading() {
		return heading;
	}
}
