package game;


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
	
	
	//konsrtuktor - j�tszhat� karakter l�trehoz�sa
	public Character(LevelObject Position, Color color, Direction direction){
		System.out.println("Chraracter konstruktor");
	}
	
	//goly� kil�v�se
	public void shoot(){
		System.out.println("Shoot");
	}
	
	//kil�vend� goly� sz�n�nek megv�ltoztat�sa
	public void changeColor(){
		System.out.println("Changecolor");
	}
	
	//karakter hal�la
	public void die(){
		System.out.println("Meghaltam");
	}
	
	//karakter mozgat�sa
	public void move(Direction dir){
		System.out.println("Move");
	}
	
	//karakter helyzet�nek be�ll�t�sa
	public void setPosition(LevelObject Position){
		System.out.println("SetPosition");
	}
	
	//karaktern�l lev� ZPM modulok sz�m�nak n�vel�se
	public void incrementZPMCount(){
		System.out.println("IncrementZPMCount");
	}
	
	//karakterhez ker�l egy doboz
	public void setBox(Box b){
		System.out.println("SetBox");
	}
	
	//t�rgy lerak�sa
	public void drop(){
		System.out.println("Drop");
	}
	
	//t�rgy felv�tele
	public void take(){
		System.out.println("Take");
	}
}

