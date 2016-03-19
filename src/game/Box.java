package game;

public class Box extends Item{
	public void pickUp(Character c){
		System.out.println("Box pickUp");
		c.setBox(this);
	}
}
