package model.cells;

import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.game.StarGate;
import model.game.Wormhole;
import model.logger.Logger;

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

  public void interactBullet(Bullet bullet) {
    Logger.log("SpecWall interactBullet");

    if (gate != null) {
      Replicator replicator = ReplicatorContainer.getReplicator(this);
      if (replicator != null) {
        replicator.die();
      } else {
        Wormhole.setSpecWall(this, gate.getColor());
        gate = bullet.createStarGate();
      }
    } else {
      gate = bullet.createStarGate();
      Wormhole.setSpecWall(this, gate.getColor());
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

  public StarGate getStarGate() {
    return this.gate;
  }

}
