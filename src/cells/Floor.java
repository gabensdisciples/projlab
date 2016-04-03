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
public class Floor extends LevelObject {
  private Item item;

  /**
   * Floor constructor.
   * 
   * @param walkable
   *          - LevelObject property
   * @param item
   *          - level element
   */
  public Floor(boolean walkable, Item item) {
    super(walkable);
    this.item = item;
    Logger.log("Floor konstruktor");
    Logger.logout();
  }

  /**
   * Checks if the cell has any item.
   */
  public ItemState hasItem() {
    Logger.log("Floor hasItem");
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
    Logger.log("Floor interactCharacter");
    if (walkable) {
      character.setPosition(this);
    }
    Logger.logout();
  }

  /**
   * Makes interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
    Logger.log("Floor interactBullet");

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

  @Override
  public void getItem(Player player) {
    Logger.log("Floor getItem");
    item.pickUp(player);
    item = null;
    Logger.logout();
  }

  @Override
  public void placeItem(Item item) {
    Logger.log("Floor placeItem");
    this.item = item;
    Logger.logout();
  }
}
