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
