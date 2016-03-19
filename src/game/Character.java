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
	
	
	//konsrtuktor - játszható karakter létrehozása
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
	public void die(){
		System.out.println("Character die");
		
	}
	
	//karakter mozgatása
	public void move(Direction dir){
		System.out.println("Character move");
		//nullt ad vissza, meg kellene csinalni
		position.getNeighbour(dir).interactCharacter(this);
	}
	
	//karakter helyzetének beállítása
	public void setPosition(LevelObject position){
		System.out.println("Character setPosition");
		this.position = position;
	}
	
	//karakternél levõ ZPM modulok számának növelése
	public void incrementZPMCount(){
		System.out.println("Character IncrementZPMCount");
	}
	
	//karakterhez kerül egy doboz
	public void setBox(Box b){
		System.out.println("Character setBox");
		box = b;
	}
	
	//tárgy lerakása
	public void drop(){
		System.out.println("Character drop");
		
		//Igy ranezve szerintem a hasItem hivasa itt folosleges, mert...
		//Ha igy van, dokumentalni kell! (szekvenciakon is :( )
		position.placeItem((Item) box);
	}
	
	//tárgy felvétele
	public void take(){
		System.out.println("Character take");
		
		//Ugyanaz, mint a lerakasnal: foloslegesnek tunik a hasItem...
		position.getItem(this);
	}
}

