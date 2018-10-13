package Message;

import java.io.Serializable;

import CaptureTheFlagGame.Statistics;

public class StatsInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3267840390487921485L;
	private double attack;
	private double defense;
	private double health;
	private double movespeed;
	private double maxHealth;
	public StatsInfo(Statistics stats) {
		this.attack = stats.getAttack();
		this.defense = stats.getDefense();
		this.health = stats.getHealth();
		this.movespeed = stats.getMovespeed();
		this.maxHealth = stats.getMaxHealth();
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
	public double getMaxHealth() {
		return maxHealth;
	}

	
}
