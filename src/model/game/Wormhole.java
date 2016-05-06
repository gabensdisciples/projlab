package model.game;

import java.util.HashMap;
import java.util.Map;
import model.cells.SpecWall;
import model.enumerations.Color;

/**
 * Defines the wormhole.
 * 
 * @author Gaben's Disciples
 * 
 */
public final class Wormhole {
  private static Map<Color, SpecWall> specWalls = new HashMap<Color, SpecWall>();

  /**
   * Sets the SpecWall objects to the wormhole.
   * 
   * @param specWall
   *          - the object
   * @param color
   *          - the SpecWall's color
   */
  public static void setSpecWall(SpecWall specWall, Color color) {
    if (specWalls.containsKey(color)) {
      specWalls.get(color).setStarGate(null);
      specWalls.remove(color);
    }
    specWalls.put(color, specWall);
  }

  /**
   * Gets the current SpecWall object.
   * 
   * @param color
   *          - the SpecWall's color
   * @return the SpecWall object
   */
  public static SpecWall getSpecWall(Color color) {
    if (specWalls.containsKey(color)) {
      return specWalls.get(color);
    } else {
      return null;
    }
  }
  
  public static void Clear(){
      specWalls.clear();
  }
}
