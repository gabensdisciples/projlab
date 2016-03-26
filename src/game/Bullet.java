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
		Logger.Log("Bullet konstruktor");
		this.position = position;
		this.direction = direction;
		this.color = color;
		Logger.Logout();
	}

	public void fly() {
		Logger.Log("Bullet fly");
		while(position.isWalkable())
		{
			position = position.getNeighbour(direction, false);
			position.interactBullet(this);
		}
		Logger.Logout();
	}

	public void setPosition(LevelObject position) {
		Logger.Log("Bullet setPosition");
		this.position = position;
		Logger.Logout();
	}

	public void die() {
		Logger.Log("Bullet die");
		Logger.Logout();
	}

	public StarGate createStarGate() {
		Logger.Log("Bullet createStarGate");
		Logger.Logout();
		return new StarGate(color);
	}
}
