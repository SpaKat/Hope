package Message;

import java.io.Serializable;

public class NewPlayer implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -2375738388889310923L;
	private int id;
	private double attack;
	private double defense;
	private double health;
	private double movespeed;
	
	public NewPlayer(int id,double attack,double defense,double health,double movespeed) {
		this.id = id;
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.movespeed = movespeed;
	}
	public int getId() {
		return id;
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
