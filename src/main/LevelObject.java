package main;

public abstract class LevelObject {
	private LevelObject neighbourNorth;
	private LevelObject neighbourEast;
	private LevelObject neighbourSouth;
	private LevelObject neighbourWest;
	private boolean walkable;
	
	public LevelObject(boolean walkable){
		System.out.println("LevelOject konstruktor");
		this.walkable = walkable;
	}
	
	public boolean isWalkable(){
		System.out.println("isWalkable");
		return walkable;
	}
	
	public abstract void interactCharacter(Character c);
	
	public abstract void interactBullet(Bullet b);
	
	public LevelObject getNeighbour(Direction dir){
		System.out.println("getNeighbour");
		return null;
	}
	
	public void setNeighbour(Direction dir, LevelObject l){
		System.out.println("setNeighbour");
	}
	
	public abstract ItemState hasItem();
	
	public void getItem(Character c){
		System.out.println("getItem");
	}
	
	public void placeItem(Item item){
		System.out.println("placeItem");
	}
	
}
