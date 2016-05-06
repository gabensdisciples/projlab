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
    if (gate != null) {
      Replicator replicator = ReplicatorContainer.getReplicator(this);
      if (replicator != null) {
        replicator.die();
      }
      
      else {
        Wormhole.setSpecWall(this, gate.getColor());
        gate = bullet.createStarGate();
        
      }
    } 
    
    else {
      gate = bullet.createStarGate();
      Wormhole.setSpecWall(this, gate.getColor());
    }
    
    bullet.die();
    walkable = true;
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
