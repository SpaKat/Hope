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
		setStyle("-fx-fill: white;"
				+"-fx-stroke:" +new ColorHexConveter(flag.getColor()).toString() +";"
				+ "-fx-stroke-width:" + (flag.getRadius()-2)  +  ";");
		this.ctfGame.getChildren().add(this);
		setVisible(false);
	}

	public void update() {
		if(flag.isSpawned()) {
			setVisible(true);
			setLayoutX(flag.getX());
			setLayoutY(flag.getY());
			toFront();
		}
	}

}
