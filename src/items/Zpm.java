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
  
  public static int zpmsRemaining = 0;
  
  public Zpm() {
    zpmsRemaining++;
  }

  /**
   * Increase the character's ZpmCount property when he picks up one.
   */
  public void pickUp(Player player) {
    Logger.log("ZPM pickUp");
    zpmsRemaining--;
    player.incrementZpmCount();
    Logger.logout();
  }
}
