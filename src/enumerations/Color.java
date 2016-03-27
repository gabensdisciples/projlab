package enumerations;

import logger.Logger;

/**
 * Enum class.
 * 
 * @author Gaben's Disciples
 * 
 */
public enum Color {

  BLUE, YELLOW;

  // TODO: Valtozott a szignatura, dokumentalni kell!
  /**
   * Gets the other different color form the actual one.
   * 
   * @return the color enum value
   */
  public Color getOtherColor() {
    Logger.log("Color getOtherColor");
    Logger.logout();
    if (this == Color.BLUE) {
      return Color.YELLOW;
    } else {
      return Color.BLUE;
    }
  }
}
