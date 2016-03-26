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
		/**
		 * Töltény szín váltás
		 */
		changeBulletColor();
		/**
		 * Doboz lerakás
		 */
		placeBox();
		/**
		 * Doboz lerakás rossz helyre
		 */
		placeBox();
		/**
		 * Mérlegre lép
		 */
		walkScale();
	}

	public static void walkFloorOrWall() {

	}

	public static void walkGap() {

	}

	public static void walkSpecWallStarGate() {

	}

	public static void walkDoor() {

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
		LevelObject position = new LevelObject(true);
		Character character = new Character(position, Color.YELLOW, Direction.WEST);
		ZPM zmp = new ZPM();
		
		character.take();
	}

	public static void pickupBox() {

	}

	public static void pickupForbidden() {

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

	}

	public static void shootSpecWall() {

	}

	public static void shootDoorOrWall() {

	}

	public static void openHelp() {

	}
}
