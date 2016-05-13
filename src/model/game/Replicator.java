package model.game;

import java.util.Random;

import model.cells.LevelObject;
import model.enumerations.Direction;
import view.View;

public class Replicator extends Character implements Runnable {
  public volatile boolean running = true;

  public Replicator(LevelObject position) {
    super(position);
    ReplicatorContainer.add(this);
  }
  
  public void die() {
    running = false;
    View.remove(this.ID);
    ReplicatorContainer.remove(this);
  }
  
  public void run() {
    Random rand = new Random();
    int randValue;
    Direction nextDir = Direction.NORTH;
      randValue = rand.nextInt(4);
      switch (randValue) {
        case 0:
          nextDir = Direction.NORTH;
          break;
        case 1:
          nextDir = Direction.EAST;
          break;
        case 2:
          nextDir = Direction.SOUTH;
          break;
        case 3:
          nextDir = Direction.WEST;
          break;
      }
      this.move(nextDir);
  }
}
