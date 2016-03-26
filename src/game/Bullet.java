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
		Logger.logout();
	}

	public void fly() {
		Logger.log("Bullet fly");
		while(position.isWalkable())
		{
			position = position.getNeighbour(direction, false);
			position.interactBullet(this);
		}
		Logger.logout();
	}

	public void setPosition(LevelObject position) {
		Logger.log("Bullet setPosition");
		this.position = position;
		Logger.logout();
	}

	public void die() {
		Logger.log("Bullet die");
		Logger.logout();
	}

	public StarGate createStarGate() {
		Logger.log("Bullet createStarGate");
		Logger.logout();
		return new StarGate(color);
	}
}
