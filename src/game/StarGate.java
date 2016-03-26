package game;

import enumerations.Color;
import logger.Logger;

public class StarGate {

	private Color color;

	public StarGate(Color color) {
		Logger.Log("StarGate konstruktor");
		this.color = color;
		Logger.Logout();
	}
}
