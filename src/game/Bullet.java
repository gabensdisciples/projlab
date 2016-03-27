package game;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import logger.Logger;

/**
 * Defines the bullet.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Bullet {
  private Direction direction;
  private Color color;
  private LevelObject position;

  /**
   * Bullet constructor.
   * 
   * @param position
   *          - the bullet's position
   * @param direction
   *          - the shooting direction
   * @param color
   *          - the bullet's color
   */
  public Bullet(LevelObject position, Direction direction, Color color) {
    Logger.log("Bullet konstruktor");
    this.position = position;
    this.direction = direction;
    this.color = color;
    Logger.logout();
  }

  /**
   * Creates interaction among the bullet and cells. Makes the bullet move.
   */
  public void fly() {
    Logger.log("Bullet fly");
    while (position.isWalkable()) {
      position = position.getNeighbour(direction, false);
      position.interactBullet(this);
    }
    Logger.logout();
  }

  /**
   * Sets the bullet's position.
   * 
   * @param position
   *          - the cell where the bullet is
   */
  public void setPosition(LevelObject position) {
    Logger.log("Bullet setPosition");
    this.position = position;
    Logger.logout();
  }

  public void die() {
    Logger.log("Bullet die");
    Logger.logout();
  }

  /**
   * Creates a stargate.
   */
  public StarGate createStarGate() {
    Logger.log("Bullet createStarGate");
    Logger.logout();
    return new StarGate(color);
  }
}
