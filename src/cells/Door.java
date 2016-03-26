package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import logger.Logger;

public class Door extends LevelObject{
	
	public Door() {
		super(false);
		Logger.log("Door konstruktor");
		Logger.logout();
	}
	
	public ItemState hasItem() {
		Logger.log("Door hasItem");
		Logger.logout();
		return ItemState.FORBIDDENAREA;
	}
	
	public void interactCharacter(Character c) {
		Logger.log("Door interactCharacter");

		if(walkable)
			c.setPosition(this);
		Logger.logout();
	}
	
	public void interactBullet(Bullet b) {
		Logger.log("Door interactBullet");
		
		if(walkable)
			b.setPosition(this);
		else
			b.die();
		Logger.logout();
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
		Logger.log("Door setWalkable");
		Logger.logout();
	}
}
