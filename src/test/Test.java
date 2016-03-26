package test;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.Scale;
import cells.SpecWall;
import enumerations.Color;
import enumerations.Direction;
import enumerations.ItemState;
import game.Bullet;
import game.StarGate;
import game.Wormhole;
import items.Box;
import items.Item;
import items.ZPM;
import main.LevelBuilder;
import main.Menu;
import main.MenuPoints;
import game.Character;
import cells.LevelObject;

public class Test {

	public static void main(String[] args) {
		System.out.println(
				"1. Lépés\n "
				+ "\t1.1 Floorra/falra\n"
				+ "\t\t1.1.1. isWalkable()? T/F\n"
				+ "\t1.2 Szakadékba\n"
				+ "\t1.3 SpecWallra/StarGatere lép\n"
				+ "\t\t1.3.1 Van StarGate? T/F\n"
				+ "\t\t\t1.3.1.1 Van StarGate-nek párja? T/F\n"
				+ "\t1.4 Ajtóra\n"
				+ "\t\t1.4.1 Nyitva van? T/F\n"
				+ "\t1.5 Mérlegre lép\n"
				+ "Adja meg a kívánt teszt kódját: ");
		/**
		 * T�lt�ny sz�n v�lt�s
		 */
		changeBulletColor();
		/**
		 * Doboz lerak�s
		 */
		placeBox();
		/**
		 * Doboz lerak�s rossz helyre
		 */
		placeBox();
		/**
		 * M�rlegre l�p
		 */
		walkScale();
	}

	public static void walkFloorOrWall() {
		Floor floorToStand = new Floor(true, null);
		Floor floorToMove = new Floor(true, null);
		Character oniell = new Character(floorToStand, Color.YELLOW, Direction.WEST);
		floorToStand.setNeighbour(Direction.EAST, floorToMove);
		oniell.move(Direction.EAST);
	}

	public static void walkGap() {
		Floor position = new Floor(true, null);
		Character oneill = new Character(position, Color.BLUE, Direction.EAST);
		Gap target = new Gap();
		position.setNeighbour(Direction.EAST, target);
		oneill.move(Direction.EAST);
	}

	public static void walkSpecWallStarGate() {
		//asdsadsd
		//eclipse asdasdasd
	}

	public static void walkDoor() {
		Door opendoor = new Door();
		Floor place = new Floor(true, null);
		Character oneill = new Character(place, Color.YELLOW, Direction.WEST);
		place.setNeighbour(Direction.EAST, opendoor);
		oneill.move(Direction.EAST);
	}

	public static void walkScale() {
		Door door = new Door();
		Scale scale = new Scale(door);
		Floor position = new Floor(true, null);
		Character character = new Character(position, Color.YELLOW, Direction.WEST);
		position.setNeighbour(Direction.EAST, scale);
		character.move(Direction.EAST);
		
	}

	public static void pickupZPM() {
		ZPM zpm = new ZPM();
		Floor floor1 = new Floor(true, zpm);
		Character character1 = new Character(floor1, Color.YELLOW, Direction.WEST);
		
		character1.take();
	}

	public static void pickupBox() {
		Box box = new Box();
		Floor floor2 = new Floor(true, box);
		Character character2 = new Character(floor2, Color.YELLOW, Direction.WEST);
		
		character2.take();
	}

	public static void pickupForbidden() {
		Floor floor3 = new Floor(true, null);
		Character character3 = new Character(floor3, Color.YELLOW, Direction.WEST);
		
		character3.take();
	}

	public static void placeBox() {
		Box box = new Box();
		Floor floor = new Floor(true, box);
		Character character = new Character(floor, Color.YELLOW, Direction.WEST);
		character.setBox(box);
		character.drop();
	}

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

	public static void changeBulletColor() {
		Floor floor = new Floor(true, null);
		Character character = new Character(floor, Color.YELLOW, Direction.WEST);
		character.changeColor();
	}
	
	public static void shootOverWalkable() {
		Floor floor1 = new Floor(true, null);
		Floor floor2 = new Floor(true, null);
		Floor floor3 = new Floor(true, null);
		Floor floor4 = new Floor(false, null);
		floor1.setNeighbour(Direction.WEST, floor2);
		floor2.setNeighbour(Direction.WEST, floor1);
		floor2.setNeighbour(Direction.WEST, floor3);
		floor3.setNeighbour(Direction.WEST, floor2);
		floor3.setNeighbour(Direction.WEST, floor4);
		floor4.setNeighbour(Direction.WEST, floor3);
		Character character = new Character(floor1, Color.YELLOW, Direction.WEST);
		character.shoot();
	}

	public static void shootSpecWall() {

	}

	public static void shootDoorOrWall() {

	}

	public static void openHelp() {
		Menu.showHelp();
	}
}
