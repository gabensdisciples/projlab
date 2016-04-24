package main;

import java.io.IOException;
import game.Player;
import enumerations.Color;
import logger.Logger;

/**
 * Defines the menu.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Menu {

  private static MenuPoints points;
  public static int[] randomZpmOffset = { 0, 0, 0, 0 };

  /**
   * Main method to start the game.
   * 
   * @param args
   *          - init parameters
   */
  public static void main(String[] args) {
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    CommandHandler.setLevelBuilder(levelBuilder);
    
    if (args.length != 0) {
      try {
        CommandHandler.processFile(args[0]);
      }
      
      catch (IOException e) {
        System.out.println("A megadott fajlt nem talaltam.");
      }
      
      finally {
        System.exit(0);
      }
    }
    
    boolean success = false;
    try {
        success = CommandHandler.setAutoTest();
    }
    
    catch (Exception e) {
      System.out.println("Ervenytelen azonosito.");
      System.exit(0);
    }
    
    if (!success) {
      System.exit(0);
    }

    while (true) {
      if (CommandHandler.autoTest) {
        CommandHandler.autoTest();
      }

      else {
        try {
          String command = CommandHandler.getCommand();
          success = CommandHandler.executeCommand(command);
          if (!success) {
            System.out.println("Hibas parancs, add meg ujra!");
          }
        }

        catch (Exception e) {
          System.out.println("A parancs vegrehajtasa sikertelen volt.");
        }
      }
    }
  }

  public static void showHelp() {
    Logger.log("Menu showHelp");
    System.out.println("loadmap <level file txt>\n"
        + "move <character name: oneill, jaffa, replicator> <direction n, e, s, w>\n"
        + "changebullet <character name: oneill, jaffa>\n" + "shoot <character name: oneill, jaffa>\n"
        + "pickup <character name: oneill, jaffa>\n" + "drop <character name: oneill, jaffa>\n" + "printmap\n"
        + "help\n" + "zpmcount <character name: oneill, jaffa>\n"
        + "setrandomzpmposition <number of steps from oneill in each direction 0 0 0 0>\n");
    Logger.logout();
  }

  public static void gameOver(Player player, int zpmsMax) {
    if (player.getPosition() == null) {
      if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
        System.out.println("O'Neill meghalt Jaffa nyert!");
      } else {
        System.out.println("Jaffa meghalt O'Neill nyert!");
      }

    } else if (player.getZpmCount() > zpmsMax - player.getZpmCount()) {
      if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
        System.out.println("O'Neill nyert!\nO'Neill zpm(s):"+player.getZpmCount()+"\nJaffa zmp(s):"+(zpmsMax - player.getZpmCount()));
      } else {
        System.out.println("Jaffa nyert!\nO'Neill zpm(s):"+player.getZpmCount()+"\nJaffa zmp(s):"+(zpmsMax - player.getZpmCount()));
      }
    } else if (player.getZpmCount() != zpmsMax - player.getZpmCount()) {
      System.out.println("Döntetlen\nO'Neill zpm(s):"+player.getZpmCount()+"\nJaffa zmp(s):"+(zpmsMax - player.getZpmCount()));
    } else {
      if (player.getColor() != Color.BLUE && player.getColor() != Color.YELLOW) {
        System.out.println("O'Neill nyert!\nO'Neill zpm(s):"+player.getZpmCount()+"\nJaffa zmp(s):"+(zpmsMax - player.getZpmCount()));
      } else {
        System.out.println("Jaffa nyert!\nO'Neill zpm(s):"+player.getZpmCount()+"\nJaffa zmp(s):"+(zpmsMax - player.getZpmCount()));
      }
    }
  }
}
