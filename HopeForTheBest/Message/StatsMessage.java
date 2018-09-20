package Message;

import java.io.Serializable;

public class StatsMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8294005556139384809L;
	private double attack;
	private double defense;
	private double health;
	private double movespeed;
	
	public StatsMessage(double attack,double defense,double health,double movespeed) {
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.movespeed = movespeed;
	}

	public double getAttack() {
		return attack;
	}

	public double getDefense() {
		return defense;
	}

	public double getHealth() {
		return health;
	}

	public double getMovespeed() {
		return movespeed;
	}
	

}
