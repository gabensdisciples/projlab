package game;

import java.util.ArrayList;

import cells.LevelObject;

public class ReplicatorContainer {
  private static ArrayList<Replicator> replicators = new ArrayList<Replicator>();
  
  public static Replicator getReplicator(LevelObject position) {
    for (int i = 0; i < replicators.size(); i++) {
      Replicator temp = replicators.get(i);
      if (temp.getPosition() == position) {
        return temp;
      }
    }
    
    return null;
  }
  
  public static void remove(Replicator replicator) {
    replicators.remove(replicator);
  }
}
