package model.game;

import model.cells.LevelObject;
import model.enumerations.Direction;

/**
 * Defines the character.
 * 
 * @author Gaben's Disciples
 * 
 */
public abstract class Character extends IdentifiedObject{

  protected LevelObject position;
  
  protected Direction direction;
  
  /**
   * Character constructor.
   * 
   * @param position
   *          - where the character stands
   */
  public Character(LevelObject position) {
    this.position = position;
  }

  /**
   * Makes the character move to a cell.
   * 
   * @param dir
   *          - the specified direction
   */

  public void move(Direction dir) {
    direction = dir;
    LevelObject target = position.getNeighbour(dir, true);
    if(target != null) {
      target.interactCharacter(this);
    }
  }

  /**
   * Sets the character's position to the given cell.
   * 
   * @param position
   *          - LevelObject, cell
   */
  public void setPosition(LevelObject position) {
    this.position = position;
  }

  /**
   * Gets the character's position.
   * 
   * @return the position as LevelObject
   */
  public LevelObject getPosition() {
    return position;
  }
  
  public abstract void die();
}