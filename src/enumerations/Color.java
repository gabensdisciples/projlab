package enumerations;

import logger.Logger;

public enum Color {

	BLUE, YELLOW;

	// TODO: Valtozott a szignatura, dokumentalni kell!
	public Color getOtherColor() {
		Logger.log("getOtherColor");
		Logger.Logout();
		if (this == Color.BLUE)
			return Color.YELLOW;

		else
			return Color.BLUE;
	}
}
