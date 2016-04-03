package items;
import game.Player;
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
  public void pickUp(Player player) {
    Logger.log("Box pickUp");
    player.setBox(this);
    Logger.logout();
  }
}
