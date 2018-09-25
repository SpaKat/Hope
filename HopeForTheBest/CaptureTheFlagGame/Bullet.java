package CaptureTheFlagGame;

public class Bullet extends GameColorObject {

	private double speed;
	private double heading;
	private double bulletLength = 75;
	private double damage;
	public Bullet(double x, double y,double heading, double damage) {
		speed = 5;
		setX(x);
		setY(y);
		this.damage = damage;
		this.heading = heading;
		setSpawned(true);
		setRadius(3);
	}
	
	public double getSpeed() {
		return speed;
	}
	public boolean move() {
		boolean done = false;
		double moveX = getX() + ( speed * Math.cos(heading) );
		double moveY = getY() + ( speed * Math.sin(heading) );
		bulletLength -= (speed * Math.cos(heading));
		bulletLength -= (speed * Math.sin(heading));
		if (bulletLength <=0) {
			done = true;
		}else {
			setX(moveX);
			setY(moveY);
		}
	//	System.out.println("Bullet.java  x: " + getX() + "  y: " + getY() + "   Bullet length: "  + bulletLength);
		
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
}
