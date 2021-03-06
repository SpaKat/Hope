package CaptureTheFlagGame;

public class Bullet extends GameColorObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636024992484246879L;
	private double speed;
	private double heading;
	private double bulletLength = 125;
	private double damage;
	public Bullet(double x, double y,double heading, double damage) {
		speed = 10;
		setX(x);
		setY(y);
		this.damage = damage;
		this.heading = heading;
		setSpawned(true);
		setRadius(3);
	}
	public Bullet(double x, double y) {
		this(x, y, 0, 0);
	}
	public double getSpeed() {
		return speed;
	}
	public boolean move() {
		boolean done = false;
		double moveX = getX() + ( speed * Math.cos(heading) );
		double moveY = getY() + ( speed * Math.sin(heading) );
		bulletLength -= Math.abs(speed * Math.cos(heading));
		bulletLength -= Math.abs(speed * Math.sin(heading));
		if (bulletLength <=0) {
			done = true;
		}else {
			setX(moveX);
			setY(moveY);
		}		
		return done;
	}
	
	public double getDamage() {
		return damage;
	}
	
	public boolean done() {
		boolean b = false;
		if (bulletLength <= 0) {
			b =true;
		}
		return b;
	}

	public void remove() {
		bulletLength = 0;
	}
}
