package model.menu;

import view.View;
import model.game.Player;
import model.enumerations.Color;

/**
 * Defines the menu.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Menu {

  private static MenuPoints points;
  public static int[] randomZpmOffset = { 0, 0, 0, 0 };
  private static String helpText = "loadmap <level file txt>\n"
      + "move <character name: oneill, jaffa, replicator> <direction n, e, s, w>\n"
      + "changebullet <character name: oneill, jaffa>\n" + "shoot <character name: oneill, jaffa>\n"
      + "pickup <character name: oneill, jaffa>\n" + "drop <character name: oneill, jaffa>\n" + "printmap\n" + "help\n"
      + "zpmcount <character name: oneill, jaffa>\n"
      + "setrandomzpmposition <number of steps from oneill in each direction 0 0 0 0>\n";

  /**
   * Main method to start the game.
   * 
   * @param args
   *          - init parameters
   */

  public static void showHelp() {
    System.out.println(helpText);
  }

  public static void gameOver(Player player, int zpmsMax) {
    if (player.getPosition() == null) {
      if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
        View.gameOver("O'Neill died, Jaffa won!");
      } else {
        View.gameOver("Jaffa died, O'Neill won!");
      }

    } else if (player.getZpmCount() > zpmsMax - player.getZpmCount()) {
      if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
        View.gameOver("O'Neill won!\nO'Neill zpm(s): " + player.getZpmCount() + "\nJaffa zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      } else {
        View.gameOver("Jaffa won!\nJaffa zpm(s): " + player.getZpmCount() + "\nO'Neill zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      }
    } else if (player.getZpmCount() == zpmsMax - player.getZpmCount()) {
      View.gameOver("The game ended in a tie.\nO'Neill zpm(s): " + player.getZpmCount() + "\nJaffa zpm(s): "
          + (zpmsMax - player.getZpmCount()));
    } else {
      if (player.getColor() != Color.BLUE && player.getColor() != Color.YELLOW) {
        View.gameOver("O'Neill won!\nJaffa zpm(s): " + player.getZpmCount() + "\nO'Neill zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      } else {
        View.gameOver("Jaffa won\nO'Neill zpm(s): " + player.getZpmCount() + "\nJaffa zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      }
    }
    //Exit?
  }

  public static String getHelpText() {
    return helpText;
  }
}
