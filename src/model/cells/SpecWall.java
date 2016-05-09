package model.cells;

import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.game.StarGate;
import model.game.Wormhole;

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
  }

  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    return ItemState.FORBIDDENAREA;
  }

  public void interactCharacter(Character character) {
    if (gate != null) {
      LevelObject pair = Wormhole.getSpecWall(gate.getColor().getOtherColor());
      if (pair != null) {
        character.setPosition(pair);
      } else {
        character.setPosition(this);
      }
    }
  }

  public void interactBullet(Bullet bullet) {
    //Check for replicator. If one is present on the specwall, destroy it.
    Replicator replicator = ReplicatorContainer.getReplicator(this);
    if (replicator != null) {
      replicator.die();
    }
    
    //If no replicator is present, and gate is still null, create a new gate
    //Note that a present gate will not be replaced by a new shot reaching it
    else if (gate == null) {
      gate = bullet.createStarGate();
      Wormhole.setSpecWall(this, gate.getColor());
    }
   
    //Destroy bullet
    bullet.die();
    if (!walkable) {
      walkable = true;
    }
  }

  /**
   * Sets the stargate to the current SpecWall.
   * 
   * @param gate
   *          the startgate object
   */
  public void setStarGate(StarGate gate) {
    this.gate = gate;
    if (gate == null) {
      walkable = false;
    }
  }

  public StarGate getStarGate() {
    return this.gate;
  }
}
