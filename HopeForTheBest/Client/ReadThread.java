package Client;

import Message.GameInfo;

public class ReadThread {

	private Client client;
	public ReadThread(Client client) {
		this.client = client;
	}
	public void read(){
		try {
			Object o = client.getIn().readObject();
			switch (o.getClass().getSimpleName()) {
			case "GameInfo":
				client.setGameInfo((GameInfo) o);;
				break;
			default:
				System.out.println(o.getClass().getSimpleName());
				break;
			}
		}catch (Exception e) {
		//	e.printStackTrace();
		}
	}
}
