package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Gameboard;

public class GameboardInfo extends ObjectInfo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7423259632903930987L;

	public GameboardInfo(Gameboard gameboard) {
		setX(gameboard.getX());
		setY(gameboard.getY());
	}
	
}
