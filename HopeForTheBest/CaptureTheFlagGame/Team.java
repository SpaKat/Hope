package CaptureTheFlagGame;

import java.util.ArrayList;
import java.util.Random;

public class Team extends ArrayList<Player> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8431792008377614482L;
	private Flag flag;
	private HomeBase homeBase;
	private int color; //hex
	private int maxPlayers;
	private int id;
	private double totalOffset =0;
	private int points = 0;
	public Team(int id) {
		this.id = id;
		Random rn = new Random();
		color = rn.nextInt(0xFFFFFF+1) ;
		flag = new Flag(color);
		homeBase = new HomeBase(color);
		maxPlayers = 20;
	}

	public int getId() {
		return id;
	}
	public int getMaxPlayers() {
		return maxPlayers;
	}
	public int getColor() {
		return color;
	}

	public void spawnPlayers(double x, double y) {
		for (int i = 0; i < size(); i++) {
			Player player = get(i);
			if (!player.isSpawned() && player.readyToSpawn()) {
				Random rn = new Random();
				switch (id) {
				case 0:
					player.setX(totalOffset + rn.nextInt((int) homeBase.getRadius()));
					player.setY(totalOffset + rn.nextInt((int) homeBase.getRadius()));
					break;
				case 1:
					player.setX(x - rn.nextInt((int) homeBase.getRadius()));
					player.setY(y - rn.nextInt((int) homeBase.getRadius()));
					break;
				case 2:
					player.setX(x - rn.nextInt((int) homeBase.getRadius()));
					player.setY(totalOffset + rn.nextInt((int) homeBase.getRadius()));
					break;
				case 3:
					player.setX(totalOffset + rn.nextInt((int) homeBase.getRadius()));
					player.setY(y - rn.nextInt((int) homeBase.getRadius()));
					break;
				}
				player.setSpawned(true);
			}
		}
	}

	protected void spawnHomeBase(double x, double y) {
		if (!homeBase.isSpawned()) {
			switch (id) {
			case 0:
				homeBase.setX(totalOffset);
				homeBase.setY(totalOffset);
				break;
			case 1:
				homeBase.setX(x - totalOffset);
				homeBase.setY(y - totalOffset);
				break;
			case 2:
				homeBase.setX(x - totalOffset);
				homeBase.setY(totalOffset);
				break;
			case 3:
				homeBase.setX(totalOffset);
				homeBase.setY(y - totalOffset);
				break;
			}
			homeBase.setSpawned(true);
		}
	}

	protected void spawnFlags(double x, double y ) {
		if (!flag.isSpawned()) {
			double offset = homeBase.getRadius();
			switch (id) {
			case 0:
				flag.setX(offset);
				flag.setY(offset);
				break;
			case 1:
				flag.setX(x - offset);
				flag.setY(y - offset);
				break;
			case 2:
				flag.setX(x - offset);
				flag.setY(offset);
				break;
			case 3:
				flag.setX(offset);
				flag.setY(y - offset);
				break;
			}
			flag.setSpawned(true);
		}
	}



	public void movePlayers(double maxX, double maxY) {
		for (int i = 0; i <size(); i++) {
			Player player = get(i);
			double moveX = player.getX() + ( player.getMoveSpeed() * Math.cos(player.getHeading()) );
			double moveY = player.getY() + ( player.getMoveSpeed() * Math.sin(player.getHeading()) );
			if (moveX < 0  ) {
				moveX = 0;
			}
			if (moveX > maxX) {
				moveX = maxX;
			}
			if (moveY < 0) {
				moveY = 0;
			}
			if (moveY > maxY) {
				moveY = maxY;
			}
			player.setX(moveX);
			player.setY(moveY);
			player.moveBullets();
		}
	}

	public void moveFlag() {
		flag.move();
	//	System.out.println("Team.java  Flag x: " + flag.getX() + "   y: "+ flag.getY());
	}

	public boolean takenFlag() {
		return flag.isTaken();
	}

	public void takeTheFlag(Player player) {
		double deltaX = Math.abs( player.getX() - flag.getX()  );
		double deltaY = Math.abs( player.getY() - flag.getY()  );
		double pickup = (player.getRadius() + flag.getRadius()) / 2;
		if (deltaX < pickup && deltaY < pickup) {
			flag.gotTakenBy(player);
		}
	}

	public boolean scorePoint(Team otherTeam) {
		boolean b = false;
		if(contains(otherTeam.getFlag().getPlayer())){
			if(ScorePoint(otherTeam.getFlag().getPlayer() )) {
				b = true;
			}
		}
		return b;
	}	
	private boolean ScorePoint(Player player) {
		double deltaX = Math.abs(player.getX() - homeBase.getX());
		double deltaY = Math.abs(player.getY() - homeBase.getY());
		double range = homeBase.getRadius();
		boolean b = false;
		if (deltaX < range && deltaY < range) {
			b = true;
		}
		return b;
	}

	public Flag getFlag() {
		return flag;
	}

	public void addPoint() {
		points++;
	}
	public void resetFlag(double x, double y) {
		flag.reset();
		spawnFlags(x, y);
	}
	public int getPoints() {
		return points;
	}
	public HomeBase getHomeBase() {
		return homeBase;
	}
}
