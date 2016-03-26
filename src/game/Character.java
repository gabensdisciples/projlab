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
		Logger.Log("Character konstruktor");
		this.position = position;
		bulletColor = color;
		this.direction = direction;
		Logger.Logout();
	}

	// goly� kil�v�se
	public void shoot() {
		Logger.Log("Character Shoot");
		Bullet b = new Bullet(position, direction, bulletColor);
		b.fly();
		Logger.Logout();
	}

	// kil�vend� goly� sz�n�nek megv�ltoztat�sa
	public void changeColor() {
		Logger.Log("Character Changecolor");
		bulletColor = bulletColor.getOtherColor();
		Logger.Logout();
	}

	// karakter hal�la
	public void die() {
		Logger.Log("Character die");
		Logger.Logout();
	}

	// karakter mozgat�sa
	public void move(Direction dir) {
		Logger.Log("Character move");
		position.getNeighbour(dir, true).interactCharacter(this);
		Logger.Logout();
	}

	// karakter helyzet�nek be�ll�t�sa
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

	// karaktern�l lev� ZPM modulok sz�m�nak n�vel�se
	public void incrementZPMCount() {
		Logger.Log("Character IncrementZPMCount");
		zpmCount++;
		Logger.Logout();
	}

	// karakterhez ker�l egy doboz
	public void setBox(Box b) {
		Logger.Log("Character setBox");
		box = b;
		Logger.Logout();
	}

	// t�rgy lerak�sa
	public void drop() {
		Logger.Log("Character drop");

		position.placeItem((Item) box);
		box = null;
		Logger.Logout();
	}

	// t�rgy felv�tele
	public void take() {
		Logger.Log("Character take");
		position.getItem(this);
		Logger.Logout();
	}
}
