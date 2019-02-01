package Client;

import Message.AllTeamInfo;

public class SetAllTeamInfo extends Thread {
	private Object o;
	private Client client;
	public SetAllTeamInfo(Client client) {
		this.client = client;
		start();
	}
	@Override
	public void run() {
		while(client.isRunning()) {
			try {
				client.setAllTeamInfo((AllTeamInfo) o);
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
