package items;

import game.Character;
import logger.Logger;

public class ZPM extends Item {
	public void pickUp(Character c) {
		Logger.log("ZPM pickUp");
		c.incrementZPMCount();
		Logger.logout();
	}
}
