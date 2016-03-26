package game;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import items.Box;
import items.Item;

public class Character {
	
	//merre n�z a karakter
	private Direction direction;
	//a kil�vend� l�ved�k sz�ne
	private Color bulletColor;
	//a karakter hely�nek t�rol�sa a p�ly�n
	private LevelObject position;
	//az �sszegy�jt�tt ZPM modulok sz�ma
	private int zpmCount;
	//referencia a karaktern�l l�v� dobozra
	private Box box;
	
	
	//konstruktor - j�tszhat� karakter l�trehoz�sa
	public Character(LevelObject Position, Color color, Direction direction){
		System.out.println("Character konstruktor");
		
		this.position = position;
		bulletColor = color;
		this.direction = direction;
	}
	
	//goly� kil�v�se
	public void shoot(){
		System.out.println("Character Shoot");
		Bullet b = new Bullet(position, direction, bulletColor);
		b.fly();
	}
	
	//kil�vend� goly� sz�n�nek megv�ltoztat�sa
	public void changeColor(){
		System.out.println("Character Changecolor");
		bulletColor = bulletColor.getOtherColor();
	}
	
	//karakter hal�la
	public void die() {
		System.out.println("Character die");
		
	}
	
	//karakter mozgat�sa
	public void move(Direction dir){
		System.out.println("Character move");
		position.getNeighbour(dir, true).interactCharacter(this);
	}
	
	//karakter helyzet�nek be�ll�t�sa
	public void setPosition(LevelObject position){
		System.out.println("Character setPosition");
		this.position = position;
	}
	
	public LevelObject getPosition() {
		return position;
	}
	
	//karaktern�l lev� ZPM modulok sz�m�nak n�vel�se
	public void incrementZPMCount(){
		System.out.println("Character IncrementZPMCount");
		zpmCount++;
	}
	
	//karakterhez ker�l egy doboz
	public void setBox(Box b){
		System.out.println("Character setBox");
		box = b;
	}
	
	//t�rgy lerak�sa
	public void drop(){
		System.out.println("Character drop");
		
		position.placeItem((Item) box);
		box = null;
	}
	
	//t�rgy felv�tele
	public void take(){
		System.out.println("Character take");
		
		position.getItem(this);
	}
}

