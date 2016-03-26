package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;
import logger.Logger;

public abstract class LevelObject {
	// TODO: privaterol protectedek lettek a szomszedok, dokumentalni kell!
	// (a Scale override-ja miatt kell)
	protected LevelObject neighbourNorth;
	protected LevelObject neighbourEast;
	protected LevelObject neighbourSouth;
	protected LevelObject neighbourWest;
	// TODO: Protected lett, dokumentalni kell!
	protected boolean walkable;

	public LevelObject(boolean walkable) {
		this.walkable = walkable;
		Logger.log("LevelObject konstruktor");
		Logger.Logout();
	}

	public boolean isWalkable() {
		Logger.log("LevelObject isWalkable");
		Logger.Logout();
		return walkable;

	}

	public abstract void interactCharacter(Character c);

	public abstract void interactBullet(Bullet b);

	public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
		Logger.log("LevelObject getNeighbour");
		Logger.Logout();
		switch (dir) {
		case NORTH:
			return neighbourNorth;
		case EAST:
			return neighbourEast;
		case SOUTH:
			return neighbourSouth;
		case WEST:
			return neighbourWest;
		default:
			return null;
		// TODO: Dobjunk Exception-t defaultn�l?
		}
	}

	public void setNeighbour(Direction dir, LevelObject l) {
		Logger.log("LevelObject setNeighbour");

		switch (dir) {
		case NORTH:
			neighbourNorth = l;
			break;
		case EAST:
			neighbourEast = l;
			break;
		case SOUTH:
			neighbourSouth = l;
			break;
		case WEST:
			neighbourWest = l;
			break;
		}
		Logger.Logout();
	}

	public abstract ItemState hasItem();

	public void getItem(Character c) {
		Logger.log("LevelObject getItem");
		Logger.Logout();
	}

	public void placeItem(Item item) {
		Logger.log("LevelObject placeItem");
		Logger.Logout();
	}

}
