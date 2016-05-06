package model.game;

import model.enumerations.Color;

/**
 * Defines the stargate.
 * 
 * @author Gaben's Disciples
 * 
 */
public class StarGate extends IdentifiedObject{

  private Color color;

  /**
   * Sets the stargate's color.
   * 
   * @param color
   *          - color to set
   */
  public StarGate(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return color;
  }
}
