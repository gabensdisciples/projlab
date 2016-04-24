package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import test.Test;
import enumerations.Direction;
import game.Player;
import game.Replicator;

public class CommandHandler {

  private static Player oneill;
  private static Player jaffa;
  private static Replicator replicator;
  public static LevelBuilder levelBuilder;
  public static boolean autoTest;
  private static Scanner in = new Scanner(System.in);

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
  public static void setLevelBuilder(LevelBuilder levelbuilder) {
    levelBuilder = levelbuilder;
  }

  /**
   * Sets autotest per user's choice.
   * 
   * @return true, if setting was successful, else false
   */

  public static boolean setAutoTest() throws Exception {
    System.out.println("1. Automatikus teszteles");
    System.out.println("2. Manualis teszteles");
    int mode = in.nextInt();
    if (mode == 1) {
      autoTest = true;
      System.out.println("Automatikus teszteles kivalasztva.");
      return true;
    } else if (mode == 2) {
      autoTest = false;
      System.out.println("Manualis teszteles kivalasztva. Kerlek, adj parancsokat!");
      return true;
    } else {
      System.out.println("Ervenytelen azonosito.");
      return false;
    }
  }

  /**
   * Shows menu and gets commands for automatic testing.
   */

  public static void autoTest() {
    System.out.println("Nyomj egy entert a menu megjelenitesehez");
    in.nextLine();
    in.nextLine();
    int caseNumber = 1;
    System.out.println(caseNumber + ". " + "Toltenyt valt (changeBulletColor())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Lő (shootBullet())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Lövedék falnak ütközik (bulletMeetsWall())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Lövedék speciális falnak ütközik (bulletMeetsSpecWall())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Lövedék replikátornak ütközik (bulletMeetsReplicator())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Lövedék ajtóba ütközik (bulletMeetsDoor())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Lövedék nem ütközik (bulletFly())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Tárgyat sikeresen felvesz (pickupItemSuccessfully())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Tárgyat felvesz tiltott mezőről (pickupItemFromForbiddenArea())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Tárgyat felvesz tárgyat nem tartalmazó mezőről (pickupItemNoItem())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt felvesz, de már van (pickupSecondBox())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt felvesz mérlegről (pickupBoxFromScale())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt sikeresen letesz (dropBoxSuccesfully())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt letesz tiltott mezőre (dropBoxForbiddenArea())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt letesz tárgyat tartalmazó mezőre (dropBoxGotItem())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt letesz, de még nincs (dropBoxNoBox())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Dobozt letesz mérlegre (dropBoxOnScale())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter átjárható mezőre lép (stepOnWalkable())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter falnak ütközik (playerHitWall())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter szakadékba lép (stepOnGap())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Replikátor szakadékba lép (replicatorStepOnGap())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter csillagkapuba lép (stepOnStarGate())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter féregjáratba lép (stepInWormHole())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter ajtóba ütközik (stepOnDoorNonWalkable())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter nyílt ajtóra lép (stepOnDoorWalkable())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter mérlegre lép (stepOnScale())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Karakter mérlegről lelép (stepOffScale())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Mérleg aktiválása (activateScale())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Mérleg deaktiválása (deActivateScale())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Súgó (helpTest())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Pálya betöltés (loadMapTest())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Harmadik ZPM megjelenése (thirdZpmAppear())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Játék befejezése (gameOver())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "ZPM számláló növekedés (zpmCountIncrement())");
    caseNumber++;
    System.out.println(caseNumber + ". " + "Kilépés");

    
    System.out.println("\nKerlek valaszd ki a teszteset kodjat: ");

    caseNumber = in.nextInt();
    System.out.println(caseNumber);
    boolean outputMatchesExpected = false;
    // TODO Kitolteni ha veglegesek a tesztek
    try {
      switch (caseNumber) {
        case 1:
          outputMatchesExpected = Test.changeBulletColor();
          break;
        case 2:
          outputMatchesExpected = Test.shootBullet();
          break;
        case 3:
          outputMatchesExpected = Test.bulletMeetsWall();
          break;
        case 4:
          outputMatchesExpected = Test.bulletMeetsSpecWall();
          break;
        case 5:
          outputMatchesExpected = Test.bulletMeetsReplicator();
          break;
        case 6:
          outputMatchesExpected = Test.bulletMeetsDoor();
          break;
        case 7:
          outputMatchesExpected = Test.bulletFly();
          break;
        case 8:
          outputMatchesExpected = Test.pickupItemSuccessfully();
          break;
        case 9:
          outputMatchesExpected = Test.pickupItemFromForbiddenArea();
          break;
        case 10:
          outputMatchesExpected = Test.pickupItemNoItem();
          break;
        case 11:
          outputMatchesExpected = Test.pickupSecondBox();
          break;
        case 12:
          outputMatchesExpected = Test.pickupBoxFromScale();
          break;
        case 13:
          outputMatchesExpected = Test.dropBoxSuccesfully();
          break;
        case 14:
          outputMatchesExpected = Test.dropBoxForbiddenArea();
          break;
        case 15:
          outputMatchesExpected = Test.dropBoxGotItem();
          break;
        case 16:
          outputMatchesExpected = Test.dropBoxNoBox();
          break;
        case 17:
          outputMatchesExpected = Test.dropBoxOnScale();
          break;
        case 18:
          outputMatchesExpected = Test.stepOnWalkable();
          break;
        case 19:
          outputMatchesExpected = Test.playerHitWall();
          break;
        case 20:
          outputMatchesExpected = Test.stepOnGap();
          break;
        case 21:
          outputMatchesExpected = Test.replicatorStepOnGap();
          break;
        case 22:
          outputMatchesExpected = Test.stepOnStarGate();
          break;
        case 23:
          outputMatchesExpected = Test.stepInWormHole();
          break;
        case 24:
          outputMatchesExpected = Test.stepOnDoorNonWalkable();
          break;
        case 25:
          outputMatchesExpected = Test.stepOnDoorWalkable();
          break;
        case 26:
          outputMatchesExpected = Test.stepOnScale();
          break;
        case 27:
          outputMatchesExpected = Test.stepOffScale();
          break;
        case 28:
          outputMatchesExpected = Test.activateScale();
          break;
        case 29:
          outputMatchesExpected = Test.deActivateScale();
          break;
        case 30:
          Test.helpTest();
          break;
        case 31:
          outputMatchesExpected = Test.loadMapTest();
          break;
        case 32:
          outputMatchesExpected = Test.thirdZpmAppear();
          break;
        case 33:
          Test.gameOver();
          break;
        case 34:
          outputMatchesExpected = Test.zpmCountIncrement();
          break;
        case 35:
          System.exit(0);
          break;
        default:
          System.out.println("Ervenytelen menupont");
      }
      if (outputMatchesExpected) {
        System.out.println("A kimenet megegyezik az elvárttal");
      } else {
        System.out.println("A kimenet nem egyezik meg az elvárttal");
      }
    } catch (IOException e) {
      System.out.println("Palyafajl hiba");
    }
  }

  /**
   * Gets a new command from standard input.
   * 
   * @return the command extracted from stdin.
   */
  public static String getCommand() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    String command = bufferedReader.readLine();
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
  public static void processFile(String path) throws IOException {
    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String command;

    // Iterate through the file
    while ((command = bufferedReader.readLine()) != null) {
      // Try to execute command. If it was a failure, warn user.
      boolean success = executeCommand(command);
      if (!success) {
        System.out.println("A kovetkezo parancs nem hajtodott vegre hibas szintaxis miatt: " + command);
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
  public static boolean executeCommand(String command) {
    String[] commandParams = command.split(" ");

    if (commandParams[0].equals("loadmap")) {
      if (commandParams.length != 2) {
        return false;
      }
      try {
        levelBuilder.init(commandParams[1]);
        oneill = levelBuilder.getOneill();
        jaffa = levelBuilder.getJaffa();
        replicator = levelBuilder.getReplicator();
      }

      catch (Exception e) {
        System.out.println("A parancs vegrehajtasa sikertelen volt.");
      }
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
        System.out.println(oneill.getZpmCount());
      } else if (commandParams[1].equals("jaffa")) {
        System.out.println(jaffa.getZpmCount());
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

      // EXIT
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

  public static void executeCommandLines(String commands) {
    String[] commandsArray = commands.split("\n");
    for (String command : commandsArray) {
      executeCommand(command);
    }
  }
}
