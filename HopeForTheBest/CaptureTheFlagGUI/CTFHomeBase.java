package CaptureTheFlagGUI;

import CaptureTheFlagGame.HomeBase;
import javafx.scene.shape.Circle;
   
public class CTFHomeBase extends Circle {
	private CTFGame ctfGame;
	private HomeBase homeBase;
	public CTFHomeBase(HomeBase homeBase, CTFGame ctfGame) {
	
		this.homeBase = homeBase;
		this.ctfGame = ctfGame;
		
		setRadius(homeBase.getRadius());
		
		setLayoutX(homeBase.getX());
		setLayoutY(homeBase.getY());
		setStyle("-fx-fill: black;"
				+"-fx-stroke:" +new ColorHexConveter(homeBase.getColor()).toString() +";"
				+ "-fx-stroke-width:" + 2  +  ";");
		this.ctfGame.getChildren().add(this);
		setVisible(false);
		
	}
	
	public void update() {
		if (homeBase.isSpawned()) {
			setVisible(true);
			setLayoutX(homeBase.getX());
			setLayoutY(homeBase.getY());
			toBack();
		}
	}

}
