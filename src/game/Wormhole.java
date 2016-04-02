package game;

import java.util.HashMap;
import java.util.Map;
import cells.SpecWall;
import enumerations.Color;
import logger.Logger;

/**
 * Defines the wormhole.
 * 
 * @author Gaben's Disciples
 * 
 */
public final class Wormhole {
  private static Map <Color,SpecWall> SpecWalls = new HashMap<Color,SpecWall>();
  /*private static SpecWall blueSpecWall = null;
  private static SpecWall yellowSpecWall = null;*/

  /**
   * Sets the SpecWall objects to the wormhole.
   * 
   * @param specWall
   *          - the object
   * @param color
   *          - the SpecWall's color
   */
  public static void setSpecWall(SpecWall specWall, Color color) {
    Logger.log("Wormhole setSpecWall");
    if(SpecWalls.containsKey(color)){
      SpecWalls.remove(color);
    }
    SpecWalls.put(color, specWall);
    Logger.logout();
  }

  /**
   * Gets the current SpecWall object.
   * 
   * @param color
   *          - the SpecWall's color
   * @return the SpecWall object
   */
  public static SpecWall getSpecWall(Color color) {
    Logger.log("Wormhole getSpecWall");
    Logger.logout();
    if(SpecWalls.containsKey(color)){
      return SpecWalls.get(color);
    }
    else {
      return null;
    }
  }
}
