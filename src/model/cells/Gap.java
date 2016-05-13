package model.cells;

import model.enumerations.Direction;
import model.enumerations.ItemState;
import model.game.Bullet;
import model.game.Character;
import model.game.Player;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.items.Item;
import view.View;

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
  }

  /**
   * Checks if the cell has any item.
   */
  public ItemState hasItem() {
      return ItemState.FORBIDDENAREA;
  }

  /**
   * Makes interaction between the character and level objects.
   */
  public void interactCharacter(Character character) {
      character.setPosition(this);
      //If a replicator walked into the gap, fill with a floor
      if (ReplicatorContainer.getReplicator(this) != null) {
        //Create a floor 
        Floor floor = new Floor(true, null);
        
        //Get the gap's neighbours
        LevelObject neighbourNorth =  this.getNeighbour(Direction.NORTH, false);
        LevelObject neighbourEast =  this.getNeighbour(Direction.EAST, false);
        LevelObject neighbourSouth =  this.getNeighbour(Direction.SOUTH, false);
        LevelObject neighbourWest =  this.getNeighbour(Direction.WEST, false);
        
        //Set the floor's neighbours
        floor.setNeighbour(Direction.NORTH, neighbourNorth);
        floor.setNeighbour(Direction.EAST, neighbourEast);
        floor.setNeighbour(Direction.SOUTH, neighbourSouth);
        floor.setNeighbour(Direction.WEST, neighbourWest);
        
        
      //Set the floor to be the gap's neighbours' neighbour
        if (neighbourNorth != null) {
          neighbourNorth.setNeighbour(Direction.SOUTH, floor);
        }
        
        if (neighbourEast != null) {
          neighbourEast.setNeighbour(Direction.WEST, floor);
        }
        
        if (neighbourSouth != null) {
          neighbourSouth.setNeighbour(Direction.NORTH, floor);
        }
        
        if (neighbourWest != null) {
          neighbourWest.setNeighbour(Direction.EAST, floor);
        }
        
        View.create(floor.ID, this.ID, "floor.png");
        View.remove(this.ID);
      }
      
      character.die();
  }

  /**
   * Makes interaction between the bullet and level objects.
   */
  public void interactBullet(Bullet bullet) {
      bullet.setPosition(this);
  }
}
