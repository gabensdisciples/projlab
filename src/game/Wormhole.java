package game;

import cells.SpecWall;
import enumerations.Color;
import logger.Logger;

public final class Wormhole {
	private static SpecWall blueSpecWall = null;
	private static SpecWall yellowSpecWall = null;

	public static void setSpecWall(SpecWall specWall, Color color) {
		System.out.println("setSpecWall");
		if (color == Color.BLUE)
			blueSpecWall = specWall;

		else
			yellowSpecWall = specWall;
	}

	public static SpecWall getSpecWall(Color color) {
		System.out.println("getSpecWall");
		if (color == Color.BLUE)
			return blueSpecWall;

		else
			return yellowSpecWall;
	}
}
