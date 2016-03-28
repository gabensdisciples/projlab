package game;

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
  private static SpecWall blueSpecWall = null;
  private static SpecWall yellowSpecWall = null;

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
    if (color == Color.BLUE) {
      if (blueSpecWall != null) {
        blueSpecWall.setStarGate(null);
      }
      blueSpecWall = specWall;
    } else {
      if (yellowSpecWall != null) {
        yellowSpecWall.setStarGate(null);
      }
      yellowSpecWall = specWall;
    }

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
    if (color == Color.BLUE) {
      return blueSpecWall;
    } else {
      return yellowSpecWall;
    }
  }
}
