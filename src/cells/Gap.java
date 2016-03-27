package cells;

import enumerations.ItemState;
import game.Bullet;
import game.Character;
import logger.Logger;

/**
 * LevelObject class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Gap extends LevelObject {

  /**
   * Gap constructor.
   */
  public Gap() {
    super(false);
    Logger.log("Gap konstruktor");
    Logger.logout();
  }

  /**
   * Checks if the cell has any item.
   */
  public ItemState hasItem() {
    Logger.log("Gap hasItem");
    Logger.logout();
    return ItemState.FORBIDDENAREA;
  }

  /**
   * Makes interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
    Logger.log("Gap interactCharacter");
    character.die();
    Logger.logout();
  }

  /**
   * Makes interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
    Logger.log("Gap interactBullet");
    bullet.setPosition(this);
    Logger.logout();
  }
}
