package controller;

import model.enumerations.Color;
import model.enumerations.Direction;
import model.game.Player;
import model.game.WorkerThread;
import view.View;

public class GameController {
  //
  private Player oneill;
  private Player jaffa;
  //
  
  public GameController(Player oneill, Player jaffa) {
    this.oneill = oneill;
    this.jaffa = jaffa;
  }

  public void execute(String keyCode){
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
  }
  
  public static void gameOver(Player player, int zpmsMax) {
    if (player.getPosition() == null) {
      if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
        View.gameOver("O'Neill died, Jaffa won!");
      } else {
        View.gameOver("Jaffa died, O'Neill won!");
      }

    } else if (player.getZpmCount() > zpmsMax - player.getZpmCount()) {
      if (player.getColor() == Color.BLUE || player.getColor() == Color.YELLOW) {
        View.gameOver("O'Neill won!\nO'Neill zpm(s): " + player.getZpmCount() + "\nJaffa zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      } else {
        View.gameOver("Jaffa won!\nJaffa zpm(s): " + player.getZpmCount() + "\nO'Neill zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      }
    } else if (player.getZpmCount() == zpmsMax - player.getZpmCount()) {
      View.gameOver("The game ended in a tie.\nO'Neill zpm(s): " + player.getZpmCount() + "\nJaffa zpm(s): "
          + (zpmsMax - player.getZpmCount()));
    } else {
      if (player.getColor() != Color.BLUE && player.getColor() != Color.YELLOW) {
        View.gameOver("O'Neill won!\nJaffa zpm(s): " + player.getZpmCount() + "\nO'Neill zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      } else {
        View.gameOver("Jaffa won\nO'Neill zpm(s): " + player.getZpmCount() + "\nJaffa zpm(s): "
            + (zpmsMax - player.getZpmCount()));
      }
    }
  }
}


