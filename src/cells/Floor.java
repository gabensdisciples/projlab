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
		Logger.Log("Floor konstruktor");
		Logger.Logout();
	}
	
	public ItemState hasItem() {
		Logger.Log("Floor hasItem");
		if(!walkable){
			Logger.Logout();
			return ItemState.forbiddenArea;
		}
		else if(item != null){ 
			Logger.Logout();
			return ItemState.gotItem;
		}
		else {
			Logger.Logout();
			return ItemState.noItem;
		}
	}
	
	public void interactCharacter(Character c) {
		Logger.Log("Floor interactCharacter");
		if(walkable)
			c.setPosition(this);
		Logger.Logout();
	}
	
	public void interactBullet(Bullet b) {
		Logger.Log("Floor interactBullet");
		
		if(walkable)
			b.setPosition(this);
		else b.die();
		Logger.Logout();
	}
	
	@Override
	public void getItem(Character c) {
		Logger.Log("Floor getItem");
		item.pickUp(c);
		item = null;
		Logger.Logout();
	}
	
	@Override
	public void placeItem(Item item) {
		Logger.Log("Floor placeItem");
		this.item = item;
		Logger.Logout();
	}
}
