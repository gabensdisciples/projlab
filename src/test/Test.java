package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.Scale;
import cells.SpecWall;
import enumerations.Color;
import enumerations.Direction;
import game.Player;
import game.StarGate;
import game.Wormhole;
import items.Box;
import items.Zpm;
import logger.Logger;
import main.CommandHandler;
import main.Menu;

/**
 * Test class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Test {
  
  public static void changeBulletColor() {
    CommandHandler.executeCommand("loadmap level_test1.txt");
    CommandHandler.executeCommand("move oneill e");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("changebullet oneill");
    CommandHandler.executeCommand("move oneill w");
    CommandHandler.executeCommand("shoot oneill");
    CommandHandler.executeCommand("printmap");
    
    
  }
}
