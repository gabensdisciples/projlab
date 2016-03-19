package items;

import game.Character;

public class Box extends Item{
	public void pickUp(Character c){
		System.out.println("Box pickUp");
		c.setBox(this);
	}
}
