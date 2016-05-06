package model.cells;

import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import view.View;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Door extends LevelObject {
  /**
   * Door constructor.
   */
  public Door() {
    super(false);
  }

  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    return ItemState.FORBIDDENAREA;
  }

  /**
   * Creates interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
    if (walkable) {
      character.setPosition(this);
    }
  }

  /**
   * Creates interaction between the bullet and level obejcts.
   */
  public void interactBullet(Bullet bullet) {
    if (walkable) {
      Replicator replicator = ReplicatorContainer.getReplicator(this);
      if (replicator != null) {
        replicator.die();
        bullet.die();
      } else {
        bullet.setPosition(this);
      }
    } else {
      bullet.die();
    }
  }

  /**
   * Set the door object's walkable property.
   * 
   * @param walkable
   *          - property
   */
  public void setWalkable(boolean walkable) {
    this.walkable = walkable;
  }
}
