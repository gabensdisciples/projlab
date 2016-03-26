package test;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.Scale;
import cells.SpecWall;
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

	public void walkFloorOrWall() {

	}

	public void walkGap() {

	}

	public void walkSpecWallStarGate() {

	}

	public void walkDoor() {

	}

	public void walkScale() {

	}

	public void pickupZPM() {

	}

	public void pickupBox() {

	}

	public void pickupForbidden() {

	}

	public void placeBox() {

	}

	public void placeBoxForbidden() {

	}

	public void changeBulletColor() {
		// Floor levelObject = new Floor(true);
		// Bullet b = new Bullet(levelObject, Direction.EAST, Color.BLUE);

	}

	public void shootOverWalkable() {

	}

	public void shootSpecWall() {

	}

	public void shootDoorOrWall() {

	}

	public void openHelp() {

	}
}
