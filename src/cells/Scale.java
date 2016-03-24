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
	//TODO: uj tagvaltozo, dokumentalni kell!
	private Character character;
	
	public Scale(Door door) {
		super(true);
		box = null;
		character = null;
		active = false;
		this.door = door;
		System.out.println("Scale konstruktor");
	}
	
	//TODO: Valtozott a szignatura, dokumentalni kell!
	public void setDoorState(boolean open) {
		System.out.println("Scale openDoor");
		door.setWalkable(open);
	}
	
	public void setActive(boolean active) {
		System.out.println("Scale setActive");
		this.active = active;
	}
	
	public ItemState hasItem() {
		System.out.println("Scale hasItem");
		if(box != null)
			return ItemState.gotItem;
		
		else return ItemState.noItem;
	}
	
	public void interactCharacter(Character c) {
		System.out.println("Scale interactCharacter");
		c.setPosition(this);
		character = c;
	}
	
	public void interactBullet(Bullet b) {
		System.out.println("Scale interactBullet");
		b.setPosition(this);
	}
	
	@Override
	public LevelObject getNeighbour(Direction dir){
		System.out.println("Scale getNeighbour");
		//nincs doboz, de van karakter
		if(box == null && character != null) {
			setDoorState(false);
			character = null;
		}

		switch(dir) {
			case North : return neighbourNorth;
			case East : return neighbourEast;
			case South : return neighbourSouth;
			case West : return neighbourWest;
			default : return null;
			//TODO: Dobjunk Exception-t defaultnál?
		}
	}
	
	@Override
	public void getItem(Character c) {
		System.out.println("Scale getItem");
		box.pickUp(c);
	}
	
	@Override
	public void placeItem(Item item) {
		System.out.println("Scale placeItem");
		box = (Box) item;
		setDoorState(true);
	}
	
}
