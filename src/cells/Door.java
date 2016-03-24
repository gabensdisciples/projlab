package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;

public class Door extends LevelObject{
	
	public Door() {
		super(false);
		System.out.println("Door konstruktor");
	}
	
	public ItemState hasItem() {
		System.out.println("Door hasItem");
		return ItemState.forbiddenArea;
	}
	
	public void interactCharacter(Character c) {
		System.out.println("Door interactCharacter");

		if(walkable)
			c.setPosition(this);		
	}
	
	public void interactBullet(Bullet b) {
		System.out.println("Door interactBullet");
		
		if(walkable)
			b.setPosition(this);
		
		else b.die();
	}
	
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
		System.out.println("Door setWalkable");
	}
}
