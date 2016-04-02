package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.Replicator;
import game.ReplicatorContainer;
import game.StarGate;
import game.Wormhole;
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
    gate = null;
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
    if (gate != null) {
      LevelObject pair = Wormhole.getSpecWall(gate.getColor().getOtherColor());
      if (pair != null) {
        character.setPosition(pair);
      } else {
        character.setPosition(this);
      }
    }
    Logger.logout();
  }

  @Override
  public void interactBullet(Bullet bullet) {
    Logger.log("SpecWall interactBullet");
    if (gate == null) {
      gate = bullet.createStarGate();
      Wormhole.setSpecWall(this, gate.getColor());
    }
    else {
      Replicator replicator = ReplicatorContainer.getReplicator(this);
      if (replicator != null) {
        replicator.die();
      }
    }
    bullet.die();
    walkable = true;
    Logger.logout();
  }

  /**
   * Sets the stargate to the current SpecWall.
   * 
   * @param gate
   *          the startgate object
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
