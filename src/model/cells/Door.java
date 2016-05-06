package model.cells;

import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.logger.Logger;

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
    Logger.log("Door konstruktor");
    Logger.logout();
  }

  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    Logger.log("Door hasItem");
    Logger.logout();
    return ItemState.FORBIDDENAREA;
  }

  /**
   * Creates interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
    Logger.log("Door interactCharacter");

    if (walkable) {
      character.setPosition(this);
    }
    Logger.logout();
  }

  /**
   * Creates interaction between the bullet and level obejcts.
   */
  public void interactBullet(Bullet bullet) {
    Logger.log("Door interactBullet");

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
    Logger.logout();
  }

  /**
   * Set the door object's walkable property.
   * 
   * @param walkable
   *          - property
   */
  public void setWalkable(boolean walkable) {
    this.walkable = walkable;
    Logger.log("Door setWalkable");
    Logger.logout();
  }
}
