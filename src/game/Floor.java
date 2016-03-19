package game;

public class Floor extends LevelObject{
	private Item item;
	
	public Floor(boolean walkable, Item item) {
		super(walkable);
		this.item = item;
		System.out.println("Floor konstruktor");
	}
	
	public ItemState hasItem() {
		System.out.println("Floor hasItem");
		return null;
	}
	
	public void interactCharacter(Character c) {
		System.out.println("Floor interactCharacter");
		c.setPosition(this);
	}
	
	public void interactBullet(Bullet b) {
		System.out.println("Floor interactBullet");
		
		if(walkable)
			b.setPosition(this);
		
		else b.die();
	}
	
	@Override
	public void getItem(Character c) {
		System.out.println("Floor getItem");
		item.pickUp(c);
	}
	
	@Override
	public void placeItem(Item item) {
		System.out.println("Floor placeItem");
	}
}
