package items;

import game.Player;
import logger.Logger;

/**
 * Defines the ZPM.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Zpm extends Item {

  /**
   * Increase the character's ZpmCount property when he picks up one.
   */
  public void pickUp(Player player) {
    Logger.log("ZPM pickUp");
    player.incrementZpmCount();
    Logger.logout();
  }
}
