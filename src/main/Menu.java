package main;

import logger.Logger;

/**
 * Defines the menu.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Menu {

  private static MenuPoints points;
  public static int[] randomZpmOffset = {0, 0, 0, 0};

  /**
   * Main method to start the game.
   * 
   * @param args
   *          - init parameters
   */
  public static void main(String[] args) {
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init();
    CommandHandler.setLevelBuilder(levelBuilder);
    CommandHandler.setAutoTest();
    
    while(true) {
      if(CommandHandler.autoTest) {
        CommandHandler.autoTest();
      }
      
      else {
        try {
          String command = CommandHandler.getCommand();
          boolean success = CommandHandler.executeCommand(command);
          if(!success) {
            System.out.println("Hibas parancs, add meg ujra!");
          }
        }
        
        catch (Exception e) {
          System.out.println("A parancs vegrehajtasa sikertelen volt.");
          
          //STACKTRACE NE MARADJON BENNE A VEGLEGES VERZIOBAN!
          e.printStackTrace();
        }
      }
    }
  }

  public static void showHelp() {
    Logger.log("Menu showHelp");
    Logger.logout();
  }
}
