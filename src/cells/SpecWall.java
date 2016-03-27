package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.StarGate;
import logger.Logger;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class SpecWall extends LevelObject {

  private StarGate gate;
  
  /**
   * SpecWall constructor.
   */
  public SpecWall() {
    super(false);
    Logger.log("SpecWall konstruktor");
    Logger.logout();
  }
  
  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    Logger.log("SpecWall hasItem");
    Logger.logout();
    return ItemState.FORBIDDENAREA;
  }

  @Override
  public void interactCharacter(Character character) {
    Logger.log("SpecWall interactCharacter");
    if (isWalkable()) {
      character.setPosition(this);
    }
    Logger.logout();
  }

  @Override
  public void interactBullet(Bullet bullet) {
    Logger.log("SpecWall interactBullet");
    if (gate == null) {
      gate = bullet.createStarGate();
    }
    bullet.die();
    walkable = true;
    Logger.logout();
  }
  
  /**
   * Sets the stargate to the current SpecWall.
   * @param gate the startgate object
   */
  public void setStarGate(StarGate gate) {
    Logger.log("SpecWall setStarGate");
    this.gate = gate;
    if (gate == null) {
      walkable = false;
    }
    Logger.logout();
  }
}
