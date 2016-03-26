package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import logger.Logger;

public class Door extends LevelObject{
	
	public Door() {
		super(false);
		Logger.Log("Door konstruktor");
		Logger.Logout();
	}
	
	public ItemState hasItem() {
		Logger.Log("Door hasItem");
		Logger.Logout();
		return ItemState.FORBIDDENAREA;
	}
	
	public void interactCharacter(Character c) {
		Logger.Log("Door interactCharacter");

		if(walkable)
			c.setPosition(this);
		Logger.Logout();
	}
	
	public void interactBullet(Bullet b) {
		Logger.Log("Door interactBullet");
		
		if(walkable)
			b.setPosition(this);
		else
			b.die();
		Logger.Logout();
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
		Logger.Log("Door setWalkable");
		Logger.Logout();
	}
}
