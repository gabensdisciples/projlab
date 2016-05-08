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
    //Determine the name of the image to be used based on the gate's color.
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
    
    //If a gate of the given color already exists, but not on the given specwall
    if (specWalls.containsKey(color) && specWalls.get(color) != specWall) {
      View.remove(specWalls.get(color).getStarGate().ID);
      specWalls.get(color).setStarGate(null);
      specWalls.remove(color);
      specWalls.put(color, specWall);
      View.create(specWall.getStarGate().ID, specWall.ID, imagename);
    }
    
    //If a gate of the given color doesnt exist yet
    else if (!specWalls.containsKey(color)){
      specWalls.put(color, specWall);
      View.create(specWall.getStarGate().ID, specWall.ID, imagename);
    }
    
    //If the gate of the given color exists on the given specwall
    else {
      return;
    }
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

  public static void Clear() {
    specWalls.clear();
  }
}
