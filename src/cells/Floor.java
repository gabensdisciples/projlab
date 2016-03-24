package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;

public class Floor extends LevelObject{
	private Item item;
	
	public Floor(boolean walkable, Item item) {
		super(walkable);
		this.item = item;
		System.out.println("Floor konstruktor");
	}
	
	public ItemState hasItem() {
		System.out.println("Floor hasItem");
		if(!walkable)
			return ItemState.forbiddenArea;
		
		else if(item != null) return ItemState.gotItem;
		
		else return ItemState.noItem;
	}
	
	public void interactCharacter(Character c) {
		System.out.println("Floor interactCharacter");
		if(walkable)
			c.setPosition(this);
		
		//TODO: szekvencia valtozott, dokumentalni kell!
		//esetleges problema: parral rendelkezo csillagkapurol falnak megy
		// -> atkerul a parjara
		else c.getPosition().interactCharacter(c);
	}
	
	public void interactBullet(Bullet b) {
		System.out.println("Floor interactBullet");
		
		if(walkable)
			b.setPosition(this);
		
		else b.die();
	}
	
	@Override
	public void getItem(Character c) {
		System.out.println("Floor getItem");
		item.pickUp(c);
	}
	
	@Override
	public void placeItem(Item item) {
		System.out.println("Floor placeItem");
		this.item = item;
	}
}
