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
    // TODO Auto-generated method stub

    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init(6);
    levelBuilder.printStringMatrix();
    
    /*A mukodes roviden:  
     *- levelbuilder es commandhandler init
     *- commandhandler setautotest
     *- while(true) : if(autotest) commandhandler.autotest(),
     *                else commandhandler.getCommand(), commanhandler.executeCommand()
     */
    
  }

  public static void showHelp() {
    Logger.log("Menu showHelp");
    Logger.logout();
  }
}
