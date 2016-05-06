package model.game;

import java.util.HashMap;
import java.util.Map;
import model.cells.SpecWall;
import model.enumerations.Color;
import view.View;

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
      View.remove(specWalls.get(color).getStarGate().ID);
      specWalls.remove(color);
    }
    
    String imagename = null;
    if (color == Color.RED) {
      imagename = "red_stargate.png";
    } else if (color == Color.YELLOW) {
      imagename = "yellow_stargate.png";
    } else if (color == Color.BLUE) {
      imagename = "blue_stargate.png";
    } else if (color == Color.GREEN) {
      imagename = "green_stargate.png";
    }
    
    specWalls.put(color, specWall);
    View.create(specWalls.get(color).getStarGate().ID, specWalls.get(color).ID, imagename);
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
