package Message;

import java.io.Serializable;
import java.util.ArrayList;

import CaptureTheFlagGame.Team;

public class AllTeamInfo extends ArrayList<TeamInfo> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -143402959300871248L;

	public AllTeamInfo(ArrayList<Team> teams) {
		for (int i = 0; i < teams.size(); i++) {
			add(new TeamInfo(teams.get(i)));
		}
	}

}
