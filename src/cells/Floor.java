package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;
import logger.Logger;

public class Floor extends LevelObject{
	private Item item;
	
	public Floor(boolean walkable, Item item) {
		super(walkable);
		this.item = item;
		Logger.log("Floor konstruktor");
		Logger.Logout();
	}
	
	public ItemState hasItem() {
		Logger.log("Floor hasItem");
		if(!walkable){
			Logger.Logout();
			return ItemState.FORBIDDENAREA;
		}
		else if(item != null){ 
			Logger.Logout();
			return ItemState.GOTITEM;
		}
		else {
			Logger.Logout();
			return ItemState.NOITEM;
		}
	}
	
	public void interactCharacter(Character c) {
		Logger.log("Floor interactCharacter");
		if(walkable)
			c.setPosition(this);
		Logger.Logout();
	}
	
	public void interactBullet(Bullet b) {
		Logger.log("Floor interactBullet");
		
		if(walkable)
			b.setPosition(this);
		else b.die();
		Logger.Logout();
	}
	
	@Override
	public void getItem(Character c) {
		Logger.log("Floor getItem");
		item.pickUp(c);
		item = null;
		Logger.Logout();
	}
	
	@Override
	public void placeItem(Item item) {
		Logger.log("Floor placeItem");
		this.item = item;
		Logger.Logout();
	}
}
