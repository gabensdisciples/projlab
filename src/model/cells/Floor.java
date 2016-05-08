package model.cells;

import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Player;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.items.Box;
import model.items.Item;
import model.items.Zpm;

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
  }

  /**
   * Checks if the cell has any item.
   */
  public ItemState hasItem() {
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
    if (walkable) {
      character.setPosition(this);
    }
  }

  /**
   * Makes interaction between the bullet and level objects.
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

  @Override
  public void getItem(Player player) {
    Item temp = item;
    item = null;
    temp.pickUp(player);
  }

  @Override
  public void placeItem(Item item) {
    this.item = item;
    //TODO akkor is box kerül rajzolásra, amikor incrementzpmcount rak le ZPM-et
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public Box getBox() {
    if (item instanceof Box) {
      return (Box) item;
    }
    return null;
  }

  public Zpm getZpm() {
    if (item instanceof Zpm) {
      return (Zpm) item;
    }
    return null;
  }
}
