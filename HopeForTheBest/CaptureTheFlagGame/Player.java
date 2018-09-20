package CaptureTheFlagGame;

import java.util.ArrayList;

public class Player extends GameColorObject{


	private double heading = 0; // tan2
	private Statistics stats; //Customized
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private int maxBullets = 1; 
	private boolean disconnect = false;
	private long RespawnTime = 3000;
	private long DeathStart;

	public Player(Statistics stats) {
		this.stats = stats;
		setRadius(10);
	}

	public boolean isDied() {
		boolean died = false;
		if (stats.getHealth()<=0) {
			died = true;
			setSpawned(false);
			DeathStart = System.currentTimeMillis();
		}
		return died;

	}

	public double getHeading() {
		return heading;
	}
	public void setHeading(double heading) {
		this.heading = heading;
	}
	public Statistics getStats() {
		return stats;
	}

	public double getMoveSpeed() {
		return stats.getMovespeed();
	}
	public void fireBullet() {
		if (bullets.size() < maxBullets && isSpawned()) {
			bullets.add(new Bullet(getX(), getY(), heading,stats.getAttack()));
		}
	}

	public void moveBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			if (bullets.get(i).move()) {
				bullets.remove(bullets.get(i));
			}
		}
	}

	public boolean readyToSpawn() {
		boolean ready = true;
		long delta = Math.abs(System.currentTimeMillis()- DeathStart);
		if (delta <= RespawnTime) {
			ready = false;
		}
		return ready;
	}
	public void hit(Player otherPlayer) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			double deltaX = Math.abs(otherPlayer.getX() - bullet.getX());
			double deltaY = Math.abs(otherPlayer.getY() - bullet.getY());
			double range = bullet.getRadius() + otherPlayer.getRadius();
			if (deltaX<= range && deltaY <= range) {
				otherPlayer.take(bullet.getDamage());
				bullets.remove(bullet);
				System.out.println("PLayer.java hit otherPlayer is died: " + otherPlayer.isDied());
			}
		}

	}

	private void take(double damage) {
		stats.take(damage);
	}
	public void setDisconnect(boolean disconnect) {
		this.disconnect = disconnect;
	}
	public boolean isDisconnect() {
		return disconnect;
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
