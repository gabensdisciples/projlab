package enumerations;

public enum Color {
	
	blue,
	yellow;
	
	//Valtozott a szignatura, dokumentalni kell!
	public Color getOtherColor(){
		System.out.println("getOtherColor");
		if(this == Color.blue)
			return Color.yellow;
		
		else return Color.blue;
	}
}
