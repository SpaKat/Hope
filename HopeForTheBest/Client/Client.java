package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
			socket = new Socket("127.0.0.1", 8008);
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
		out.writeObject(new StatsMessage(5, 5, 5, 80));
		out.reset();
		out.writeObject(new Teamid(0));
		out.reset();
		double heading = 0;
		while (true) {
		//	heading += Math.PI*2 / 180;
		//	System.out.println(heading);
			out.writeObject(new Heading(heading));
			out.reset();
			out.writeObject(new Fire());
			out.reset();
			try{Thread.sleep(50);}catch(Exception d ) {}
			
		}
	}





	public static void main(String[] args) {
		new Client();
	}

}
