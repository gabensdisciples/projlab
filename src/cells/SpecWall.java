package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.StarGate;
import logger.Logger;

public class SpecWall extends LevelObject {
	
	private StarGate gate;

	public SpecWall() {
		super(false);
		Logger.Log("SpecWall konstruktor");
		Logger.Logout();
	}
	
	public ItemState hasItem() {
		Logger.Log("SpecWall hasItem");
		Logger.Logout();
		return null;
	}
	
	@Override
	public void interactCharacter(Character c) {
		Logger.Log("SpecWall interactCharacter");
		Logger.Logout();
	}

	@Override
	public void interactBullet(Bullet b) {
		Logger.Log("SpecWall interactBullet");
		Logger.Logout();
	}

	public void SetStarGate(StarGate gate){
		Logger.Log("SpecWall setStarGate");
		this.gate = gate;
		Logger.Logout();
	}
}
