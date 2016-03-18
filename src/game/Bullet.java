package game;

public class Bullet {
	private Direction direction;
	private Color color;
	private LevelObject position;
	
	public Bullet(LevelObject position, Direction direction, Color color){
		System.out.println("Bullet konstruktor");
		this.position = position;
		this.direction = direction;
		this.color = color;		
	}
	
	public void fly(){
		System.out.println("fly");
	}

	public void setPosition(LevelObject position){
		System.out.println("setPosition");
		this.position = position;
	}
	
	public void die(){
		System.out.println("Bullet die");
	}
	
	public StarGate createStarGate(){
		System.out.println("createStarGate");
		return new StarGate(color);
	}
}
