package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;
import items.Box;
import logger.Logger;

public class Scale extends LevelObject {
	private Box box;
	private boolean active;
	private Door door;
	// TODO: uj tagvaltozo, dokumentalni kell!
	// private Character character;

	public Scale(Door door) {
		super(true);
		box = null;
		// character = null;
		active = false;
		this.door = door;
		Logger.Log("Scale konstruktor");
		Logger.Logout();
	}

	// TODO: Valtozott a szignatura, dokumentalni kell!
	public void setDoorState(boolean open) {
		Logger.Log("Scale openDoor");
		door.setWalkable(open);
		Logger.Logout();
	}

	public void setActive(boolean active) {
		Logger.Log("Scale setActive");
		this.active = active;
		setDoorState(active);
		Logger.Logout();
	}

	public ItemState hasItem() {
		Logger.Log("Scale hasItem");
		Logger.Logout();
		if (box != null) {
			return ItemState.GOTITEM;
		} else {
			return ItemState.NOITEM;
		}
	}

	public void interactCharacter(Character c) {
		Logger.Log("Scale interactCharacter");
		c.setPosition(this);
		setActive(true);
		// character = c;
		Logger.Logout();
	}

	public void interactBullet(Bullet b) {
		Logger.Log("Scale interactBullet");
		b.setPosition(this);
		Logger.Logout();
	}

	@Override
	public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
		Logger.Log("Scale getNeighbour");

		if (characterCalled && box == null) {
			Character dummy = new Character(this, null, dir);
			LevelObject neighbour = null;
			switch (dir) {
			case NORTH:
				neighbour = neighbourNorth;
				break;
			case EAST:
				neighbour = neighbourEast;
				break;
			case SOUTH:
				neighbour = neighbourSouth;
				break;
			case WEST:
				neighbour = neighbourWest;
				break;
			}

			neighbour.interactCharacter(dummy);
			// ha sikeres lesz a lepes
			if (dummy.getPosition() != this) {
				setActive(false);
				// character = null;
			}
		}
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
		// TODO: Dobjunk Exception-t defaultnál?
		}
	}

	@Override
	public void getItem(Character c) {
		Logger.Log("Scale getItem");
		box.pickUp(c);
		box = null;
		setActive(false);
		Logger.Logout();
	}

	@Override
	public void placeItem(Item item) {
		Logger.Log("Scale placeItem");
		box = (Box) item;
		setActive(true);
		Logger.Logout();
	}
}
