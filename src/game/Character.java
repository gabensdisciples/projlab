package game;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import items.Box;
import items.Item;
import logger.Logger;

public class Character {

	// merre néz a karakter
	private Direction direction;
	// a kilövendõ lövedék színe
	private Color bulletColor;
	// a karakter helyének tárolása a pályán
	private LevelObject position;
	// az összegyûjtött ZPM modulok száma
	private int zpmCount;
	// referencia a karakternél lévõ dobozra
	private Box box;

	// konstruktor - játszható karakter létrehozása
	public Character(LevelObject position, Color color, Direction direction) {
		Logger.Log("Character konstruktor");
		this.position = position;
		bulletColor = color;
		this.direction = direction;
		Logger.Logout();
	}

	// golyó kilövése
	public void shoot() {
		Logger.Log("Character Shoot");
		Bullet b = new Bullet(position, direction, bulletColor);
		b.fly();
		Logger.Logout();
	}

	// kilövendõ golyó színének megváltoztatása
	public void changeColor() {
		Logger.Log("Character Changecolor");
		bulletColor = bulletColor.getOtherColor();
		Logger.Logout();
	}

	// karakter halála
	public void die() {
		Logger.Log("Character die");
		Logger.Logout();
	}

	// karakter mozgatása
	public void move(Direction dir) {
		Logger.Log("Character move");
		position.getNeighbour(dir, true).interactCharacter(this);
		Logger.Logout();
	}

	// karakter helyzetének beállítása
	public void setPosition(LevelObject position) {
		Logger.Log("Character setPosition");
		this.position = position;
		Logger.Logout();
	}

	public LevelObject getPosition() {
		Logger.Log("Character getPosition");
		Logger.Logout();
		return position;
	}

	// karakternél levõ ZPM modulok számának növelése
	public void incrementZPMCount() {
		Logger.Log("Character IncrementZPMCount");
		zpmCount++;
		Logger.Logout();
	}

	// karakterhez kerül egy doboz
	public void setBox(Box b) {
		Logger.Log("Character setBox");
		box = b;
		Logger.Logout();
	}

	// tárgy lerakása
	public void drop() {
		Logger.Log("Character drop");

		position.placeItem((Item) box);
		box = null;
		Logger.Logout();
	}

	// tárgy felvétele
	public void take() {
		Logger.Log("Character take");
		position.getItem(this);
		Logger.Logout();
	}
}
