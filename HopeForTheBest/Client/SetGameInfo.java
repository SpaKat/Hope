package Client;

import Message.GameInfo;

public class SetGameInfo extends Thread {


	private Object o;
	private Client client;
	public SetGameInfo(Client client) {
		this.client = client;
		start();
	}
	@Override
	public void run() {
		while(client.isRunning()) {
			try {
				client.setGameInfo((GameInfo) o);
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {Thread.sleep(10000);}catch(Exception e) {};
		}
	}
	public void setO(Object o) {
		this.o = o;
	}

}
