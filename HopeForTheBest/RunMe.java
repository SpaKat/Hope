import java.io.IOException;

import CaptureTheFlagGame.GameManager;
import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Statistics;

public class RunMe {


	public static void main(String[] args) {
		GameManager gManager = new GameManager(500, 500);

		Player[] players = new Player[2]; 
		
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(new Statistics(5, 5, 5, 0));
			gManager.addPlayer(players[i],i);
		}


		
		/*for (int j = 0; j < players.length; j++) {
			System.out.println(players[j].getX() + "     "+ players[j].getY());
		}*/
			runATurn(gManager, players);
			/*players[1] .setX(60);
			players[1].setY(60);
			players[0].setY(60);
			players[0].setX(30);*/
			players[0].setHeading(2*Math.PI);
			
			
			
			
		for (int i = 0; i < 100; i++) {
			runATurn(gManager, players);
			// 
			players[0].fireBullet();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		/*for (int i = 0; i < 10000; i++) {
			runATurn(gManager, players);
			// 
			players[0].setHeading(Math.atan2(0-players[0].getY(), 0-players[0].getX() ));
		}*/
		
		
	
	}

	private static void runATurn(GameManager gManager, Player[] players) {
		//System.out.println();
		gManager.OneTurn();
		
		for (int j = 0; j < players.length; j++) {
			//System.out.println(players[j].getX() + "     "+ players[j].getY());
		}
		
		
	}

}
