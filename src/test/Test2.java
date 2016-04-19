package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import enumerations.Direction;
import game.Player;
import main.LevelBuilder;

public class Test2 {
  
  public static LevelBuilder levelBuilder;
  public static Player player;
  
  public static void main(String[] args) {
    init();
    levelBuilder.testScale();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String line;
    try {
      while( (line = br.readLine()) != null) {
      interact(line);
      levelBuilder.printStringMatrix();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void init() {
    levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init(6);
    levelBuilder.printStringMatrix();
    player = levelBuilder.getOneill();
  }
  
  public static void interact(String s) {
    
    switch(s) {
      case "a":
        player.move(Direction.WEST);
        break;
      case "d":
        player.move(Direction.EAST);
        break;
      case "w":
        player.move(Direction.NORTH);
        break;
      case "s":
        player.move(Direction.SOUTH);
        break;
      case "b":
        player.take();
        break;
      case "v":
        // WTF
        // player.drop(player.getBox());
        break;
    }
  }
  
}