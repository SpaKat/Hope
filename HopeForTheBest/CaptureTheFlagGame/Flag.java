package CaptureTheFlagGame;

public class Flag extends GameColorObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3068979050080026308L;
	private boolean taken;
	private Player player;
	public Flag(int color) {
		super();
		taken = false;
		setColor(color);
		setRadius(7);
	}
	 
	public void gotTakenBy(Player player) {
		this.player = player;
	}
	public Player getPlayer() {
		return player;
	}
	public boolean isTaken() {
		return taken;
	}
	public void move() {
		if (player != null) {
			if (player.isDied() || player.isDisconnect()) {
				player = null;
				taken = false;
			}else {
				setX(player.getX());
				setY(player.getY());
				taken = true;
			}
		}else {
			taken = false;
		}

	}

	public void reset() {
		player = null;
		taken = false;
		setSpawned(false);
	}
}
