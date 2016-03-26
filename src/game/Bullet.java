package game;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import logger.Logger;

public class Bullet {
	private Direction direction;
	private Color color;
	private LevelObject position;

	public Bullet(LevelObject position, Direction direction, Color color) {
		Logger.log("Bullet konstruktor");
		this.position = position;
		this.direction = direction;
		this.color = color;
		Logger.Logout();
	}

	public void fly() {
		Logger.log("Bullet fly");
		while(position.isWalkable())
		{
			position = position.getNeighbour(direction, false);
			position.interactBullet(this);
		}
		Logger.Logout();
	}

	public void setPosition(LevelObject position) {
		Logger.log("Bullet setPosition");
		this.position = position;
		Logger.Logout();
	}

	public void die() {
		Logger.log("Bullet die");
		Logger.Logout();
	}

	public StarGate createStarGate() {
		Logger.log("Bullet createStarGate");
		Logger.Logout();
		return new StarGate(color);
	}
}
