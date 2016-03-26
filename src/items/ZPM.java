package items;

import game.Character;

public class ZPM extends Item {
	public void pickUp(Character c) {
		System.out.println("ZPM pickUp");
		c.incrementZPMCount();
	}
}
