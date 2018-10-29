import java.io.IOException;

import CaptureTheFlagGame.Statistics;
import Client.ClientAPI;

public class testclientAPi extends Thread{

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new testclientAPi();
		}
	}
	public testclientAPi() {
		start();
	}
	@Override
	public void run() {
		test();
	}
	private void test() {
		ClientAPI c= new ClientAPI("127.0.0.1");

		Statistics stats = new Statistics(1, 1, 1, 85);
		int teamId = 1; 
		double heading = Math.PI;

		try {
			c.connect(stats, teamId);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			c.sendHeading(heading);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean running =true;
		try {
			while(running) {
				c.fire();
				c.sendHeading(heading+=Math.PI/36*Math.random());
				try {Thread.sleep(17);}catch (Exception e) {}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("TEST CLIENT DIED");
		try {
			c.end();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
