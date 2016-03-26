package items;

import game.Character;
import logger.Logger;

public class ZPM extends Item {
	public void pickUp(Character c) {
		Logger.Log("ZPM pickUp");
		c.incrementZPMCount();
		Logger.Logout();
	}
}
