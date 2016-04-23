package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import enumerations.Direction;
import game.Player;
import game.Replicator;
import main.LevelBuilder;

/**
 *  Test class that shows the interaction among the level and the characters.
 *
 * Commands:
 *
 *   Oneill
 *   - movement      : a, s, d, w
 *   - shoot         : shoot 
 *   - take box      : take
 *   - drop box      : drop
 *   - change bullet : change
 *
 *   Jaffa
 *   -movement: 4, 6, 8, 2
 *   - shoot         : jshoot 
 *   - take box      : jtake
 *   - drop box      : jdrop
 *   - change bullet : jchange
 *   
 * @author Gaben's Disciples
 *
 */
public class GameTest {

  public static LevelBuilder levelBuilder;
  public static Player oneill;
  public static Replicator replicator;
  public static Player jaffa;
  public static final Direction[] directions = { 
      Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH
      };

  /**
   * Reads console input, move characters.
   * 
   * @param args
   *          - param
   */
  public static void main(String[] args) {
    init();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    try {
      while ((line = br.readLine()) != null) {
        
        if (oneill != null) {
          oneillInteract(line);
        }
        if (replicator != null) {
          replicatorInteract(line);
        }
        if (jaffa != null) {
          jaffaInteract(line);
        }
        levelBuilder.printStringMatrix();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * LevelBuilder initialization.
   */
  public static void init() {
    levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init("C:\\Users\\Mate\\Desktop\\projlab\\level\\level.txt");
    levelBuilder.printStringMatrix();
    oneill = levelBuilder.getOneill();
    replicator = levelBuilder.getReplicator();
    jaffa = levelBuilder.getJaffa();
  }

  /**
   * Oneill interactions.
   * 
   * @param command
   *          - input string
   */
  public static void oneillInteract(String command) {

    switch (command) {
      case "a":
        oneill.move(Direction.WEST);
        break;
      case "d":
        oneill.move(Direction.EAST);
        break;
      case "w":
        oneill.move(Direction.NORTH);
        break;
      case "s":
        oneill.move(Direction.SOUTH);
        break;
      case "take":
        oneill.take();
        break;
      case "drop":
        oneill.drop();
        break;
      case "change":
        oneill.changeColor();
        break;
      case "shoot":
        oneill.shoot();
        break;
      default:
        break;
    }
  }
  
  public static void replicatorInteract(String command) {

    switch (command) {
      case "j":
        replicator.move(Direction.WEST);
        break;
      case "l":
        replicator.move(Direction.EAST);
        break;
      case "i":
        replicator.move(Direction.NORTH);
        break;
      case "k":
        replicator.move(Direction.SOUTH);
        break;
      default:
        break;
    }
  }
  

  /**
   * Jaffa interactions.
   * 
   * @param command
   *          - console parameter as string
   */
  public static void jaffaInteract(String command) {
    switch (command) {
      case "4":
        jaffa.move(Direction.WEST);
        break;
      case "6":
        jaffa.move(Direction.EAST);
        break;
      case "8":
        jaffa.move(Direction.NORTH);
        break;
      case "2":
        jaffa.move(Direction.SOUTH);
        break;
      case "jtake":
        jaffa.take();
        break;
      case "jdrop":
        jaffa.drop();
        break;
      case "jchange":
        jaffa.changeColor();
        break;
      case "jshoot":
        jaffa.shoot();
        break;
      default:
        break;
    }
  }

  /**
   * Replicator does random movement after every command.
   */
  public static void replicatorInteract() {
    Random random = new Random();
    int directionIndex = random.nextInt(3) + 1;
    replicator.move(directions[directionIndex]);
  }

}