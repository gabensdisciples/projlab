package game;


public class Character {
	
	private Direction direction;
	private Color bulletColor;
	private LevelObject position;
	private int zpmCount;
	private Box box;
	
	public Character(LevelObject Position, Color color, Direction direction){
		System.out.println("Karakter konstruktor vagyok");
	}
	
	public void shoot(){
		System.out.println("Shoot f�ggv�ny vagyok");
	}
	
	public void changeColor(){
		System.out.println("Changecolor vagyok");
	}
	
	public void die(){
		System.out.println("Meghaltam");
	}
	
	public void move(Direction dir){
		System.out.println("Move vagyok");
	}
	
	public void setPosition(LevelObject Position){
		System.out.println("SetPosition vagyok");
	}
	
	public void incrementZPMCount(){
		System.out.println("IncrementZPMCount");
	}
	
	public void setBox(Box b){
		System.out.println("SetBox");
	}
	
	public void drop(){
		System.out.println("Drop");
	}
	
	public void take(){
		System.out.println("Take");
	}
}

