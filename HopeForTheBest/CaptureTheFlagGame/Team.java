package CaptureTheFlagGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Team extends ArrayList<Player> implements Serializable {


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
	private Gameboard gameboard;
	public Team(int id, Gameboard gameboard) {
		this.id = id;
		this.gameboard = gameboard;
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

	public void spawnPlayers() {
		for (int i = 0; i < size(); i++) {
			Player player = get(i);
			if (!player.isSpawned() && player.readyToSpawn()) {
				Random rn = new Random();
				spawnAtHome( player, rn);
				player.setSpawned(true);
			}
		}
	}

	private void spawnAtHome(Player player, Random rn) {
		switch (id) {
		case 0:
			player.setX(totalOffset + rn.nextInt((int) homeBase.getRadius()));
			player.setY(totalOffset + rn.nextInt((int) homeBase.getRadius()));
			break;
		case 1:
			player.setX(gameboard.getX() - rn.nextInt((int) homeBase.getRadius()));
			player.setY(gameboard.getY() - rn.nextInt((int) homeBase.getRadius()));
			break;
		case 2:
			player.setX(gameboard.getX() - rn.nextInt((int) homeBase.getRadius()));
			player.setY(totalOffset + rn.nextInt((int) homeBase.getRadius()));
			break;
		case 3:
			player.setX(totalOffset + rn.nextInt((int) homeBase.getRadius()));
			player.setY(gameboard.getY() - rn.nextInt((int) homeBase.getRadius()));
			break;
		}
	}

	protected void spawnHomeBase() {
		if (!homeBase.isSpawned()) {
			switch (id) {
			case 0:
				homeBase.setX(totalOffset);
				homeBase.setY(totalOffset);
				break;
			case 1:
				homeBase.setX(gameboard.getX() - totalOffset);
				homeBase.setY(gameboard.getY() - totalOffset);
				break;
			case 2:
				homeBase.setX(gameboard.getX() - totalOffset);
				homeBase.setY(totalOffset);
				break;
			case 3:
				homeBase.setX(totalOffset);
				homeBase.setY(gameboard.getY() - totalOffset);
				break;
			}
			homeBase.setSpawned(true);
		}
	}

	protected void spawnFlags() {
		if (!flag.isSpawned()) {
			double offset = homeBase.getRadius();
			switch (id) {
			case 0:
				flag.setX(offset);
				flag.setY(offset);
				break;
			case 1:
				flag.setX(gameboard.getX() - offset);
				flag.setY(gameboard.getY() - offset);
				break;
			case 2:
				flag.setX(gameboard.getX() - offset);
				flag.setY(offset);
				break;
			case 3:
				flag.setX(offset);
				flag.setY(gameboard.getY() - offset);
				break;
			}
			flag.setSpawned(true);
		}
	}



	public void movePlayers() {
		for (int i = 0; i <size(); i++) {
			Player player = get(i);
			double moveX = player.getX() + ( player.getMoveSpeed() * Math.cos(player.getHeading()) );
			double moveY = player.getY() + ( player.getMoveSpeed() * Math.sin(player.getHeading()) );
			if (moveX < 0  ) {
				moveX = 0;
			}
			if (moveX > gameboard.getX()) {
				moveX = gameboard.getX();
			}
			if (moveY < 0) {
				moveY = 0;
			}
			if (moveY > gameboard.getY()) {
				moveY = gameboard.getY();
			}
			player.setX(moveX);
			player.setY(moveY);
			player.moveBullets();
		}
	}

	public void moveFlag() {
		flag.move();
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
		if (deltaX < range && deltaY < range && !player.isDied() && player.isSpawned()) {
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
	public void resetFlag() {
		flag.reset();
		spawnFlags();
	}
	public int getPoints() {
		return points;
	}
	public HomeBase getHomeBase() {
		return homeBase;
	}

	public void reset() {
		for (int i = 0; i < size(); i++) {
			Player player = get(i);
			spawnAtHome(player, new Random());
		}
		points = 0;
		spawnFlags();
	}

	public void resetForSize() {
		homeBase.setSpawned(false);
		spawnHomeBase();
		flag.setSpawned(false);
		spawnFlags();

	}

	public boolean has(Object o) {
		boolean itdoes = false;
		try{
			for (int i = 0; i < size(); i++) {

				if (get(i).samePlayer((Player) o)) {
					itdoes = true;
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return itdoes;
	}

	public void cleanPlayers() {
		for (int i = 0; i < size(); i++) {
			Player p = get(i);
			if(p.isDisconnect()) {
				p.cleanBullets();
				remove(p);
			}
		}
	}
}
