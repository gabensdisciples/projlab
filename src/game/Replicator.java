package game;

import cells.LevelObject;
import logger.Logger;

public class Replicator extends Character {

  public Replicator(LevelObject position) {
    super(position);
  }
  
  public void die() {
    //GUI itt szedi le a replikatort
    ReplicatorContainer.remove(this);
  }
}
