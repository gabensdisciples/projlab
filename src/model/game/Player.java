package model.game;

import java.util.Random;

import model.cells.LevelObject;
import model.enumerations.Color;
import model.enumerations.Direction;
import model.enumerations.ItemState;
import model.items.Box;
import model.items.Zpm;
import model.logger.Logger;
import model.main.Menu;

/**
 * Defines the player.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Player extends Character {

  private Color bulletColor;

  private int zpmCount;

  private Box box;

  /**
   * Player constructor.
   * 
   * @param position
   *          - where the character stands
   * @param color
   *          - the bullet color
   * @param direction
   *          - the direction the character faces
   */
  public Player(LevelObject position, Color color, Direction direction) {
    super(position);
    Logger.log("Player konstruktor");
    bulletColor = color;
    this.direction = direction;
    this.zpmCount = 0;
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
   * The character shoots a bullet in a direction with specified color.
   */
  public void shoot() {
    Logger.log("Player shoot");
    Bullet bullet = new Bullet(position, direction, bulletColor);
    bullet.fly();
    Logger.logout();
  }

  /**
   * Increments zpmCount attribute. Called after picking up a ZPM.
   */
  public void incrementZpmCount() {
    Logger.log("Player incrementZpmCount");
    zpmCount++;

    if (zpmCount == 2) {
      // If randomizing is turned off, then place zpm at the given location
      if (Menu.randomZpmOffset[0] != 0 || Menu.randomZpmOffset[1] != 0 ||
          Menu.randomZpmOffset[2] != 0 || Menu.randomZpmOffset[3] != 0) {
        LevelObject current = position;

        // Iterate north
        for (int i = 1; i <= Menu.randomZpmOffset[0]; i++) {
          current = current.getNeighbour(Direction.NORTH, true);
        }

        // Iterate east
        for (int i = 1; i <= Menu.randomZpmOffset[1]; i++) {
          current = current.getNeighbour(Direction.EAST, true);
        }

        // Iterate south
        for (int i = 1; i <= Menu.randomZpmOffset[2]; i++) {
          current = current.getNeighbour(Direction.SOUTH, true);
        }

        // Iterate west
        for (int i = 1; i <= Menu.randomZpmOffset[3]; i++) {
          current = current.getNeighbour(Direction.WEST, true);
        }

        // Try to place it
        if (current.hasItem() == ItemState.NOITEM) {
          current.placeItem(new Zpm());
        }
      }

      //Else randomize
      else {
        Random rand = new Random();
        // Randomize iteration limit
        int limit = rand.nextInt(101);
        boolean iterate = true;
        
        //Randomize direction to step in
        int direction = rand.nextInt(4);
        int i = 0;
        LevelObject current = position;
        //Iterate until we find a cell where we can put a zpm
        while (iterate) {
          //Keep stepping until iteration limit
          while (i < limit) {
            //Move in the randomized direction
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
            }
            //Randomize next move's direction
            direction = rand.nextInt(4);
            i++;
          }
          
          /*If it is possible to put a zpm on the cell we arrived at, then put it,
           *and set iterate flag false
           */
          if (current.hasItem() == ItemState.NOITEM) {
            current.placeItem(new Zpm());
            iterate = false;
          }
          i = 0;
        }
      }
    }
    
    //If no more zpms remain, end of game
    if (Zpm.zpmsRemaining == 0) {
      // Vege a jateknak
    }
    Logger.logout();
  }

  /**
   * Sets box attribute to the given value.
   * @param box
   *          - the box to be set
   */
  public void setBox(Box box) {
    Logger.log("Player setBox");
    if (this.box != null) {
      position.placeItem(box);
    } else {
      this.box = box;
    }
    Logger.logout();
  }

  /**
   * Attempts to drop the player's box on the cell it is currently on.
   * If that cell is not a valid target or the player has no box, nothing happens.
   */
  
  public void drop() {
    Logger.log("Player drop");
    
    ItemState state = position.hasItem();
    
    if (box != null && 
        (state == ItemState.NOITEM || state == ItemState.STACKITEM)) {
        position.placeItem(box);
        box = null;
     }

    Logger.logout();
  }

  /**
   * Takes a box.
   */
  public void take() {
    Logger.log("Player take");    
    
    ItemState state = position.hasItem();
    if (state == ItemState.GOTITEM || state == ItemState.STACKITEM) {
      position.getItem(this);
    }

    Logger.logout();
  }
  
  public Color getColor(){
    return bulletColor;
  }

  public void die() {
    Logger.log("Player die");
    this.position = null;
    Menu.gameOver(this, zpmCount);
    Logger.logout();
  }

  public int getZpmCount() {
    return zpmCount;
  }
}