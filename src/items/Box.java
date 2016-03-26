package items;

import game.Character;
import logger.Logger;

public class Box extends Item {
	public void pickUp(Character c) {
		Logger.log("Box pickUp");
		c.setBox(this);
		Logger.logout();
	}
}
