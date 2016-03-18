package game;

public final class Wormhole{
	private SpecWall blueSpecWall;
	private SpecWall yellowSpecWall;
	
	public static void setSpecWall(SpecWall specWall, Color color){
		System.out.println("setSpecWall");
	}
	
	public static SpecWall getSpecWall(Color color){
		System.out.println("getSpecWall");
		return null; //Color-t nem tudom letesztelni.
	}

}
