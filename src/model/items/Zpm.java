package model.items;

import model.game.Player;
import model.menu.Menu;

/**
 * Defines the ZPM.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Zpm extends Item {
  
  public static int zpmsMax = 0;
  public static int zpmsRemaining = 0;
  
  public Zpm() {
    zpmsRemaining++;
    zpmsMax++;
  }

  /**
   * Increase the character's ZpmCount property when he picks up one.
   */
  public void pickUp(Player player) {
    zpmsRemaining--;
    player.incrementZpmCount();
    if(zpmsRemaining == 0){
      Menu.gameOver(player, zpmsMax);
    }
  }
}
