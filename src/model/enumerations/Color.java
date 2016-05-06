package model.enumerations;

import model.logger.Logger;

/**
 * Enum class.
 * 
 * @author Gaben's Disciples
 * 
 */
public enum Color {

  BLUE, YELLOW, GREEN, RED;

  /**
   * Gets the other different color form the actual one.
   * 
   * @return the color enum value
   */
  public Color getOtherColor() {
    Logger.log("Color getOtherColor");
    Logger.logout();
    switch (this) {
      case BLUE:
        return Color.YELLOW;
      case YELLOW:
        return Color.BLUE;
      case GREEN:
        return Color.RED;
      case RED:
        return Color.GREEN;
      default:
        break;
    }
    return null;
  }
}
