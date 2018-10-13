package Message;

import java.io.Serializable;
import java.util.ArrayList;

import com.sun.org.glassfish.external.probe.provider.StatsProviderInfo;

import CaptureTheFlagGame.Player;

public class PlayerInfo extends ColorObjectInfo implements Serializable {
	/**
	 * 
	 */
	private boolean died;
	private ArrayList<BulletInfo> bullets;
	private StatsInfo stats;
	private double heading;
	private static final long serialVersionUID = -6220344386203542341L;
	public PlayerInfo(Player player) {
		setColor(player.getColor());
		setRadius(player.getRadius());
		setSpawned(player.isSpawned());
		setX(player.getX());
		setY(player.getY());
		heading = player.getHeading();
		died = player.isDied();
		bullets = new ArrayList<>();
		for (int i = 0; i < player.getBullets().size(); i++) {
			bullets.add(new BulletInfo(player.getBullets().get(i)));
		}
		stats = new StatsInfo(player.getStats());
	}
	public StatsInfo getStats() {
		return stats;
	}
	public boolean isDied() {
		return died;
	}
	public ArrayList<BulletInfo> getBullets() {
		return bullets;
	}
}
