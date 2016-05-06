package model.game;

import model.cells.LevelObject;
import model.enumerations.Color;
import model.enumerations.Direction;

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
    this.position = position;
    this.direction = direction;
    this.color = color;
    collision = false;
  }

  /**
   * Creates interaction among the bullet and cells. Makes the bullet move.
   */
  public void fly() {
    while (!collision && position != null) {
      position.interactBullet(this);
      if (!collision) {
        position = position.getNeighbour(direction, false);
      }
    }
  }

  /**
   * Sets the bullet's position.
   * 
   * @param position
   *          - the cell where the bullet is
   */
  public void setPosition(LevelObject position) {
    this.position = position;
  }

  /**
   * Destroy bullet.
   */
  public void die() {
    collision = true;
  }

  /**
   * Creates a stargate.
   */
  public StarGate createStarGate() {
    return new StarGate(color);
  }
}
