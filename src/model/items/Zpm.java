package model.items;

import controller.GameController;
import model.enumerations.Color;
import model.game.Player;
import view.View;

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
    View.remove(this.ID);
    if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
      View.refreshZpmCount("oneill", player.getZpmCount());
    } else if (player.getColor() == Color.GREEN || player.getColor() == Color.RED) {
      View.refreshZpmCount("jaffa", player.getZpmCount());
    }
    if (zpmsRemaining == 0) {
      GameController.gameOver(player, zpmsMax);
    }
  }
}
