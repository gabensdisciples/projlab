package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import logger.Logger;

public class Gap extends LevelObject {
	public Gap() {
		super(false);
		Logger.log("Gap konstruktor");
		Logger.logout();
	}

	public ItemState hasItem() {
		Logger.log("Gap hasItem");
		Logger.logout();
		return ItemState.FORBIDDENAREA;
	}

	public void interactCharacter(Character c) {
		Logger.log("Gap interactCharacter");
		c.die();
		Logger.logout();
	}

	public void interactBullet(Bullet b) {
		Logger.log("Gap interactBullet");
		b.setPosition(this);
		Logger.logout();
	}
}
