package Message;

import java.io.Serializable;
import java.util.ArrayList;

import CaptureTheFlagGame.Team;

public class TeamInfo extends ArrayList<PlayerInfo> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4034931022822340652L;
	private FlagInfo flag;
	private HomeBaseInfo homeBase;
	private int Id;
	public TeamInfo(Team team) {
		flag = new FlagInfo(team.getFlag());
		homeBase = new HomeBaseInfo(team.getHomeBase());
		for (int i = 0; i < team.size(); i++) {
			add(new PlayerInfo(team.get(i)));
		}
		Id = team.getId();
	}
	public int getId() {
		return Id;
	}
	public FlagInfo getFlag() {
		return flag;
	}
	public HomeBaseInfo getHomeBase() {
		return homeBase;
	}
}
