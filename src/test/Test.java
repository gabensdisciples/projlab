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

public class Test {
	public static void main(String[] args) {

		/**
		 * cells
		 */
		Door door = null;
		Floor floor = null;
		Gap gap = null;
		Scale scale = null;
		SpecWall specWall = null;
		/**
		 * game
		 */
		Bullet bullet = null;
		Character character = null;
		StarGate starGate = null;
		Wormhole wormhole = null;
		/**
		 * items
		 */
		Box box = null;
		Item item = null;
		ZPM zpm = null;
		/**
		 * main
		 */
		LevelBuilder levelBuilder = null;
		Menu menu = null;
		MenuPoints menuPoints = null;
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

	}

	public static void pickupBox() {

	}

	public static void pickupForbidden() {

	}

	public static void placeBox() {

	}

	public static void placeBoxForbidden() {

	}

	public static void changeBulletColor() {
//		box = new Box();
//		floor = new Floor(true);
//		bullet = new Bullet(levelObject, Direction.EAST, Color.BLUE);

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
