package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;
import items.Box;

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
		System.out.println("Scale konstruktor");
	}

	// TODO: Valtozott a szignatura, dokumentalni kell!
	public void setDoorState(boolean open) {
		System.out.println("Scale openDoor");
		door.setWalkable(open);
	}

	public void setActive(boolean active) {
		System.out.println("Scale setActive");
		this.active = active;
		setDoorState(active);
	}

	public ItemState hasItem() {
		System.out.println("Scale hasItem");
		if (box != null)
			return ItemState.GOTITEM;

		else
			return ItemState.NOITEM;
	}

	public void interactCharacter(Character c) {
		System.out.println("Scale interactCharacter");
		c.setPosition(this);
		setActive(true);
		// character = c;
	}

	public void interactBullet(Bullet b) {
		System.out.println("Scale interactBullet");
		b.setPosition(this);
	}

	@Override
	public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
		System.out.println("Scale getNeighbour");

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
		System.out.println("Scale getItem");
		box.pickUp(c);
		box = null;
		setActive(false);
	}

	@Override
	public void placeItem(Item item) {
		System.out.println("Scale placeItem");
		box = (Box) item;
		setActive(true);
	}
}
