package cells;

import java.util.Stack;

import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.Character;
import game.Player;
import game.Replicator;
import game.ReplicatorContainer;
import items.Box;
import items.Item;
import logger.Logger;
//változott: default konstruktor, setDoor!

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Scale extends LevelObject {
  private Stack<Box> boxes;
  private Door door;
  private int weight;
  private int limit;

  /**
   * Default constructor.
   */
  public Scale(int limit) {
    super(true);
    boxes = new Stack<Box>();
    walkable = true;
    this.limit = limit;
    this.weight = 0;
  }

  /**
   * Scale constructor.
   * 
   * @param door
   *          - the door which the scale opens
   */
  public Scale(Door door, int limit) {
    super(true);
    boxes = new Stack<Box>();
    walkable = true;
    weight = 0;
    this.door = door;
    this.limit = limit;
    Logger.log("Scale konstruktor");
    Logger.logout();
  }

  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    Logger.log("Scale hasItem");
    Logger.logout();
    if (!boxes.isEmpty()) {
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
    weight += 2;
    if (weight >= limit) {
      door.setWalkable(true);
    }
    character.setPosition(this);
    Logger.logout();
  }

  /**
   * Creates interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
    Logger.log("Scale interactBullet");
    Replicator replicator = ReplicatorContainer.getReplicator(this);
    if (replicator != null) {
      replicator.die();
      bullet.die();
    } else {
      bullet.setPosition(this);
    }
    Logger.logout();
  }

  @Override
  public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
    Logger.log("Scale getNeighbour");
    LevelObject neighbour = null;
    if (characterCalled) {
      Player dummy = new Player(this, null, dir);
      
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
      
      if (dummy.getPosition() != this &&  weight < limit) {
        weight -= 2;
        door.setWalkable(false);
      }
    }
    Logger.logout();

    return neighbour;
  }

  @Override
  public void getItem(Player player) {
    Logger.log("Scale getItem");
    boxes.pop().pickUp(player);
    weight -= 1;
    if (weight < limit) {
      door.setWalkable(false);
    }
    Logger.logout();
  }

  @Override
  public void placeItem(Item item) {
    Logger.log("Scale placeItem");
    boxes.push((Box) item);
    System.out.println("Scale placeitem :" +boxes.size());
    weight += 1;
    if (weight >= limit) {
      door.setWalkable(true);
    }
    Logger.logout();
  }

  public void setDoor(Door door) {
    this.door = door;
  }

  public Door getDoor() {
    return this.door;
  }

  public int getBoxNumber() {
    return boxes.size();
  }
  
}
