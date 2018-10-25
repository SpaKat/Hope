import CaptureTheFlagGame.Statistics;
import Client.ClientAPI;

public class testclientAPi {

	public static void main(String[] args) {
		ClientAPI c= new ClientAPI("127.0.0.1");
		
		Statistics stats = new Statistics(5, 5, 5, 85);
		int teamId = 0; 
		double heading = Math.PI;
		
		c.connect(stats, teamId);
		boolean running =true;
		while(running) {
			try {
								System.out.println(c.gameBoard().getX());
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
				
		c.end();
	}

}
