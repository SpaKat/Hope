package CaptureTheFlagGame;



public class Statistics {

	private double maxHealth;
	private double attack;
	private double defense;
	private double health;
	private double movespeed;
	private final double MAXRATING = 100;
	private final double SCALE = 50;
	
	
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
	public void take(double damage) {
		health-= (damage - (defense/2)  );
	}
}
