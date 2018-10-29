package Server;

import CaptureTheFlagGame.Player;

public class ReadFireBullet extends Thread{
	private Player player;
	public ReadFireBullet(Player player) {
		this.player = player;
		setName("FIRE Bullet");
		start();
	}

	@Override
	public void run() {
		if(player.canFire()) {
			player.fireBullet();
		}
	}

}
