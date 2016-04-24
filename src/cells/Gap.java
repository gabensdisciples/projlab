package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.Player;
import game.Replicator;
import game.ReplicatorContainer;
import items.Item;
import logger.Logger;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Gap extends LevelObject {

  private Item item;

  /**
   * Gap constructor.
   */
  public Gap() {
    super(false);
    Logger.log("Gap konstruktor");
    item = null;
    Logger.logout();
  }

  /**
   * Checks if the cell has any item.
   */
  public ItemState hasItem() {
    Logger.log("Gap hasItem");
    Logger.logout();
    if (!walkable) {
      return ItemState.FORBIDDENAREA;
    } else if (item != null) {
      return ItemState.GOTITEM;
    } else {
      return ItemState.NOITEM;
    }
  }

  /**
   * Makes interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
    Logger.log("Gap interactCharacter");
    if (!walkable) {
      character.setPosition(this);
      if (ReplicatorContainer.getReplicator(this) != null) {
        System.out.println("walkable = true");
        walkable = true;
      }
      character.die();
    } else {
      character.setPosition(this);
    }
    Logger.logout();
  }

  /**
   * Makes interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
    Logger.log("Gap interactBullet");
    if (walkable) {
      Replicator replicator = ReplicatorContainer.getReplicator(this);
      if (replicator != null) {
        replicator.die();
        bullet.die();
      } else {
        bullet.setPosition(this);
      }
    } else {
      bullet.setPosition(this);
    }
    Logger.logout();
  }

  @Override
  public void getItem(Player player) {
    Logger.log("Gap getItem");
    Item tmp = item;
    item = null;
    tmp.pickUp(player);
    Logger.logout();
  }

  @Override
  public void placeItem(Item item) {
    Logger.log("Gap placeItem");
    this.item = item;
    Logger.logout();
  }
}
