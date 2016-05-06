package model.cells;

import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Player;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.items.Item;
import view.View;

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
    item = null;
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
    if (!walkable) {
      character.setPosition(this);
      if (ReplicatorContainer.getReplicator(this) != null) {
        walkable = true;
        View.remove(this.ID);
        View.create(this.ID, this.ID, "floor.png");
      }
      character.die();
    } else {
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
      bullet.setPosition(this);
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
    View.create(item.ID, this.ID, "box.png");
  }
}
