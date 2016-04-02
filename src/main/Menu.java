<<<<<<< HEAD
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

  /**
   * Main method to start the game.
   * 
   * @param args
   *          - init parameters
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // Logger.log("Menu main");
    // Logger.logout();
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init(3);
    levelBuilder.printObjectMatrix();

  }

  public static void showHelp() {
    Logger.log("Menu showHelp");
    Logger.logout();
  }
}
=======
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

  /**
   * Main method to start the game.
   * 
   * @param args
   *          - init parameters
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    // Logger.log("Menu main");
    // Logger.logout();
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init(3);
    levelBuilder.printObjectMatrix();

  }

  public static void showHelp() {
    Logger.log("Menu showHelp");
    Logger.logout();
  }
}
>>>>>>> branch 'master' of https://github.com/gabensdisciples/projlab.git
