package CaptureTheFlagGame;

import java.util.ArrayList;

public class Player extends GameColorObject{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2761959824208880908L;
	private double heading = 0; // tan2
	private Statistics stats; //Customized
	private ArrayList<Bullet> bullets = new ArrayList<>();
	private int maxBullets = 1; 
	private boolean disconnect = false;
	private long RespawnTime = 3000;
	private long DeathStart = 0;

	public Player(Statistics stats) {
		this.stats = stats;
		setRadius(10);
	}

	public boolean isDied() {
		boolean died = false;
		if (stats.getHealth()<=0 ) {
			died = true;
			if(isSpawned()) {
				DeathStart = System.currentTimeMillis();
			}
			setSpawned(false);
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
			try {
				if (bullets.get(i).move()) {
					bullets.remove(bullets.get(i));
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public boolean readyToSpawn() {
		boolean ready = false;
		long delta = Math.abs(System.currentTimeMillis()- DeathStart);
		if (delta >= RespawnTime) {
			ready = true;
			DeathStart = 0;
		}else {
			stats.resetHealth();
		}
		return ready;
	}
	public void hit(Player otherPlayer) {
		try {
			for (int i = 0; i < bullets.size(); i++) {
				Bullet bullet = bullets.get(i);
				double deltaX = Math.abs(otherPlayer.getX() - bullet.getX());
				double deltaY = Math.abs(otherPlayer.getY() - bullet.getY());
				double range = bullet.getRadius() + otherPlayer.getRadius();
				if (deltaX<= range && deltaY <= range) {
					otherPlayer.take(bullet.getDamage());
					bullets.remove(bullet);
					bullet.remove();
				}
			}
		}catch (Exception e) {

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
	public boolean samePlayer(Player otherPLayer) {
		boolean same = false;
		if (stats.sameStats(otherPLayer.getStats())) {
			if(getColor() == otherPLayer.getColor() &&
					getX() == otherPLayer.getX() &&
					getY() == otherPLayer.getY() &&
					getHeading() == otherPLayer.getHeading()
					) {
				same = true;
			}
		}
		return same;
	}

	public boolean canFire() {
		boolean b = false;
		if (bullets.size() < maxBullets && isSpawned()) {
			b= true;
		}
		return b;
	}

	public void cleanBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			Bullet b = bullets.get(i);
			b.remove();
		}
	}
	public void setStats(Statistics stats) {
		this.stats = stats;
	}
}
