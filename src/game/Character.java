package game;

import cells.LevelObject;
import enumerations.Direction;

import logger.Logger;

/**
 * Defines the character.
 * 
 * @author Gaben's Disciples
 * 
 */
public abstract class Character {

  protected LevelObject position;


  /**
   * Character constructor.
   * 
   * @param position
   *          - where the character stands
   */
  public Character(LevelObject position) {
    Logger.log("Character konstruktor");
    this.position = position;
    Logger.logout();
  }

  /**
   * Makes the character move to a cell.
   * 
   * @param dir
   *          - the specified direction
   */
  public void move(Direction dir) {
    Logger.log("Character move");
    position.getNeighbour(dir, true).interactCharacter(this);
    Logger.logout();
  }

  /**
   * Sets the character's position to the given cell.
   * 
   * @param position
   *          - LevelObject, cell
   */
  public void setPosition(LevelObject position) {
    Logger.log("Character setPosition");
    this.position = position;
    Logger.logout();
  }

  /**
   * Gets the character's postion.
   * 
   * @return the position as LevelObject
   */
  public LevelObject getPosition() {
    Logger.log("Character getPosition");
    Logger.logout();
    return position;
  }
  
  public abstract void die();

}