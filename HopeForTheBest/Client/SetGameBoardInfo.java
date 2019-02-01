package Client;

import Message.GameboardInfo;

public class SetGameBoardInfo extends Thread {
	private Object o;
	private Client client;
	public SetGameBoardInfo(Client client) {
		this.client = client;
		start();
	}
	@Override
	public void run() {
		while(client.isRunning()) {
			try {
				client.setGameboardInfo((GameboardInfo) o);
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
