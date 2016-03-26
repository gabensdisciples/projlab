package cells;

import java.text.Normalizer.Form;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.StarGate;
import logger.Logger;

public class SpecWall extends LevelObject {
	
	private StarGate gate;

	public SpecWall() {
		super(false);
		Logger.log("SpecWall konstruktor");
		Logger.logout();
	}
	
	public ItemState hasItem() {
		Logger.log("SpecWall hasItem");
		Logger.logout();
		return ItemState.FORBIDDENAREA;
	}
	
	@Override
	public void interactCharacter(Character c) {
		Logger.log("SpecWall interactCharacter");
		if(isWalkable())
			c.setPosition(this);			
		Logger.logout();
	}

	@Override
	public void interactBullet(Bullet b) {
		Logger.log("SpecWall interactBullet");
		if(gate == null)
			gate = b.createStarGate();
		b.die();
		walkable = true;
		Logger.logout();
	}

	public void setStarGate(StarGate gate){
		Logger.log("SpecWall setStarGate");
		this.gate = gate;
		if(gate == null){
			walkable = false;
		}
		Logger.logout();
	}
}
