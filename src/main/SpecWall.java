package main;

public class SpecWall extends LevelObject {
	
	private StarGate gate;

	public SpecWall(boolean walkable) {
		super(walkable);
	}

	@Override
	public void interactCharacter(Character c) {
		System.out.println("SpecWall interactCharacter");
	}

	@Override
	public void interactBullet(Bullet b) {
		System.out.println("SpecWall interactBullet");
	}

	@Override
	public ItemState hasItem() {
		System.out.println("SpecWall hasItem");
		return null;
	}
	
	public void StarGate(StarGate gate){
		System.out.println("SpecWall konstruktor");
		this.gate = gate;
	}
}
