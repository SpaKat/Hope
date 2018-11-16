package Client;

import Message.PlayerInfo;

public class SetPlayerInfo extends Thread {
	private Object o;
	private Client client;
	public SetPlayerInfo(Client client) {
		this.client = client;
		start();
	}
	@Override
	public void run() {
		while(client.isRunning()) {
			try {
				client.setPlayerInfo((PlayerInfo) o);
			}catch (Exception e) {
				// TODO: handle exception
			}
			try {Thread.sleep(100000);}catch(Exception e) {};
		}
	}
	public void setO(Object o) {
		this.o = o;
	}
}
