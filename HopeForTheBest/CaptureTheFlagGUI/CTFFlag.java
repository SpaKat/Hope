package CaptureTheFlagGUI;

import CaptureTheFlagGame.Flag;
import javafx.scene.shape.Circle;

public class CTFFlag extends Circle {

	private Flag flag;
	private CTFGame ctfGame;
	
	public CTFFlag(Flag flag, CTFGame ctfGame) {
		this.flag = flag;
		this.ctfGame = ctfGame;
		setRadius(flag.getRadius());
		setLayoutX(flag.getX());
		setLayoutY(flag.getY());
		setStyle("-fx-fill: " + new ColorHexConveter(flag.getColor()).toString() +";"
				+"-fx-stroke: gold;"
				+ "-fx-stroke-width:" + 2  +  ";");
		ctfGame.getChildren().add(this);
	}

	public void update() {
		setLayoutX(flag.getX());
		setLayoutY(flag.getY());
	}

}
