package enumerations;

import logger.Logger;

/**
 * Enum class.
 * 
 * @author Gaben's Disciples
 * 
 */
public enum Color {

  BLUE, YELLOW, GREEN, RED;

  // TODO: Valtozott a szignatura, dokumentalni kell!
  /**
   * Gets the other different color form the actual one.
   * 
   * @return the color enum value
   */
  public Color getOtherColor(Color c) {
    Logger.log("Color getOtherColor");
    Logger.logout();
    switch (c) {
      case BLUE:return Color.BLUE;
      case YELLOW:return Color.BLUE;
      case GREEN:return Color.BLUE;
      case RED:return Color.BLUE;
    }
    return null;
  }
}
