/**
 * 
 */
package Message;

import java.io.Serializable;

import CaptureTheFlagGame.HomeBase;

/**
 * @author Laga-Sama
 *
 */
public class HomeBaseInfo extends ColorObjectInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6570832445376370841L;

	public HomeBaseInfo(HomeBase homeBase) {
		setColor(homeBase.getColor());
		setRadius(homeBase.getRadius());
		setSpawned(homeBase.isSpawned());
		setX(homeBase.getX());
		setY(homeBase.getY());
	}

}
