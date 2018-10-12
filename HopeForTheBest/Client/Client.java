package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import Message.Fire;
import Message.Heading;
import Message.StatsMessage;
import Message.Teamid;

public class Client extends Thread {


	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public Client() {
		start();
	}

	@Override
	public void run() {
		try {
			//System.out.println(InetAddress.getLocalHost().getHostAddress());
			socket = new Socket(Inet4Address.getLocalHost().getHostAddress(), 8008);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			nor();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//System.out.println("WHAT!?!?!?!");
		}
		//while(true)				try{Thread.sleep(1);}catch (Exception e) {}

	}

	private void nor() throws IOException {
		out.writeObject(new StatsMessage(5, 5, 5,80));
		out.flush();
		out.writeObject(new Teamid((int) (Math.random()*4)));
		out.flush();
		try{Thread.sleep(50);}catch(Exception d ) {}
		double heading = Math.PI/2;
		while (true) {
			//	heading += Math.PI*2 / 180;
			//	System.out.println(heading);
			out.writeObject(new Heading(heading+=Math.PI/20));
			out.reset();;
			/*Scanner scan = new Scanner(System.in);
			switch (scan.next()) {
			case "w":
				heading = Math.PI*3/2.0;
				break;
			case "a":
				heading = Math.PI;
				break;
			case "s":
				heading = Math.PI/2.0;
				break;
			case "d":
				heading = Math.PI*2;
				break;
			case "f":
			out.writeObject(new Fire());
			out.reset();
				
				break;
			}*/
			try{Thread.sleep(50);}catch(Exception d ) {}
		}
	}





	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			new Client();
		}
		
	}

}
