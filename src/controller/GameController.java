package controller;

import model.game.Replicator;
import model.game.Player;
import model.game.WorkerThread;

public class GameController {
  private WorkerThread worker;
  
  public GameController(){
    
  }

  public void addPressedKey(String keyCode){
    synchronized(WorkerThread.instructionQueue){
      WorkerThread.instructionQueue.add(keyCode);
    }
    //worker.notify();
  }
  
  public void startWorkerThread(Player oneill, Player jaffa, Replicator replicator){
    worker = new WorkerThread(oneill, jaffa, replicator);
    new Thread(worker).start();
  }
}


