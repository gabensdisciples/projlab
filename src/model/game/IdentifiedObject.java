package model.game;

public abstract class IdentifiedObject {
  public int ID;
  public static int maxID = 0;
  
  public IdentifiedObject() {
    maxID++;
    ID = maxID;
  }
}
