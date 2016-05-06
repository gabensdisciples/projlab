package model.game;

import model.cells.LevelObject;
import model.enumerations.Color;
import model.enumerations.Direction;
import model.logger.Logger;

/**
 * Defines the bullet.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Bullet extends IdentifiedObject{
  private Direction direction;
  private Color color;
  private LevelObject position;
  private boolean collision;

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
    collision = false;
    Logger.logout();
  }

  /**
   * Creates interaction among the bullet and cells. Makes the bullet move.
   */
  public void fly() {
    Logger.log("Bullet fly");
    while (!collision && position != null) {
      position.interactBullet(this);
      if (!collision) {
        position = position.getNeighbour(direction, false);
      }
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

  /**
   * Destroy bullet.
   */
  public void die() {
    Logger.log("Bullet die");
    collision = true;
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
