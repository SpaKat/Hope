package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Message.Heading;
import Message.StatsMessage;
import Message.Teamid;

public class Client extends Thread {


	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public Client() {
		try {
			socket = new Socket("127.0.0.1", 8008);



		} catch (Exception e) {

		}
		start();
	}

	@Override
	public void run() {
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			out.writeObject(new StatsMessage(5, 5, 5, 50));
			out.flush();
			out.writeObject(new Teamid(0));
			out.flush();
			double heading = 0;
			while (true) {
				heading += Math.PI*2 / 18;
				
				out.writeObject(new Heading(heading));
				out.reset();
				try{Thread.sleep(50);}catch(Exception d ) {}
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}





	public static void main(String[] args) {
		new Client();
	}

}
