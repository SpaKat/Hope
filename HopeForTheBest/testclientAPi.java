import java.io.IOException;

import CaptureTheFlagGame.Statistics;
import Client.ClientAPI;
import Message.FlagInfo;
import Message.PlayerInfo;

public class testclientAPi extends Thread{

	public static void main(String[] args) {
		for (int j = 0; j < 1; j++) {
			for (int i = 0; i < 1; i++) {
				new testclientAPi(j);
			}
		}
	}
	int teamId; 
	public testclientAPi(int i){
		teamId = i;
		start();	
	}
	@Override
	public void run() {
		test();
	}
	private void test() {
		ClientAPI c= new ClientAPI("127.0.0.1");

		Statistics stats = new Statistics(1, 1, 1, 85);

		double heading = Math.PI;

		try {
			c.connect(stats, teamId);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
		try {
			c.sendHeading(heading);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		try {

			while(c.isAlive()) {
				c.requestPlayerInfo();
				c.requestGameInfo();
				c.fire();
				try {
					FlagInfo flag = c.getTeam(Math.abs(1-teamId)).getFlag(); 
					PlayerInfo player = c.getPlayerInfo();
					if(Math.abs(flag.getX()-player.getX())  >1 && Math.abs(flag.getY() - player.getY() ) > 1) {
						c.sendHeading( Math.atan2( flag.getY() - player.getY() ,flag.getX()-player.getX()  ));
					//	System.out.println(Math.atan2( c.getTeam(1).getFlag().getY() - c.getTeam(1).getFlag().getY() ,flag.getX()-player.getX()  ));
					}else {
						c.sendHeading( Math.atan2( c.getTeam(teamId).getHomeBase().getY() - player.getY() ,c.getTeam(teamId).getHomeBase().getX()-player.getX()  ));
					//	System.out.println(Math.atan2( c.getTeam(0).getFlag().getY() - player.getY() ,c.getTeam(0).getFlag().getX()-player.getX()  ));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
/*
				switch (scan.nextLine()) {
				case "s":
					c.sendHeading(Math.PI/2);
					break;
				case "d":
					c.sendHeading(0);
					break;
				case "w":
					c.sendHeading(3*Math.PI/2);
					break;
				case "a":
					c.sendHeading(Math.PI);
					break;
				case " ":
					c.fire();
					break;
				default:
					break;
				}
*/

				try {Thread.sleep(17);}catch (Exception e) {}
			}
		}catch (Exception e) {
			// TODO: handle exception
			//	e.printStackTrace();
		}
		//System.out.println("TEST CLIENT DIED");
		try {
			c.end();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
