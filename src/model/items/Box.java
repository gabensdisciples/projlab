package model.items;
import model.game.Player;
import view.View;

/**
 * Defines the box.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Box extends Item {

  /**
   * Set's the character box reference to itself.
   */
  public void pickUp(Player player) {
    player.setBox(this);
    View.remove(this.ID);
  }
}
