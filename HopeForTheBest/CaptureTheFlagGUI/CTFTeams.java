package CaptureTheFlagGUI;

import java.util.ArrayList;

import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Team;

public class CTFTeams extends ArrayList<CTFPlayers>{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 5963502336512454678L;
	private Team team;
	private CTFGame ctfGame;

	private CTFFlag flag;
	private CTFHomeBase homebase;
 

	public CTFTeams(Team team, CTFGame ctfGame) {
		this.team = team;
		this.ctfGame = ctfGame;
		flag = new CTFFlag(team.getFlag(),ctfGame);
		homebase = new CTFHomeBase(team.getHomeBase(),ctfGame);
	}
	public void update() {
		flag.update();
		homebase.update();

		for (int j = 0; j < size(); j++) {
			CTFPlayers CTFp = get(j);
			CTFp.update();
		}
		for (int i = 0; i < team.size(); i++) {
			Player p = team.get(i);
			boolean addPlayer = true;
			for (int j = 0; j < size(); j++) {
				CTFPlayers CTFp = get(j);
				if (CTFp.getPlayer().samePlayer(p)) {
					addPlayer = false;
				}
			}
			if(addPlayer) {
				this.add(new CTFPlayers(p,ctfGame));
			}
		}
	}
	public void updateClient() {
		flag.updateClient();
		homebase.updateClient();
		for (int i = 0; i < size(); i++) {
			get(i).updateClient();
		}
	}
}
