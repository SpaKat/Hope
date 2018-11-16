package Client;

public class ReadThread extends Thread{

	private Client client;
	public ReadThread(Client client) {
		this.client = client;
		setName("READ Cilent thread");
		start();
	}
	@Override
	public void run(){
		SetGameInfo gameinfo = new SetGameInfo(client);
		SetPlayerInfo playerinfo = new SetPlayerInfo(client);
		
		while(client.isRunning()) {
			try {
				Object o = client.getIn().readObject();
				switch (o.getClass().getSimpleName()) {
				case "GameInfo":
					gameinfo.setO(o);
					gameinfo.interrupt();
					break;
				case "PlayerInfo":
					playerinfo.setO(o);
					playerinfo.interrupt();
					//client.setPlayerInfo((PlayerInfo) o);
					break;
				default:
					break;
				}
				try {Thread.sleep(17);}catch(Exception e) {};
			}catch (Exception e) {
				//	e.printStackTrace();
			}
		}
		gameinfo.interrupt();
		playerinfo.interrupt();
	}
}
