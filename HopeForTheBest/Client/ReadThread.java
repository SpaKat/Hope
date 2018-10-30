package Client;

import Message.GameInfo;

public class ReadThread extends Thread{

	private Client client;
	public ReadThread(Client client) {
		this.client = client;
		setName("READ Cilent thread");
		start();
	}
	@Override
	public void run(){
		while(client.isRunning()) {
			try {
				Object o = client.getIn().readObject();
				switch (o.getClass().getSimpleName()) {
				case "GameInfo":
					client.setGameInfo((GameInfo) o);
					break;
				default:
					break;
				}
				try {Thread.sleep(1);}catch(Exception e) {};
			}catch (Exception e) {
				//	e.printStackTrace();
			}
		}
	}
}
