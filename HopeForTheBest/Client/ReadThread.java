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
		SetGameBoardInfo gameboardinfo = new SetGameBoardInfo(client);
		SetAllTeamInfo allTeaminfo = new SetAllTeamInfo(client);
		SetPlayerInfo playerinfo = new SetPlayerInfo(client);
		
		while(client.isRunning()) {
			try {
				Object o = client.getIn().readObject();
				switch (o.getClass().getSimpleName()) {
				case "GameboardInfo":
					gameboardinfo.setO(o);
					gameboardinfo.interrupt();
					break;
				case "AllTeamInfo":
					allTeaminfo.setO(o);
					allTeaminfo.interrupt();
					break;
				case "PlayerInfo":
					playerinfo.setO(o);
					playerinfo.interrupt();
					break;
				default:
					break;
				}
				try {Thread.sleep(17);}catch(Exception e) {};
			}catch (Exception e) {
				//	e.printStackTrace();
			}
		}
		gameboardinfo.interrupt();
		playerinfo.interrupt();
	}
}
