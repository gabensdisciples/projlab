package test;

import java.util.Scanner;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.Scale;
import cells.SpecWall;
import enumerations.Color;
import enumerations.Direction;
import enumerations.ItemState;
import game.Character;
import game.StarGate;
import game.Wormhole;
import items.Box;
import items.Zpm;
import main.Menu;

/**
 * Test class.
 * 
 * @author Gaben's Disciples
 * 
 */
public class Test {

  private static Scanner in;
  
  /**
   * Main method for testing.
   * @param args - init params
   */
  public static void main(String[] args) {
    in = new Scanner(System.in);
    while (true) {
      testSwitch();
      in.nextLine();
      in.nextLine();
    }
  }
  
  /**
   * Choose test cases.
   */
  public static void testSwitch() {
    System.out.println("\nKerlek valaszd ki, melyik teszteset erdekel!\n");
    int caseNumber = 0;
    caseNumber++;
    System.out.println(caseNumber + " " + "Toltenyt valt (changeBulletColor())");
    caseNumber++;
    System.out.println(caseNumber + " " + "Sugo (openHelp())");
    caseNumber++;
    System.out.println("Felvesz tesztesetek:");
    System.out.println("\t" + caseNumber + " " + "Felvesz dobozt (pickupBox())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Felvenne, de nincs mit (pickupForbidden())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Felvesz ZPM-et (pickupZPM())");
    caseNumber++;
    System.out.println("Letesz tesztesetek:");
    System.out.println("\t" + caseNumber + " " + "Letesz dobozt (placeBox())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Letenne, de nem tud (placeBoxForbidden())");
    caseNumber++;
    System.out.println("Lövés tesztesetek:");
    System.out.println("\t" + caseNumber + " " + "Lo zart ajtora vagy falra (shootDoorOrWall())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Lo atjarható mezok felett (shootOverWalkable())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Lo Spec Fal (shootSpecWall())");
    caseNumber++;
    System.out.println("Lépés tesztesetek:");
    System.out.println("\t" + caseNumber + " " + "Ajtora lep (walkDoor())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Lep (walkFloorOrWall())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Szakadekba lep (walkGap())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Merlegre lep (walkScale())");
    caseNumber++;
    System.out.println("\t" + caseNumber + " " + "Feregjaratba lep (walkSpecWallStarGate())");
    caseNumber++;
    System.out.println(caseNumber + " " + "Kilepes a programbol");

    caseNumber = in.nextInt();
    System.out.println(caseNumber);

    switch (caseNumber) {
      case 1:
        changeBulletColor();
        break;
      case 2:
        openHelp();
        break;
      case 3:
        pickupBox();
        break;
      case 4:
        pickupForbidden();
        break;
      case 5:
        pickupZpm();
        break;
      case 6:
        placeBox();
        break;
      case 7:
        placeBoxForbidden();
        break;
      case 8:
        shootDoorOrWall();
        break;
      case 9:
        shootOverWalkable();
        break;
      case 10:
        shootSpecWall();
        break;
      case 11:
        walkDoor();
        break;
      case 12:
        walkFloorOrWall();
        break;
      case 13:
        walkGap();
        break;
      case 14:
        walkScale();
        break;
      case 15:
        walkSpecWallStarGate();
        break;
      case 16:
        in.close();
        System.exit(0);
        break;
      default:
        System.out.println("Nem menő");
    }
    return;
  }

  /**
   * Walk over floor or wall.
   */
  public static void walkFloorOrWall() {
    System.out.println("isWalkable? T/F");
    in.nextLine();
    String answer = in.nextLine().toLowerCase();

    boolean isWalkable = answer.equals("t");
    Floor target = new Floor(isWalkable, null);
    Floor position = new Floor(true, null);

    Character oniell = new Character(position, Color.YELLOW, Direction.WEST);
    position.setNeighbour(Direction.EAST, target);
    oniell.move(Direction.EAST);
  }

  /**
   * Walk over a gap.
   */
  public static void walkGap() {
    Floor position = new Floor(true, null);
    Character oneill = new Character(position, Color.BLUE, Direction.EAST);
    Gap target = new Gap();
    position.setNeighbour(Direction.EAST, target);
    oneill.move(Direction.EAST);
  }

