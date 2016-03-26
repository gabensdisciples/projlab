package items;

import game.Character;
import logger.Logger;

public class Box extends Item {
	public void pickUp(Character c) {
		Logger.Log("Box pickUp");
		c.setBox(this);
		Logger.Logout();
	}
}
