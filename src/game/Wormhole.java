package game;

import cells.SpecWall;
import enumerations.Color;

public final class Wormhole{
	private static SpecWall blueSpecWall = null;
	private static SpecWall yellowSpecWall = null;
	
	public static void setSpecWall(SpecWall specWall, Color color){
		System.out.println("setSpecWall");
		if(color == Color.blue)
			blueSpecWall = specWall;
		
		else yellowSpecWall = specWall;
	}
	
	public static SpecWall getSpecWall(Color color){
		System.out.println("getSpecWall");
		if(color == Color.blue)
			return blueSpecWall;
		
		else return yellowSpecWall;
	}
}
