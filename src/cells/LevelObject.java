package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;

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
		System.out.println("LevelOject konstruktor");
	}

	public boolean isWalkable() {
		return walkable;
	}

	public abstract void interactCharacter(Character c);

	public abstract void interactBullet(Bullet b);

	public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
		System.out.println("LevelObject getNeighbour");

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
		// TODO: Dobjunk Exception-t defaultnál?
		}
	}

	public void setNeighbour(Direction dir, LevelObject l) {
		System.out.println("LevelObject setNeighbour");

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
	}

	public abstract ItemState hasItem();

	public void getItem(Character c) {
		System.out.println("LevelObject getItem");
	}

	public void placeItem(Item item) {
		System.out.println("LevelObject placeItem");
	}

}
