package cells;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.Player;
import items.Item;
import logger.Logger;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public abstract class LevelObject {
  protected LevelObject neighbourNorth;

  protected LevelObject neighbourEast;

  protected LevelObject neighbourSouth;

  protected LevelObject neighbourWest;

  protected boolean walkable;

  /**
   * LevelObject constructor.
   * 
   * @param walkable
   *          - LevelObject property
   */
  public LevelObject(boolean walkable) {
    this.walkable = walkable;
    Logger.log("LevelObject konstruktor");
    Logger.logout();
  }

  /**
   * Checks if the character can step on the cell.
   * 
   * @return the walkable value
   */
  public boolean isWalkable() {
    Logger.log("LevelObject isWalkable");
    Logger.logout();
    return walkable;

  }

  public abstract void interactCharacter(Character character);

  public abstract void interactBullet(Bullet bullet);

  /**
   * Gets the neighbor of a cell in the given direction.
   * 
   * @param dir
   *          - direction
   * @param characterCalled
   *          - the callers
   * @return the neighbor cell
   */
  public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
    Logger.log("LevelObject getNeighbour");
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
      // TODO: Dobjunk Exception-t defaultn�l?
    }
  }

  /**
   * Gets the neighbor of a cell in the given direction.
   * 
   * @param dir
   *          - direction
   * @param levelObject
   *          - LevelObject
   */
  public void setNeighbour(Direction dir, LevelObject levelObject) {
    Logger.log("LevelObject setNeighbour");

    switch (dir) {
      case NORTH:
        neighbourNorth = levelObject;
        break;
      case EAST:
        neighbourEast = levelObject;
        break;
      case SOUTH:
        neighbourSouth = levelObject;
        break;
      case WEST:
        neighbourWest = levelObject;
        break;
      default:
        break;
    }
    Logger.logout();
  }

  public abstract ItemState hasItem();

  public void getItem(Player character) {
    Logger.log("LevelObject getItem");
    Logger.logout();
  }

  public void placeItem(Item item) {
    Logger.log("LevelObject placeItem");
    Logger.logout();
  }
}
