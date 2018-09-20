package CaptureTheFlagGUI;

import java.util.ArrayList;

import CaptureTheFlagGame.Player;
import CaptureTheFlagGame.Team;

public class CTFTeams extends ArrayList<CTFPlayers>{

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
			try {
			CTFPlayers CTFp = get(i);
			}catch (Exception e) {
				this.add(new CTFPlayers(p,ctfGame));
			}
		}
		//System.out.println("size " +team.size());
	}
}
