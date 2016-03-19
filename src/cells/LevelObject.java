package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Item;

public abstract class LevelObject {
	private LevelObject neighbourNorth;
	private LevelObject neighbourEast;
	private LevelObject neighbourSouth;
	private LevelObject neighbourWest;
	//TODO: Protected lett, isWalkable metodus megszunt : dokumentalni kell!
	protected boolean walkable;
	
	public LevelObject(boolean walkable){
		this.walkable = walkable;
		System.out.println("LevelOject konstruktor");
	}
	
	public abstract void interactCharacter(Character c);
	
	public abstract void interactBullet(Bullet b);
	
	public LevelObject getNeighbour(Direction dir){
		System.out.println("getNeighbour");
		
		switch(dir) {
			case North : return neighbourNorth;
			case East : return neighbourEast;
			case South : return neighbourSouth;
			case West : return neighbourWest;
			default : return null;
			//TODO: Dobjunk Exception-t defaultnál?
		}
	}
	
	public void setNeighbour(Direction dir, LevelObject l){
		System.out.println("setNeighbour");
		
		switch(dir) {
		case North : neighbourNorth = l; break;
		case East : neighbourEast = l; break;
		case South : neighbourSouth = l; break;
		case West : neighbourWest = l; break;
		}
	}
	
	public abstract ItemState hasItem();
	
	public void getItem(Character c){
		System.out.println("getItem");
	}
	
	public void placeItem(Item item){
		System.out.println("placeItem");
	}
	
}
