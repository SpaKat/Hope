package CaptureTheFlagGame;

public class Flag extends GameColorObject{

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
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	public void move() {
		if (player != null) {
			if (player.isDied()) {
				player = null;
			}else {
				setX(player.getX());
				setY(player.getY());
			}
		}

	}

	public void reset() {
		player = null;
		taken = false;
		setSpawned(false);
	}
}
