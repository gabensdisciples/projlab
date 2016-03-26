package game;

import cells.LevelObject;
import enumerations.Color;
import enumerations.Direction;
import items.Box;
import items.Item;

public class Character {
	
	//merre néz a karakter
	private Direction direction;
	//a kilövendõ lövedék színe
	private Color bulletColor;
	//a karakter helyének tárolása a pályán
	private LevelObject position;
	//az összegyûjtött ZPM modulok száma
	private int zpmCount;
	//referencia a karakternél lévõ dobozra
	private Box box;
	
	
	//konstruktor - játszható karakter létrehozása
	public Character(LevelObject Position, Color color, Direction direction){
		System.out.println("Character konstruktor");
		
		this.position = position;
		bulletColor = color;
		this.direction = direction;
	}
	
	//golyó kilövése
	public void shoot(){
		System.out.println("Character Shoot");
		Bullet b = new Bullet(position, direction, bulletColor);
		b.fly();
	}
	
	//kilövendõ golyó színének megváltoztatása
	public void changeColor(){
		System.out.println("Character Changecolor");
		bulletColor = bulletColor.getOtherColor();
	}
	
	//karakter halála
	public void die() {
		System.out.println("Character die");
		
	}
	
	//karakter mozgatása
	public void move(Direction dir){
		System.out.println("Character move");
		position.getNeighbour(dir, true).interactCharacter(this);
	}
	
	//karakter helyzetének beállítása
	public void setPosition(LevelObject position){
		System.out.println("Character setPosition");
		this.position = position;
	}
	
	public LevelObject getPosition() {
		return position;
	}
	
	//karakternél levõ ZPM modulok számának növelése
	public void incrementZPMCount(){
		System.out.println("Character IncrementZPMCount");
		zpmCount++;
	}
	
	//karakterhez kerül egy doboz
	public void setBox(Box b){
		System.out.println("Character setBox");
		box = b;
	}
	
	//tárgy lerakása
	public void drop(){
		System.out.println("Character drop");
		
		position.placeItem((Item) box);
		box = null;
	}
	
	//tárgy felvétele
	public void take(){
		System.out.println("Character take");
		
		position.getItem(this);
	}
}

