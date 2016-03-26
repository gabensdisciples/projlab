package game;

import cells.SpecWall;
import enumerations.Color;
import logger.Logger;

public final class Wormhole {
	private static SpecWall blueSpecWall = null;
	private static SpecWall yellowSpecWall = null;

	public static void setSpecWall(SpecWall specWall, Color color) {
		Logger.Log("setSpecWall");
		if (color == Color.BLUE){
			
			blueSpecWall = specWall;
		}
		else{
			
			yellowSpecWall = specWall;
		}
		Logger.Logout();
	}

	public static SpecWall getSpecWall(Color color) {
		Logger.Log("getSpecWall");
		Logger.Logout();
		if (color == Color.BLUE)
			return blueSpecWall;

		else
			return yellowSpecWall;
	}
}
