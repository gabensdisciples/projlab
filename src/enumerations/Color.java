package enumerations;

public enum Color {

	BLUE, YELLOW;

	// TODO: Valtozott a szignatura, dokumentalni kell!
	public Color getOtherColor() {
		System.out.println("getOtherColor");
		if (this == Color.BLUE)
			return Color.YELLOW;

		else
			return Color.BLUE;
	}
}
