package game;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import items.Box;
import items.Item;
import logger.Logger;

public class Character {

	// merre n�z a karakter
	private Direction direction;
	// a kil�vend� l�ved�k sz�ne
	private Color bulletColor;
	// a karakter hely�nek t�rol�sa a p�ly�n
	private LevelObject position;
	// az �sszegy�jt�tt ZPM modulok sz�ma
	private int zpmCount;
	// referencia a karaktern�l l�v� dobozra
	private Box box;

	// konstruktor - j�tszhat� karakter l�trehoz�sa
	public Character(LevelObject position, Color color, Direction direction) {
		Logger.log("Character konstruktor");
		this.position = position;
		bulletColor = color;
		this.direction = direction;
		Logger.logout();
	}

	// goly� kil�v�se
	public void shoot() {
		Logger.log("Character Shoot");
		Bullet b = new Bullet(position, direction, bulletColor);
		b.fly();
		Logger.logout();
	}

	// kil�vend� goly� sz�n�nek megv�ltoztat�sa
	public void changeColor() {
		Logger.log("Character Changecolor");
		bulletColor = bulletColor.getOtherColor();
		Logger.logout();
	}

	// karakter hal�la
	public void die() {
		Logger.log("Character die");
		Logger.logout();
	}

	// karakter mozgat�sa
	public void move(Direction dir) {
		Logger.log("Character move");
		position.getNeighbour(dir, true).interactCharacter(this);
		Logger.logout();
	}

	// karakter helyzet�nek be�ll�t�sa
	public void setPosition(LevelObject position) {
		Logger.log("Character setPosition");
		this.position = position;
		Logger.logout();
	}

	public LevelObject getPosition() {
		Logger.log("Character getPosition");
		Logger.logout();
		return position;
	}

	// karaktern�l lev� ZPM modulok sz�m�nak n�vel�se
	public void incrementZPMCount() {
		Logger.log("Character IncrementZPMCount");
		zpmCount++;
		Logger.logout();
	}

	// karakterhez ker�l egy doboz
	public void setBox(Box b) {
		Logger.log("Character setBox");
		box = b;
		Logger.logout();
	}

	// t�rgy lerak�sa
	public void drop() {
		Logger.log("Character drop");

		position.placeItem((Item) box);
		box = null;
		Logger.logout();
	}

	// t�rgy felv�tele
	public void take() {
		Logger.log("Character take");
		position.getItem(this);
		Logger.logout();
	}
}
