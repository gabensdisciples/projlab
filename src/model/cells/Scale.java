package model.cells;

import java.util.Stack;

import model.enumerations.Direction;
import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Player;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.items.Box;
import model.items.Item;

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
  }

  /**
   * Cell's property.
   */
  public ItemState hasItem() {
    if (!boxes.isEmpty()) {
      return ItemState.STACKITEM;
    } else {
      return ItemState.NOITEM;
    }
  }

  /**
   * Creates interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
    weight += 2;
    if (weight >= limit) {
      door.setWalkable(true);
    }
    character.setPosition(this);
  }

  /**
   * Creates interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
    Replicator replicator = ReplicatorContainer.getReplicator(this);
    if (replicator != null) {
      replicator.die();
      bullet.die();
    } else {
      bullet.setPosition(this);
    }
  }

  @Override
  public LevelObject getNeighbour(Direction dir, boolean characterCalled) {
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
    }

    if (characterCalled && neighbour != null) {
        Player dummy = new Player(this, null, dir);
        
        neighbour.interactCharacter(dummy);
        if (dummy.getPosition() != this) {
          if (neighbour instanceof Door) {
            door.setWalkable(false);
          }
          if (!(neighbour instanceof Door) && neighbour.walkable) {
            weight -= 2;
          }
          if (weight < limit) {
            door.setWalkable(false);
          }
        }
    }

    return neighbour;
  }

  @Override
  public void getItem(Player player) {
    boxes.pop().pickUp(player);
    weight -= 1;
    if (weight < limit) {
      door.setWalkable(false);
    }
  }

  @Override
  public void placeItem(Item item) {
    boxes.push((Box) item);
    weight += 1;
    if (weight >= limit) {
      door.setWalkable(true);
    }
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
