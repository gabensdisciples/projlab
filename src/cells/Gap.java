package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import logger.Logger;

public class Gap extends LevelObject {
	public Gap() {
		super(false);
		Logger.Log("Gap konstruktor");
		Logger.Logout();
	}

	public ItemState hasItem() {
		Logger.Log("Gap hasItem");
		Logger.Logout();
		return ItemState.FORBIDDENAREA;
	}

	public void interactCharacter(Character c) {
		Logger.Log("Gap interactCharacter");
		c.die();
		Logger.Logout();
	}

	public void interactBullet(Bullet b) {
		Logger.Log("Gap interactBullet");
		b.setPosition(this);
		Logger.Logout();
	}
}
