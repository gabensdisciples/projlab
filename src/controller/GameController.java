package controller;

import model.enumerations.Direction;
import model.game.Player;
import model.game.WorkerThread;

public class GameController {
  //
  private Player oneill;
  private Player jaffa;
  //
  
  public GameController(){
    
  }

  public void addPressedKey(String keyCode){
    synchronized(WorkerThread.instructionQueue){
      WorkerThread.instructionQueue.add(keyCode);
    }
    switch (WorkerThread.instructionQueue.pollFirst()){
      case "W": oneill.move(Direction.NORTH);break;
      case "S": oneill.move(Direction.SOUTH);break;
      case "A": oneill.move(Direction.WEST);break;
      case "D": oneill.move(Direction.EAST);break;
      case "Q": oneill.changeColor();break;
      case "E": oneill.shoot();break;
      case "C": oneill.take();break;
      case "V": oneill.drop();break;
      case "I": jaffa.move(Direction.NORTH);break;
      case "K": jaffa.move(Direction.SOUTH);break;
      case "J": jaffa.move(Direction.WEST);break;
      case "L": jaffa.move(Direction.EAST);break;
      case "U": jaffa.changeColor();break;
      case "O": jaffa.shoot();break;
      case "N": jaffa.take();break;
      case "M": jaffa.drop();break;
      default: break;
    }
    //worker.notify();
  }
  
  public void startWorkerThread(Player oneill, Player jaffa){
    this.oneill = oneill;
    this.jaffa = jaffa;
    //worker = new WorkerThread(oneill, jaffa, replicator);
    //new Thread(worker).start();
  }
}


