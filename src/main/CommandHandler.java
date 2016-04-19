package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import enumerations.Direction;
import game.Player;
import game.Replicator;

public class CommandHandler {

  private Player oneill;
  private Player jaffa;
  private Replicator replicator;
  private LevelBuilder levelBuilder;
  public boolean autoTest;
  private Scanner in = new Scanner(System.in);

  /**
   * CommandHandler constructor.
   * 
   * @param oneill
   *          - reference to oneill
   * @param jaffa
   *          - reference to the jaffa
   * @param replicator
   *          - reference to the replicator
   */
  public CommandHandler(Player oneill, Player jaffa, Replicator replicator, LevelBuilder levelBuilder) {
    /*
     * TODO Ha a levelbuilderbol kinyerheto ez a 3 referencia, akkor nem is kell
     */
    this.levelBuilder = levelBuilder;
    this.oneill = oneill;
    this.jaffa = jaffa;
    this.replicator = replicator;
  }
  
  /**
   * Sets autotest per user's choice.
   * @return true, if setting was successful, else false
   */
  
  public boolean setAutoTest() {
    System.out.println("1. Automatikus teszteles");
    System.out.println("2. Manualis teszteles");

    int mode = in.nextInt();
    if (mode == 1) {
      autoTest = true;
      return true;
    } else if (mode == 2) {
      autoTest = false;
      return true;
    } else {
      System.out.println("Ervenytelen azonosito, add meg ujra!");
      return false;
    }
  }

  /**
   * Shows menu and gets commands for automatic testing.
   */
  
  public void autoTest() {
    int caseNumber = 1;
    System.out.println(caseNumber + ". " + "Toltenyt valt (changeBulletColor())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Sugo (openHelp())");
    caseNumber++;
    System.out.println("Felvesz tesztesetek:");
    System.out.println("\t" + caseNumber + ". " + "Felvesz dobozt (pickupBox())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Felvenne, de nincs mit (pickupForbidden())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Felvesz ZPM-et (pickupZPM())");
    caseNumber++;
    System.out.println("Letesz tesztesetek:");
    System.out.println("\t" + caseNumber + ". " + "Letesz dobozt (placeBox())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Letenne, de nem tud (placeBoxForbidden())");
    caseNumber++;
    System.out.println("Loves tesztesetek:");
    System.out.println("\t" + caseNumber + ". " + "Lo zart ajtora vagy falra (shootDoorOrWall())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Lo atjarható mezok felett (shootOverWalkable())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Lo Spec Fal (shootSpecWall())");
    caseNumber++;
    System.out.println("Lepes tesztesetek:");
    System.out.println("\t" + caseNumber + ". " + "Ajtora lep (walkDoor())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Lep (walkFloorOrWall())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Szakadekba lep (walkGap())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Merlegre lep (walkScale())");
    caseNumber++;
    System.out.println("\t" + caseNumber + ". " + "Feregjaratba lep (walkSpecWallStarGate())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Kilepes a programbol");

    System.out.println("\nKerlek valaszd ki a teszteset kodjat: ");

    caseNumber = in.nextInt();
    System.out.println(caseNumber);
    
    //TODO Kitolteni ha veglegesek a tesztek
    switch (caseNumber) {
      case 1:
        break;
      default:
        System.out.println("Ervenytelen menupont");
    }
  }

