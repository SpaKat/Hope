package CaptureTheFlagGUI;

import java.util.ArrayList;

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
		
	}
}
