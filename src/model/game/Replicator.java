package model.game;

import model.cells.LevelObject;

public class Replicator extends Character {

  public Replicator(LevelObject position) {
    super(position);
    ReplicatorContainer.add(this);
  }
  
  public void die() {
    //GUI itt szedi le a replikatort
    ReplicatorContainer.remove(this);
  }
}
