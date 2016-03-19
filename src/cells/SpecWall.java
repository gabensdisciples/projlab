package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.StarGate;

public class SpecWall extends LevelObject {
	
	private StarGate gate;

	public SpecWall() {
		super(false);
		System.out.println("SpecWall konstruktor");
	}
	
	public ItemState hasItem() {
		System.out.println("SpecWall hasItem");
		return null;
	}
	
	@Override
	public void interactCharacter(Character c) {
		System.out.println("SpecWall interactCharacter");
	}

	@Override
	public void interactBullet(Bullet b) {
		System.out.println("SpecWall interactBullet");
	}

	public void SetStarGate(StarGate gate){
		System.out.println("SpecWall setStarGate");
		this.gate = gate;
	}
}
