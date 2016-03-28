package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import items.Box;
import items.Item;
import logger.Logger;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Scale extends LevelObject {
  private Box box;
  private Door door;

  /**
   * Scale constructor.
   * 
   * @param door
   *          - the door which the scale opens
   */
  public Scale(Door door) {
    super(true);
    box = null;
    this.door = door;
    Logger.log("Scale konstruktor");
    Logger.logout();
  }

  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    Logger.log("Scale hasItem");
    Logger.logout();
    if (box != null) {
      return ItemState.GOTITEM;
    } else {
      return ItemState.NOITEM;
    }
  }

  /**
   * Creates interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
    Logger.log("Scale interactCharacter");
    door.setWalkable(true);
    character.setPosition(this);
    Logger.logout();
  }

  /**
   * Creates interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
    Logger.log("Scale interactBullet");
    bullet.setPosition(this);
    Logger.logout();
  }

  @Override
  public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
    Logger.log("Scale getNeighbour");

    if (characterCalled && box == null) {
      Character dummy = new Character(this, null, dir);
      LevelObject neighbour = null;
      switch (dir) {
        case NORTH:
          neighbour = neighbourNorth;
          break;
        case EAST:
          neighbour = neighbourEast;
          break;
        case SOUTH:
          neighbour = neighbourSouth;
          break;
        case WEST:
          neighbour = neighbourWest;
          break;
        default:
          break;
      }

      neighbour.interactCharacter(dummy);
      if (dummy.getPosition() != this) {
        door.setWalkable(false);
      }
    }
    Logger.logout();
    switch (dir) {
      case NORTH:
        return neighbourNorth;
      case EAST:
        return neighbourEast;
      case SOUTH:
        return neighbourSouth;
      case WEST:
        return neighbourWest;
      default:
        return null;
    }
  }

  @Override
  public void getItem(Character character) {
    Logger.log("Scale getItem");
    box.pickUp(character);
    box = null;
    door.setWalkable(false);
    Logger.logout();
  }

  @Override
  public void placeItem(Item item) {
    Logger.log("Scale placeItem");
    box = (Box) item;
    door.setWalkable(true);
    Logger.logout();
  }
}
