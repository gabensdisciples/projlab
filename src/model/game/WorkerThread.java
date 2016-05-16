package model.game;

import java.util.LinkedList;

import model.enumerations.Direction;

public class WorkerThread implements Runnable{
  private Player oneill;
  private Player jaffa;
  private Replicator replicator;
  public static LinkedList<String> instructionQueue = new LinkedList<String>();
  
  public WorkerThread(Player oneill, Player jaffa, Replicator replicator){
    this.oneill = oneill;
    this.jaffa = jaffa;
    this.replicator = replicator;
  }
  
  public void run(){
    int i = 0;
    while(i <100){
      
      if(!instructionQueue.isEmpty()){
        synchronized(instructionQueue){
          switch (instructionQueue.pollFirst()){
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
        }
      }
      else{
        try {
          Thread.sleep(300);
          System.out.println(i++);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
