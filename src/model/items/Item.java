package model.items;

import model.game.IdentifiedObject;
import model.game.Player;

/**
 * Abstract item class.
 * 
 * @author Gaben's Disciples
 * 
 */
public abstract class Item extends IdentifiedObject{

  public abstract void pickUp(Player player);
}
