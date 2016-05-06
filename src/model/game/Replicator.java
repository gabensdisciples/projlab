package model.game;

import model.cells.LevelObject;
import view.View;

public class Replicator extends Character {

  public Replicator(LevelObject position) {
    super(position);
    ReplicatorContainer.add(this);
  }
  
  public void die() {
    View.remove(this.ID);
    ReplicatorContainer.remove(this);
  }
}
