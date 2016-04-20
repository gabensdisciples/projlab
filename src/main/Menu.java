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
    CommandHandler commandHandler = new CommandHandler(levelBuilder);
    commandHandler.setAutoTest();
    
    while(true) {
      if(commandHandler.autoTest) {
        commandHandler.autoTest();
      }
      
      else {
        try {
          String command = commandHandler.getCommand();
          commandHandler.executeCommand(command);
        }
        
        catch (Exception e) {
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
