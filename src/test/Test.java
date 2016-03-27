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

	public static void main(String[] args) {
		in = new Scanner(System.in);
		while(true) {		
			testSwitch();
		}
	}
	
  public static void testSwitch() {
    System.out.println("\nKerlek valaszd ki, melyik teszteset erdekel!\n");
    int i = 0;
    i++;
    System.out.println(i + " " + "changeBulletColor()");
    i++;
    System.out.println(i + " " + "openHelp()");
    i++;
    System.out.println(i + " " + "pickupBox()");
    i++;
    System.out.println(i + " " + "pickupForbidden()");
    i++;
    System.out.println(i + " " + "pickupZPM()");
    i++;
    System.out.println(i + " " + "placeBox()");
    i++;
    System.out.println(i + " " + "placeBoxForbidden()");
    i++;
    System.out.println(i + " " + "shootDoorOrWall()");
    i++;
    System.out.println(i + " " + "shootOverWalkable()");
    i++;
    System.out.println(i + " " + "shootSpecWall()");
    i++;
    System.out.println(i + " " + "walkDoor()");
    i++;
    System.out.println(i + " " + "walkFloorOrWall()");
    i++;
    System.out.println(i + " " + "walkGap()");
    i++;
    System.out.println(i + " " + "walkScale()");
    i++;
    System.out.println(i + " " + "walkSpecWallStarGate()");
    i++;
    System.out.println(i + " " + "Kilépés a programból");


    i = in.nextInt();
    System.out.println(i);

    switch (i) {
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
        pickupZPM();
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
   * Walk through a stargate.
   */
  public static void walkSpecWallStarGate() {
    Floor position = new Floor(true, null);
    Character oneill = new Character(position, Color.BLUE, Direction.EAST);
    SpecWall target = new SpecWall();
    target.interactCharacter(oneill);
    // Szekvencia diagram alapján
    // wormhole.getSpecWall(Color.getOtherColor(target.color()));
    SpecWall pair = new SpecWall();
    if (pair != null) {
      oneill.setPosition(pair);
    } else {
      oneill.setPosition(target);
    }
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
  public static void pickupZPM() {
    Zpm zpm = new Zpm();
    Floor floor1 = new Floor(true, zpm);
    Character character1 = new Character(floor1, Color.YELLOW, Direction.WEST);

    character1.take();
  }

  /**
   * Pick up a box.
   */
  public static void pickupBox() {
    Box box = new Box();
    Floor position = new Floor(true, box);
    Character oneill = new Character(position, Color.YELLOW, Direction.WEST);

    oneill.take();
  }

  /**
   * Trying to pick a up a box, but he can't.
   */
  public static void pickupForbidden() {
    Floor floor3 = new Floor(true, null);
    Character character3 = new Character(floor3, Color.YELLOW, Direction.WEST);

    character3.take();
  }

  /**
   * Place a box on a cell.
   */
  public static void placeBox() {
    Box box = new Box();
    Floor floor = new Floor(true, box);
    Character character = new Character(floor, Color.YELLOW, Direction.WEST);
    character.setBox(box);
    character.drop();
  }

  /**
   * Trying to place a box on a forbidden place.
   */
  public static void placeBoxForbidden() {
    Box box = new Box();
    Floor floor = new Floor(true, box);
    Character character = new Character(floor, Color.YELLOW, Direction.WEST);
    if (floor.hasItem() == ItemState.FORBIDDENAREA) {
      System.out.println("Ide nem tehetsz dobozt kocsog");
    } else {
      character.setBox(box);
      character.drop();
    }
  }

  /**
   * Change bullet color.
   */
  public static void changeBulletColor() {
    Floor floor = new Floor(true, null);
    Character character = new Character(floor, Color.YELLOW, Direction.WEST);
    character.changeColor();
  }

  /**
   * Shoot over walkable objects.
   */
  public static void shootOverWalkable() {
    Floor floor1 = new Floor(true, null);
    Floor floor2 = new Floor(true, null);
    Floor floor3 = new Floor(true, null);
    Floor floor4 = new Floor(false, null);
    floor1.setNeighbour(Direction.WEST, floor2);
    floor2.setNeighbour(Direction.EAST, floor1);
    floor2.setNeighbour(Direction.WEST, floor3);
    floor3.setNeighbour(Direction.EAST, floor2);
    floor3.setNeighbour(Direction.WEST, floor4);
    floor4.setNeighbour(Direction.EAST, floor3);
    Character oneill = new Character(floor1, Color.YELLOW, Direction.WEST);
    oneill.shoot();
  }

  /**
   * Shoot on a special wall transforms it into a stargate.
   */
  public static void shootSpecWall() {
    Floor floor1 = new Floor(true, null);
    SpecWall target = new SpecWall();
    floor1.setNeighbour(Direction.WEST, target);
    target.setNeighbour(Direction.EAST, floor1);
    Character oneill = new Character(floor1, Color.YELLOW, Direction.WEST);
    oneill.shoot();
  }

  /**
   * Shoot door or wall.
   */
  public static void shootDoorOrWall() {
    Floor floor1 = new Floor(true, null);
    SpecWall target = new SpecWall();
    floor1.setNeighbour(Direction.WEST, target);
    target.setNeighbour(Direction.EAST, floor1);
    Character oneill = new Character(floor1, Color.YELLOW, Direction.WEST);
    oneill.shoot();
  }

  /**
   * Opens the help menu.
   */
  public static void openHelp() {
    Menu.showHelp();
  }

}
