package model.cells;

import model.enumerations.Direction;
import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.IdentifiedObject;
import model.game.Player;
import model.items.Item;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public abstract class LevelObject extends IdentifiedObject{
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
  }

  /**
   * Checks if the character can step on the cell.
   * 
   * @return the walkable value
   */
  public boolean isWalkable() {
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
  /**
   * Gets the neighbor of a cell in the given direction.
   * 
   * @param dir
   *          - direction
   * @param levelObject
   *          - LevelObject
   */
  public void setNeighbour(Direction dir, LevelObject levelObject) {
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
  }
  
  public abstract ItemState hasItem();

  public void getItem(Player character) {}

  public void placeItem(Item item) {}
}
