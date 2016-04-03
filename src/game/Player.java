package game;

import java.util.Random;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import enumerations.ItemState;
import items.Box;
import items.Item;
import items.Zpm;
import logger.Logger;


/**
 * Defines the player.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Player extends Character {
    
  private Direction direction;

  private Color bulletColor;
  
  private Box box;
  
  private int zpmCount;

  
  /**
   * Player constructor.
   * 
   * @param position
   *          - where the character stands
   * @param color
   *          - the bullet color
   * @param direction
   *          - the character's direction
   */
  public Player(LevelObject position, Color color, Direction direction) {
    super(position);
    Logger.log("Player konstruktor");
    bulletColor = color;
    this.direction = direction;
    Logger.logout();
  }
  
  /**
   * The character shoots a bullet in a direction with specified color.
   */
  public void shoot() {
    Logger.log("Player shoot");
    Bullet bullet = new Bullet(position, direction, bulletColor);
    bullet.fly();
    Logger.logout();
  }

  /**
   * Changes the bullet color.
   */
  public void changeColor() {
    Logger.log("Player Changecolor");
    bulletColor = bulletColor.getOtherColor();
    Logger.logout();
  }
  
  /**
   * Increments the ZPM's at the character when he pickes up one.
   */
  public void incrementZpmCount() {
    Logger.log("Player incrementZpmCount");
    zpmCount++;
    
    if (zpmCount == 2 && (bulletColor == Color.BLUE || bulletColor == Color.YELLOW)) {
      Random rand = new Random();
      int direction = rand.nextInt(4);
      int limit = rand.nextInt(101);
      int i = 0;
      boolean iterate = true;
      LevelObject current = position;
      while (iterate) {
        while (i < limit) {
          switch (direction) {
            case 0:
              if (current.getNeighbour(Direction.NORTH, false) != null) {
                current = current.getNeighbour(Direction.NORTH, false);
              }
              break;
            case 1:
              if (current.getNeighbour(Direction.EAST, false) != null) {
                current = current.getNeighbour(Direction.EAST, false);
              }
              break;
            case 2:
              if (current.getNeighbour(Direction.SOUTH, false) != null) {
                current = current.getNeighbour(Direction.SOUTH, false);
              }
              break;
            case 3:
              if (current.getNeighbour(Direction.WEST, false) != null) {
                current = current.getNeighbour(Direction.WEST, false);
              }
              break;
            default:
              break;
          }
          direction = rand.nextInt(4);
          i++;
        }

        if (current.hasItem() == ItemState.NOITEM) {
          this.drop((Item) new Zpm());
          iterate = false;
        }
        i = 0;
      }
    }
    Logger.logout();
  }

  /**
   * Sets the box object belong to the character when he picks up one.
   * 
   * @param box
   *          - the box he picked up
   */
  public void setBox(Box box) {
    Logger.log("Player setBox");
    this.box = box;
    Logger.logout();
  }

  /**
   * Drops the box.
   */
  public void drop(Item item) {
    Logger.log("Player drop");

    if (position.hasItem() == ItemState.NOITEM) {
      position.placeItem((Item) box);
      box = null;
    }

    Logger.logout();
  }

  /**
   * Takes a box.
   */
  public void take() {
    Logger.log("Player take");

    if (position.hasItem() == ItemState.GOTITEM) {
      position.getItem(this);
    }

    Logger.logout();
  }
  
  public void die() {
    Logger.log("Player die");
    Logger.logout();
  }
}