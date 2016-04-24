package main;

import java.io.IOException;

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
    CommandHandler.setAutoTest();
    
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

    while (true) {
      if (CommandHandler.autoTest) {
        CommandHandler.autoTest();
      }

      else {
        try {
          String command = CommandHandler.getCommand();
          boolean success = CommandHandler.executeCommand(command);
          if (!success) {
            System.out.println("Hibas parancs, add meg ujra!");
          }
        }

        catch (Exception e) {
          System.out.println("A parancs vegrehajtasa sikertelen volt.");

          // STACKTRACE NE MARADJON BENNE A VEGLEGES VERZIOBAN!
          e.printStackTrace();
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
}
