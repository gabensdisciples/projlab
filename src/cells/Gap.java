package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;

public class Gap extends LevelObject {
	public Gap() {
		super(false);
		System.out.println("Gap konstruktor");
	}
	
	public ItemState hasItem() {
		System.out.println("Gap hasItem");
		return null;
	}
	
	public void interactCharacter(Character c) {
		System.out.println("Gap interactCharacter");
		c.die();
	}
	
	public void interactBullet(Bullet b) {
		System.out.println("Gap interactBullet");
		b.setPosition(this);
	}
}
