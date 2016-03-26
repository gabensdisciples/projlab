package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;

public class Floor extends LevelObject {
	private Item item;

	public Floor(boolean walkable, Item item) {
		super(walkable);
		this.item = item;
		System.out.println("Floor konstruktor");
	}

	public ItemState hasItem() {
		System.out.println("Floor hasItem");
		if (!walkable)
			return ItemState.FORBIDDENAREA;

		else if (item != null)
			return ItemState.GOTITEM;

		else
			return ItemState.NOITEM;
	}

	public void interactCharacter(Character c) {
		System.out.println("Floor interactCharacter");
		if (walkable)
			c.setPosition(this);
	}

	public void interactBullet(Bullet b) {
		System.out.println("Floor interactBullet");

		if (walkable)
			b.setPosition(this);

		else
			b.die();
	}

	@Override
	public void getItem(Character c) {
		System.out.println("Floor getItem");
		item.pickUp(c);
		item = null;
	}

	@Override
	public void placeItem(Item item) {
		System.out.println("Floor placeItem");
		this.item = item;
	}
}
