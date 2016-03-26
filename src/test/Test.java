package test;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.Scale;
import cells.SpecWall;
import enumerations.Color;
import enumerations.Direction;
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
		 * Test 1
		 */
		changeBulletColor();
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
		LevelObject position1 = new LevelObject(true);
		Character character1 = new Character(position1, Color.YELLOW, Direction.WEST);
		ZPM zpm = new ZPM();
		
		character.take();
	}

	public static void pickupBox() {
		LevelObject position2 = new LevelObject(true);
		Character character2 = new Character(position2, Color.YELLOW, Direction.WEST);
		Box box = new Box();
		
		character2.take();
	}

	public static void pickupForbidden() {

	}

	public static void placeBox() {

	}

	public static void placeBoxForbidden() {

	}

	public static void changeBulletColor() {
		Box box = new Box();
		Floor floor = new Floor(true, box);
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