  /**
   * Gets a new command from standard input.
   * 
   * @return the command extracted from stdin.
   */
  public String getCommand() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    String command = bufferedReader.readLine();
    bufferedReader.close();
    return command;
  }

  /**
   * Reads all lines from the given file, and executes each line interpreted as
   * a command. If a command is syntactically incorrect, that command is
   * skipped, but all others are still attempted to be executed.
   * 
   * @param path
   *          - path of the file to be opened
   */
  public void processFile(String path) throws IOException {
    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String command;

    // Iterate through the file
    while ((command = bufferedReader.readLine()) != null) {
      // Try to execute command. If it was a failure, warn user.
      boolean success = executeCommand(command);
      if (!success) {
        System.out.println("The following command was skipped due to incorrect syntax: " + command);
      }
    }

    bufferedReader.close();
  }

  /**
   * Verifies the specified command's validity, and executes it, if it was found
   * valid.
   * 
   * @param command
   *          - the command to be verified
   * 
   * @return true, if execution was successful, else false
   */
  public boolean executeCommand(String command) {
    /*
     * TODO Megjegyzes: szokozon valo splitteles miatt fajl eleresi ut szokozt
     * nem tartalmazhat!
     */
    String[] commandParams = command.split(" ");
    if (commandParams[0].equals("loadmap")) {
      if (commandParams.length != 2) {
        return false;
      }

      // TODO Levelbuilder jelenleg nem tamogatja ezt a funkciot

      // MOVE
    } else if (commandParams[0].equals("move")) {
      if (commandParams.length != 3) {
        return false;
      }

      // Set direction to move in
      Direction dir;

      if (commandParams[2].equals("n")) {
        dir = Direction.NORTH;
      } else if (commandParams[2].equals("e")) {
        dir = Direction.EAST;
      } else if (commandParams[2].equals("s")) {
        dir = Direction.SOUTH;
      } else if (commandParams[2].equals("w")) {
        dir = Direction.WEST;

        // If there was no match, then direction parameter was invalid
      } else {
        return false;
      }

      // Move the given character
      if (commandParams[1].equals("oneill")) {
        oneill.move(dir);
      } else if (commandParams[1].equals("jaffa")) {
        jaffa.move(dir);
      } else if (commandParams[1].equals("replicator")) {
        replicator.move(dir);

        // If there was no match, then character parameter was invalid
      } else {
        return false;
      }

      // CHANGEBULLET
    } else if (commandParams[0].equals("changebullet")) {
      if (commandParams.length != 2) {
        return false;
      }

      if (commandParams[1].equals("oneill")) {
        oneill.changeColor();
      } else if (commandParams[1].equals("jaffa")) {
        jaffa.changeColor();

        // If there was no match, then character parameter was invalid
      } else {
        return false;
      }

      // SHOOT
    } else if (commandParams[0].equals("shoot")) {
      if (commandParams.length != 2) {
        return false;
      }

      if (commandParams[1].equals("oneill")) {
        oneill.shoot();
      } else if (commandParams[1].equals("jaffa")) {
        jaffa.shoot();

        // If there was no match, then character parameter was invalid
      } else {
        return false;
      }

      // PICKUP
    } else if (commandParams[0].equals("pickup")) {
      if (commandParams.length != 2) {
        return false;
      }

      if (commandParams[1].equals("oneill")) {
        oneill.take();
      } else if (commandParams[1].equals("jaffa")) {
        jaffa.take();

        // If there was no match, then character parameter was invalid
      } else {
        return false;
      }

      // DROP
    } else if (commandParams[0].equals("drop")) {
      if (commandParams.length != 2) {
        return false;
      }

      if (commandParams[1].equals("oneill")) {
        oneill.drop();
      } else if (commandParams[1].equals("jaffa")) {
        jaffa.drop();

        // If there was no match, then character parameter was invalid
      } else {
        return false;
      }

      // PRINTMAP
    } else if (commandParams[0].equals("printmap")) {
      if (commandParams.length != 1) {
        return false;
      }

      levelBuilder.printStringMatrix();

      // HELP
    } else if (commandParams[0].equals("help")) {
      if (commandParams.length != 1) {
        return false;
      }

      Menu.showHelp();

      // ZPMCOUNT
    } else if (commandParams[0].equals("zpmcount")) {
      if (commandParams.length != 2) {
        return false;
      }

      if (commandParams[1].equals("oneill")) {
        // TODO Player jelenleg nem tamogatja ezt a funkciot
      } else if (commandParams[1].equals("jaffa")) {

        // If there was no match, then character parameter was invalid
      } else {
        return false;
      }

      // SETRANDOMZPMPOSITION
    } else if (commandParams[0].equals("setrandomzpmposition")) {
      if (commandParams.length != 5) {
        return false;
      }

      // Integer.parseInt can throw an exception
      try {
        for (int i = 1; i < commandParams.length; i++) {
          Menu.randomZpmOffset[i - 1] = Integer.parseInt(commandParams[i]);
        }
      }

      catch (Exception e) {
        return false;
      }

      // LOADFILE
    } else if (commandParams[0].equals("loadfile")) {
      if (commandParams.length != 2) {
        return false;
      }

      try {
        processFile(commandParams[1]);
      }

      catch (IOException e) {
        return false;
      }
    } else if (commandParams[0].equals("exit")) {
      if (commandParams.length != 1) {
        return false;
      }

      System.exit(0);
      // If command matched no existing commands, then it was invalid
    } else {
      return false;
    }

    // If nothing was invalid, then execution was successful
    return true;
  }
}
