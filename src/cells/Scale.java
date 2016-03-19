package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;

public class Scale extends LevelObject {
	//Item lett a Box helyett, dokumnetalni kell!
	private Item item;
	private boolean active;
	private Door door;
	
	public Scale(Door door) {
		super(true);
		item = null;
		active = false;
		this.door = door;
		System.out.println("Scale konstruktor");
	}
	
	//Valtozott a szignatura, dokumentalni kell!
	public void setDoorState(boolean open) {
		System.out.println("Scale openDoor");
	}
	
	public void setActive(boolean active) {
		System.out.println("Scale setActive");
	}
	
	public ItemState hasItem() {
		System.out.println("Scale hasItem");
		return null;
	}
	
	public void interactCharacter(Character c) {
		System.out.println("Scale interactCharacter");
		c.setPosition(this);
	}
	
	public void interactBullet(Bullet b) {
		System.out.println("Scale interactBullet");
		b.setPosition(this);
	}
	
	@Override
	public void getItem(Character c) {
		System.out.println("Scale getItem");
		item.pickUp(c);
	}
	
	@Override
	public void placeItem(Item item) {
		System.out.println("Scale placeItem");
		setDoorState(true);
	}
	
}
