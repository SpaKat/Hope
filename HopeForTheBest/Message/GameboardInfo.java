package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Gameboard;

public class GameboardInfo extends ObjectInfo implements Serializable{


	public GameboardInfo(Gameboard gameboard) {
		setX(gameboard.getX());
		setY(gameboard.getY());
	}
	
}
