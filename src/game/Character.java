package game;


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
		System.out.println("Chraracter konstruktor");
	}
	
	//golyó kilövése
	public void shoot(){
		System.out.println("Shoot");
	}
	
	//kilövendõ golyó színének megváltoztatása
	public void changeColor(){
		System.out.println("Changecolor");
	}
	
	//karakter halála
	public void die(){
		System.out.println("Meghaltam");
	}
	
	//karakter mozgatása
	public void move(Direction dir){
		System.out.println("Move");
	}
	
	//karakter helyzetének beállítása
	public void setPosition(LevelObject Position){
		System.out.println("SetPosition");
	}
	
	//karakternél levõ ZPM modulok számának növelése
	public void incrementZPMCount(){
		System.out.println("IncrementZPMCount");
	}
	
	//karakterhez kerül egy doboz
	public void setBox(Box b){
		System.out.println("SetBox");
	}
	
	//tárgy lerakása
	public void drop(){
		System.out.println("Drop");
	}
	
	//tárgy felvétele
	public void take(){
		System.out.println("Take");
	}
}

