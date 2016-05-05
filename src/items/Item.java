package items;

import game.IdentifiedObject;
import game.Player;

/**
 * Abstract item class.
 * 
 * @author Gaben's Disciples
 * 
 */
public abstract class Item extends IdentifiedObject{

  public abstract void pickUp(Player player);
}
