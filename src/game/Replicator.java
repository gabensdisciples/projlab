package game;

import cells.LevelObject;
import logger.Logger;

public class Replicator extends Character {

  public Replicator(LevelObject position) {
    super(position);
    Logger.log("Replicator konstruktor");
    Logger.logout();
  }
  
  public void die() {
    Logger.log("Replicator die");
    //GUI itt szedi le a replikatort
    
    ReplicatorContainer.remove(this);
    Logger.logout();
  }
}
