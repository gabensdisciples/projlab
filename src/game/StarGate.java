package game;

import enumerations.Color;
import logger.Logger;

/**
 * Defines the stargate.
 * 
 * @author Gaben's Disciples
 * 
 */
public class StarGate {

  private Color color;

  /**
   * Sets the stargate's color.
   * 
   * @param color
   *          - color to set
   */
  public StarGate(Color color) {
    Logger.log("StarGate konstruktor");
    this.color = color;
    Logger.logout();
  }

  public Color getColor(){
    return color;
  }
}
