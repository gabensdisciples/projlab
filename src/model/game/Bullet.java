package model.game;

import model.cells.LevelObject;
import model.enumerations.Color;
import model.enumerations.Direction;
import view.View;

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
    
    String imagename = null;
    if (color == Color.RED) {
      imagename = "red_bullet.png";
    } else if (color == Color.YELLOW) {
      imagename = "yellow_bullet.png";
    } else if (color == Color.BLUE) {
      imagename = "blue_bullet.png";
    } else if (color == Color.GREEN) {
      imagename = "green_bullet.png";
    }
    View.create(this.ID, position.ID, imagename);
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
    //View.remove(this.ID);
  }

  /**
   * Sets the bullet's position.
   * 
   * @param position
   *          - the cell where the bullet is
   */
  public void setPosition(LevelObject position) {
    this.position = position;
    View.move(this.ID, position.ID);
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
