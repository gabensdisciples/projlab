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
				"1. Lepes\n "
				+ "\t1.1 Floorra/falra\n"
				+ "\t\t1.1.1. isWalkable()? T/F\n"
				+ "\t1.2 Szakadekba\n"
				+ "\t1.3 SpecWallra/StarGatere lep\n"
				+ "\t\t1.3.1 Van StarGate? T/F\n"
				+ "\t\t\t1.3.1.1 Van StarGate-nek parja? T/F\n"
				+ "\t1.4 Ajtora\n"
				+ "\t\t1.4.1 Nyitva van? T/F\n"
				+ "\t1.5 Merlegre lep\n"
				+ "Adja meg a kivant teszt kodjat: ");
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
	
	public static void testSwitch(){
		System.out.println("Kerlek valaszd ki, melyik teszteset erdekel!");
		int i = 0;
		i++;
		System.out.println(i +"changeBulletColor()");
		i++;
		System.out.println(i +"openHelp()");
		i++;
		System.out.println(i +"pickupBox()");
		i++;
		System.out.println(i +"pickupForbidden()");
		i++;
		System.out.println(i +"pickupZPM()");
		i++;
		System.out.println(i +"placeBox()");
		i++;
		System.out.println(i +"placeBoxForbidden()");
		i++;
		System.out.println(i +"shootDoorOrWall()");
		i++;
		System.out.println(i +"shootOverWalkable()");
		i++;
		System.out.println(i +"shootSpecWall()");
		i++;
		System.out.println(i +"walkDoor()");
		i++;
		System.out.println(i +"walkFloorOrWall()");
		i++;
		System.out.println(i +"walkGap()");
		i++;
		System.out.println(i +"walkScale()");
		i++;
		System.out.println(i +"walkSpecWallStarGate()");
		
		Scanner in = new Scanner(System.in);
		i= in.nextInt();
		
		switch (i){
		case 1:
			changeBulletColor();
		case 2:
			openHelp();
		case 3:
			pickupBox();
		case 4:
			pickupForbidden();
		}
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
		Floor position = new Floor(true,null);
		Character oneill = new Character(position, Color.BLUE, Direction.EAST);
		SpecWall target = new SpecWall();
		Wormhole wormhole = new Wormhole();
		target.interactCharacter(oneill);
		//Szekvencia diagram alapján
		//wormhole.getSpecWall(Color.getOtherColor(target.color()));
		SpecWall pair = new SpecWall();
		if (pair!= null){
			oneill.setPosition(pair);
		}
		else{
			oneill.setPosition(target);
	}
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
		Floor position = new Floor(true, box);
		Character oneill = new Character(position, Color.YELLOW, Direction.WEST);
		
		oneill.take();
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
		floor2.setNeighbour(Direction.EAST, floor1);
		floor2.setNeighbour(Direction.WEST, floor3);
		floor3.setNeighbour(Direction.EAST, floor2);
		floor3.setNeighbour(Direction.WEST, floor4);
		floor4.setNeighbour(Direction.EAST, floor3);
		Character oneill = new Character(floor1, Color.YELLOW, Direction.WEST);
		oneill.shoot();
	}

	public static void shootSpecWall() {
		Floor floor1 = new Floor(true, null);
		SpecWall target = new SpecWall();
		floor1.setNeighbour(Direction.WEST, target);
		target.setNeighbour(Direction.EAST, floor1);
		Character oneill = new Character(floor1, Color.YELLOW, Direction.WEST);
		oneill.shoot();
	}

	public static void shootDoorOrWall() {
		Floor floor1 = new Floor(true, null);
		SpecWall target = new SpecWall();
		floor1.setNeighbour(Direction.WEST, target);
		target.setNeighbour(Direction.EAST, floor1);
		Character oneill = new Character(floor1, Color.YELLOW, Direction.WEST);
		oneill.shoot();
	}

	public static void openHelp() {
		Menu.showHelp();
	}
	

}