  /**
   * Walk through a stargate if there's one.
   * 
   * TODO prompt van stargate rajta? prompt van a stargate-nek párja?
   */
  public static void walkSpecWallStarGate() {
    Floor position = new Floor(true, null);
    Character oneill = new Character(position, Color.BLUE, Direction.EAST);
    SpecWall target = new SpecWall();
    position.setNeighbour(Direction.EAST, target);

    System.out.println("Van StarGate? T/F");
    in.nextLine();
    String answer = in.nextLine().toLowerCase();
    boolean isThereStarGate = answer.equals("t");

    if (isThereStarGate) {
      StarGate gateBlue = new StarGate(Color.BLUE);
      target.setStarGate(gateBlue);

      System.out.println("Van a StarGate-nek parja? T/F");
      answer = in.nextLine().toLowerCase();
      boolean isTherePair = answer.equals("t");

      if (isTherePair) {
        StarGate gateYellow = new StarGate(Color.YELLOW);
        SpecWall pair = new SpecWall();
        pair.setStarGate(gateYellow);
        Wormhole.setSpecWall(target, Color.BLUE);
        Wormhole.setSpecWall(pair, Color.YELLOW);
      }
    }

    oneill.move(Direction.EAST);

  }

  /**
   * Walk through a door.
   */
  public static void walkDoor() {
    System.out.println("Nyitva van az ajtó? T/F");
    in.nextLine();
    String answer = in.nextLine().toLowerCase();
    boolean doorOpen = answer.equals("t");

    Door target = new Door();
    target.setWalkable(doorOpen);
    Floor position = new Floor(true, null);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    position.setNeighbour(Direction.EAST, target);
    oneill.move(Direction.EAST);
  }

  /**
   * Stand on a scale.
   */
  public static void walkScale() {
    Door door = new Door();
    Scale scale = new Scale(door);
    Floor position = new Floor(true, null);
    Character character = new Character(position, Color.YELLOW, Direction.WEST);
    position.setNeighbour(Direction.EAST, scale);
    character.move(Direction.EAST);

  }

  /**
   * Pick up a Zpm.
   */
  public static void pickupZpm() {
    Zpm zpm = new Zpm();
    Floor position = new Floor(true, zpm);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);

    if (position.hasItem() == ItemState.GOTITEM) {
      oneill.take();
    }
  }

  /**
   * Pick up a box.
   */
  public static void pickupBox() {
    Box box = new Box();
    Floor position = new Floor(true, box);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);

    if (position.hasItem() == ItemState.GOTITEM) {
      oneill.take();
    }
  }

  /**
   * Trying to pick a up a box, but he can't.
   */
  public static void pickupForbidden() {
    Door position = new Door();
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);

    if (position.hasItem() == ItemState.GOTITEM) {
      oneill.take();
    }
  }

  /**
   * Place a box on a cell.
   */
  public static void placeBox() {
    Box box = new Box();
    Floor position = new Floor(true, box);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    oneill.setBox(box);
    if (position.hasItem() == ItemState.NOITEM) {
      oneill.drop();
    }
  }

  /**
   * Trying to place a box on a forbidden place.
   */
  public static void placeBoxForbidden() {
    Box box = new Box();
    Floor position = new Floor(true, box);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    ItemState result = position.hasItem();
    if (result != ItemState.FORBIDDENAREA && result != ItemState.GOTITEM && box != null) {
      oneill.drop();
    }
  }

  /**
   * Change bullet color.
   */
  public static void changeBulletColor() {
    Floor position = new Floor(true, null);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    oneill.changeColor();
  }

  /**
   * Shoot over walkable objects.
   */
  public static void shootOverWalkable() {
    Floor position = new Floor(true, null);
    Floor floor2 = new Floor(true, null);
    Floor floor3 = new Floor(true, null);
    Floor floor4 = new Floor(false, null);
    position.setNeighbour(Direction.WEST, floor2);
    floor2.setNeighbour(Direction.EAST, position);
    floor2.setNeighbour(Direction.WEST, floor3);
    floor3.setNeighbour(Direction.EAST, floor2);
    floor3.setNeighbour(Direction.WEST, floor4);
    floor4.setNeighbour(Direction.EAST, floor3);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    oneill.shoot();
  }

  /**
   * Shoot on a special wall transforms it into a stargate.
   */
  public static void shootSpecWall() {
    Floor position = new Floor(true, null);
    SpecWall target = new SpecWall();
    position.setNeighbour(Direction.WEST, target);
    target.setNeighbour(Direction.EAST, position);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    oneill.shoot();
  }

  /**
   * Shoot door or wall.
   */
  public static void shootDoorOrWall() {
    Floor position = new Floor(true, null);
    Door target = new Door();
    target.setWalkable(false);
    position.setNeighbour(Direction.WEST, target);
    target.setNeighbour(Direction.EAST, position);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
    oneill.shoot();
  }

  /**
   * Opens the help menu.
   */
  public static void openHelp() {
    Menu.showHelp();
  }

}
