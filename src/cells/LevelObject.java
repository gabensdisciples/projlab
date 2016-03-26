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
		Logger.Log("LevelObject konstruktor");
		Logger.Logout();
	}

	public boolean isWalkable() {
		Logger.Log("LevelObject isWalkable");
		Logger.Logout();
		return walkable;

	}

	public abstract void interactCharacter(Character c);

	public abstract void interactBullet(Bullet b);

	public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
		Logger.Log("LevelObject getNeighbour");
		Logger.Logout();
		switch (dir) {
		case North:
			return neighbourNorth;
		case East:
			return neighbourEast;
		case South:
			return neighbourSouth;
		case West:
			return neighbourWest;
		default:
			return null;
		// TODO: Dobjunk Exception-t defaultnál?
		}
	}

	public void setNeighbour(Direction dir, LevelObject l) {
		Logger.Log("LevelObject setNeighbour");

		switch (dir) {
		case North:
			neighbourNorth = l;
			break;
		case East:
			neighbourEast = l;
			break;
		case South:
			neighbourSouth = l;
			break;
		case West:
			neighbourWest = l;
			break;
		}
		Logger.Logout();
	}

	public abstract ItemState hasItem();

	public void getItem(Character c) {
		Logger.Log("LevelObject getItem");
		Logger.Logout();
	}

	public void placeItem(Item item) {
		Logger.Log("LevelObject placeItem");
		Logger.Logout();
	}

}
