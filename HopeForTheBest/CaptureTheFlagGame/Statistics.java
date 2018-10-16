package CaptureTheFlagGame;

import java.io.Serializable;

public class Statistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -990422555623864737L;
	private double maxHealth;
	private double attack;
	private double defense;
	private double health;
	private double movespeed;
	private final double MAXRATING = 100;
	private final double SCALE = 25;

	public Statistics(double attack,double defense,double health,double movespeed) {
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.maxHealth = health;
		this.movespeed = movespeed/ SCALE;
	}
	public boolean getRateing() {
		int rateing = (int)   Math.ceil(attack + defense + health + movespeed);
		boolean b = false;
		if(rateing <= MAXRATING) {
			b = true;
		}
		return b;
	}
	public double getMaxHealth() {
		return maxHealth;
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
	public void resetHealth() {
		health = maxHealth;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}
	public void take(double damage) {
		health-= (damage - (defense/2)  );
	}
	public boolean sameStats(Statistics otherStats) {
		boolean same = false;
		if (attack == otherStats.getAttack() &&
				defense == otherStats.getDefense() &&
				health == otherStats.getHealth() &&
				movespeed == otherStats.getMovespeed()) {
			same = true;
		}
		return same;
	}
	public void setAttack(double attack) {
		this.attack = attack;
	}
	public void setDefense(double defense) {
		this.defense = defense;
	}
	public void setMovespeed(double movespeed) {
		this.movespeed = movespeed;
	}
}
