package items;

import game.Character;
import logger.Logger;

/**
 * Defines the box.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Box extends Item {

  /**
   * Set's the character box reference to itself.
   */
  public void pickUp(Character character) {
    Logger.log("Box pickUp");
    character.setBox(this);
    Logger.logout();
  }
}
