package model.levelbuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import model.cells.Door;
import model.cells.Floor;
import model.cells.Gap;
import model.cells.LevelObject;
import model.cells.Scale;
import model.cells.SpecWall;
import model.enumerations.Color;
import model.enumerations.Direction;
import model.game.Player;
import model.game.Replicator;
import model.game.ReplicatorContainer;
import model.game.Wormhole;
import model.items.Box;
import model.items.Zpm;

/**
 * Singleton utility class, builds a level from text file.
 * 
 * @author Gaben's Disciples
 * 
 */
public class LevelBuilder {

  private static int WIDTH;

  private static int HEIGHT;

  private static LevelBuilder instance = null;

  private Player oneill;

  private Player jaffa;

  private Replicator replicator;

  public LevelObject[][] objectMatrix;

  public String[][] stringMatrix;
  
  public  int[][][] idMatrix;
  
  public String[][][] imageNameMatrix;

  /**
   * Creates a single instance of the class.
   * 
   * @return the instance
   */
  public static LevelBuilder getInstance() {
    if (instance == null) {
      instance = new LevelBuilder();
    }

    return instance;
  }

  /**
   * Constructs the level by calling the necessary method in order.
   */
  public void init(String filename) {
    Wormhole.Clear();
    Zpm.zpmsMax = 0;
    Zpm.zpmsRemaining = 0;
    measureLevelSize(filename);
    createStringMatrix(filename);
    createObjectMatrix();
    initNeighborDiscovery();
    setScaleDoor();
  }

  /**
   * Reads the level file and measures its height and width. The class gets its
   * WIDHT and HEIGHT paramters by this method. These member variables are used
   * to iterate over the string and object matrixes.
   *
   * Details:
   *
   * First it reads only one row in a string array and make WIDTH equal to the
   * length of the array. After it reads every row from the second one to the
   * end and counts them. Finally it adds 1 to the counted rows - because at the
   * beginning we have already read one line - and set the HEIGHT member
   * variable to the value of the counted rows.
   */
  public void measureLevelSize(String filename) {
    try {
      FileReader stream = new FileReader(filename);
      BufferedReader br = new BufferedReader(stream);
      try {
        int row = 0;
        String startLine = br.readLine();
        String[] startLineSplit = startLine.split("\\s+");
        WIDTH = startLineSplit.length;
        while (br.readLine() != null) {
          row++;
        }
        row = row + 1;
        HEIGHT = row;
        stringMatrix = new String[HEIGHT][WIDTH];
        objectMatrix = new LevelObject[HEIGHT][WIDTH];
        idMatrix = new int[HEIGHT][WIDTH][2];
        imageNameMatrix = new String[HEIGHT][WIDTH][2];
      } finally {
        br.close();
      }
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ArrayIndexOutOfBoundsException("Set the correct size of the text file matrix!");
    } catch (IOException exception) {
      System.out.println("Nem talaltam a megadott fajlt.");
    }
  }

  /**
   * Creates a square matrix from the text file.
   * 
   * Details:
   * 
   * Reads the file by using the classloader's method. It makes possible to
   * place the file anywhere in the project folder. The method reads one line to
   * a string array splitted by any spaces and put the line at the appropriate
   * row in the string matrix by iterations.
   */
  public void createStringMatrix(String filename) {
    try {
      FileReader stream = new FileReader(filename);
      BufferedReader br = new BufferedReader(stream);
      try {
        String line;
        int row = 0;
        while ((line = br.readLine()) != null) {
          String[] values = line.split("\\s+");
          for (int column = 0; column < values.length; column++) {
            stringMatrix[row][column] = values[column];
          }
          row++;
        }
      } finally {
        br.close();
      }
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ArrayIndexOutOfBoundsException("Set the correct size of the text file matrix!");
    } catch (IOException exception) {
      
    }
  }

  /**
   * Creates a matrix with the appropriate objects from the string one.
   *
   * Details: Loops through the string matrix and creates objects in the object
   * matrix at the same index depending on the string value.
   * 
   * For example if at the 2, 4 indexes in the string matrix there's an FW it
   * makes a walkable floor in the object matrix at 2, 4.
   * 
   * In case of scales and doors it cuts the identifier from the end of the
   * string. The scale door pairs will be set later in the setScaleDoor method.
   * 
   * If the method found a string that can't identify, throws an exception.
   * 
   * @return the object matrix
   */
  public LevelObject[][] createObjectMatrix() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        String currentString = stringMatrix[i][j];
        if (currentString.equals("O")) {
          Floor floor = new Floor(true, null);
          objectMatrix[i][j] = floor;
          oneill = new Player(objectMatrix[i][j], Color.BLUE, Direction.NORTH);
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = oneill.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "oneill_sprites.png";
          
          stringMatrix[i][j] = "FW";
        } else if (currentString.equals("R")) {
          Floor floor = new Floor(true, null);
          objectMatrix[i][j] = floor;
          replicator = new Replicator(objectMatrix[i][j]);
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = replicator.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "replicator.png";
          
          stringMatrix[i][j] = "FW";
        } else if (currentString.equals("J")) {
          Floor floor = new Floor(true, null);
          objectMatrix[i][j] = floor;
          jaffa = new Player(objectMatrix[i][j], Color.GREEN, Direction.NORTH);
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = jaffa.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "jaffa_sprites.png";
          
          stringMatrix[i][j] = "FW";
        } else if (currentString.equals("FW")) {
          Floor floor = new Floor(true, null);
          objectMatrix[i][j] = floor;
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = 0;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = null;
        } else if (currentString.equals("FNW")) {
          Floor wall = new Floor(false, null);
          objectMatrix[i][j] = wall;
          
          idMatrix[i][j][0] = wall.ID;
          idMatrix[i][j][1] = 0;
          imageNameMatrix[i][j][0] = "wall.png";
          imageNameMatrix[i][j][1] = null;
        } else if (currentString.equals("FB")) {
          Floor floor = new Floor(true, null);
          Box box = new Box();
          floor.setItem(box);
          objectMatrix[i][j] = floor;
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = box.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "box.png";
        } else if (currentString.equals("FZ")) {
          Floor floor = new Floor(true, null);
          Zpm zpm = new Zpm();
          floor.setItem(zpm);
          objectMatrix[i][j] = floor;
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = zpm.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "zpm.png";
        } else if (currentString.equals("G")) {
          Floor floor = new Floor(true, null);
          Gap gap = new Gap();
          objectMatrix[i][j] = gap;
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = gap.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "gap.png";
        } else if (currentString.equals("SP")) {
          SpecWall specWall = new SpecWall();
          objectMatrix[i][j] = specWall;
          
          idMatrix[i][j][0] = specWall.ID;
          idMatrix[i][j][1] = 0;
          imageNameMatrix[i][j][0] = "specwall.png";
          imageNameMatrix[i][j][1] = null;
        } else if (currentString.equals("E")) {
          objectMatrix[i][j] = null;
          idMatrix[i][j][0] = 0;
          idMatrix[i][j][1] = 0;
          imageNameMatrix[i][j][0] = null;
          imageNameMatrix[i][j][1] = null;
        } else if (currentString.substring(0, 1).equals("D")) {
          Floor floor = new Floor(true, null);
          Door door = new Door();
          objectMatrix[i][j] = door;
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = door.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "door.png";
        } else if (currentString.length() >= 2 && currentString.substring(0, 2).equals("SC")) {
          Floor floor = new Floor(true, null);
          Scale scale = new Scale(2);
          objectMatrix[i][j] = scale;
          
          idMatrix[i][j][0] = floor.ID;
          idMatrix[i][j][1] = scale.ID;
          imageNameMatrix[i][j][0] = "floor.png";
          imageNameMatrix[i][j][1] = "scale.png";
        } else {
          throw new RuntimeException("Wrong abbreviation in text file.");
        }
      }
    }
    return objectMatrix;
  }

  /**
   * Loops through the matrix and sets every object's neighbors.
   * 
   * Details:
   * 
   * The method iterates over the object matrix, and calls the setNeighbours()
   * method on each level object.
   */
  public void initNeighborDiscovery() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        setNeighbours(objectMatrix[i][j], i, j);
      }
    }
  }

  /**
   * Checks the valid neighbors around a level object and sets them.
   * 
   * Details:
   * 
   * There are four cases of neighbors of an object. The method first checks if
   * each neighbor of the object parameter is in bound of the array. If so, it
   * sets them.
   * 
   * @param levelObject
   *          - the one who we find neighbors for
   * @param columnIndex
   *          - column index
   * @param rowIndex
   *          - row index
   */
  public void setNeighbours(LevelObject levelObject, int columnIndex, int rowIndex) {
    // SOUTH neighbor
    if (columnIndex + 1 >= 0 && columnIndex + 1 < HEIGHT) {
      levelObject.setNeighbour(Direction.SOUTH, objectMatrix[columnIndex + 1][rowIndex]);
    }
    // NORTH neighbor
    if (columnIndex - 1 >= 0 && columnIndex - 1 < HEIGHT) {
      levelObject.setNeighbour(Direction.NORTH, objectMatrix[columnIndex - 1][rowIndex]);
    }
    // EAST neighbor
    if (rowIndex + 1 >= 0 && rowIndex + 1 < WIDTH) {
      levelObject.setNeighbour(Direction.EAST, objectMatrix[columnIndex][rowIndex + 1]);
    }
    // WEST neighbor
    if (rowIndex - 1 >= 0 && rowIndex - 1 < WIDTH) {
      levelObject.setNeighbour(Direction.WEST, objectMatrix[columnIndex][rowIndex - 1]);
    }
  }

  /**
   * Iterates through the matrix, and sets the doors to their scales.
   * 
   * Details:
   * 
   * The method creates a hashmap to store the coherent scales and doors. First
   * it iterates through the object matrix. If the current object is a door,
   * gets the door's unique identifier and saves it. In the next step the method
   * gets the map value belongs to the current door's unique identifier as a
   * key. If it finds a door throws an exception, if it finds a scale sets the
   * door to the scale If none of them was found it puts the door as value to
   * the map. It's key is the identifier from the current object.
   * 
   * If it finds a scale as current object does the same, cut the identifer, and
   * checks what is in the map...
   * 
   */
  public void setScaleDoor() {
    Map<String, LevelObject> pairMap = new HashMap<>();
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        String currentString = stringMatrix[i][j];

        // Check if it's a door
        if (currentString.substring(0, 1).equals("D")) {
          // If the key is already in the map, we found its scale.
          currentString = currentString.substring(1);
          if (pairMap.get(currentString) != null) {

            if ((pairMap.get(currentString) instanceof Scale) == false) {
              throw new RuntimeException("Unique constraint violation in text file for doors");
            }
            Scale scale = (Scale) pairMap.get(currentString);
            scale.setDoor((Door) objectMatrix[i][j]);
          } else {

            pairMap.put(currentString, objectMatrix[i][j]);
          } // Check fif it's a scale
        } else if (currentString.length() >= 2 && currentString.substring(0, 2).equals("SC")) {
          currentString = currentString.substring(2);
          // If the key is already in the map
          if (pairMap.get(currentString) != null) {

            if ((pairMap.get(currentString) instanceof Door) == false) {
              throw new RuntimeException("Unique constraint violation in text file for scales");
            }
            Scale scale = (Scale) objectMatrix[i][j];
            scale.setDoor((Door) pairMap.get(currentString));
          } else {

            pairMap.put(currentString, objectMatrix[i][j]);
          }

        }
      }
    }
  }

  /**
   * Prints the stringMatrix.
   * 
   * Details:
   * 
   * Loops through the string matrix and prints it out in a formatted table. It
   * uses the get<charactername> method below. If a player is found it prints a
   * special character instead of the original one. It handles the cases when
   * two or three character are at the same position. Also handles the null
   * values of characters, when they aren't in the level file.
   */
  public void printStringMatrix() {
    synchronizeStringMatrix();
    StringBuilder sb = new StringBuilder();
    sb.append(" ");
    for (int s = 0; s < (WIDTH) * 6; s++) {
      sb.append("-");
    }
    System.out.println("\n" + sb.toString());
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        System.out.print(" | " + String.format("%3s", stringMatrix[i][j]));
      }
      System.out.print(" | " + "\n" + sb.toString() + "\n");
    }
  }

  /**
   * If the player modify any level object, basically it doesn't appear in the
   * string matrix. This method iterates through the object matrix, checks
   * changes and makes the appropriate changes in the string matrix also.
   */
  public void synchronizeStringMatrix() {
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        LevelObject currentObject = objectMatrix[i][j];
        // Floor
        if (currentObject instanceof Floor) {
          String floorName;
          Floor floor = (Floor) currentObject;
          if (floor.isWalkable()) {
            floorName = "FW";
          } else {
            floorName = "FNW";
          }
          if (floor.getBox() != null) {
            floorName += "B";
          }
          if (floor.getZpm() != null) {
            floorName += "Z";
          }
          stringMatrix[i][j] = floorName;
        }
        // Scale
        if (currentObject instanceof Scale) {
          Scale scale = (Scale) currentObject;
          StringBuilder sb = new StringBuilder();
          for (int boxNumber = 0; boxNumber < scale.getBoxNumber(); boxNumber++) {
            sb.append("B");
          }
          String boxes = sb.toString();
          stringMatrix[i][j] = stringMatrix[i][j].split("(?<=[0-9])(?=[a-zA-Z])")[0] + boxes;
        }
        // Door
        if (currentObject instanceof Door) {
          String doorName = stringMatrix[i][j].split("(?<=[0-9])(?=[a-zA-Z])")[0];
          if (((Door) currentObject).isWalkable()) {
            stringMatrix[i][j] = doorName + "W";
          } else {
            stringMatrix[i][j] = doorName + "NW";
          }
        }
        // SpecWall
        if (currentObject instanceof SpecWall) {
          SpecWall specWall = (SpecWall) currentObject;
          if (specWall.getStarGate() != null) {
            String color = specWall.getStarGate().getColor().name();
            stringMatrix[i][j] = stringMatrix[i][j].substring(0, 2) + color.substring(0, 1).toLowerCase();
          } else {
            stringMatrix[i][j] = stringMatrix[i][j].substring(0, 2);
          }
        }
        // Gap
        if (currentObject instanceof Gap) {
          Gap gap = (Gap) currentObject;
          if (gap.isWalkable() == true) {
            stringMatrix[i][j] = "GW";
          } else {
            stringMatrix[i][j] = "G";
          }
        }

        // O'neill
        if (oneill != null && currentObject.equals(oneill.getPosition())) {
          stringMatrix[i][j] += "O";
        }
        // Jaffa
        if (jaffa != null && currentObject.equals(jaffa.getPosition())) {
          stringMatrix[i][j] += "J";
        }
        // Replicator
        if (replicator != null && ReplicatorContainer.getReplicator(currentObject) != null) {
          stringMatrix[i][j] += "R";
        }
      }
    }
  }

  public String getLevelAsString() {
    synchronizeStringMatrix();
    String levelString = Arrays.deepToString(stringMatrix);
    return levelString;
  }

  public String getLevelAsString2() {
    synchronizeStringMatrix();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        sb.append(stringMatrix[i][j]);
      }
    }
    return sb.toString();
  }

  /**
   * Private constructor for the singleton class.
   */
  private LevelBuilder() {
  }

  public LevelObject[][] getObjectMatrix() {
    return objectMatrix;
  }

  public void setObjectMatrix(LevelObject[][] objectMatrix) {
    this.objectMatrix = objectMatrix;
  }

  public String[][] getStringMatrix() {
    return stringMatrix;
  }

  public void setStringMatrix(String[][] stringMatrix) {
    this.stringMatrix = stringMatrix;
  }

  public Player getOneill() {
    return oneill;
  }

  public void setOneill(Player oneill) {
    this.oneill = oneill;
  }

  public Replicator getReplicator() {
    return replicator;
  }

  public void setReplicator(Replicator replicator) {
    this.replicator = replicator;
  }

  public Player getJaffa() {
    return jaffa;
  }

  public void setJaffa(Player jaffa) {
    this.jaffa = jaffa;
  }
  
  public int[][][] getIdMatrix() {
    return idMatrix;
  }
  
  public String[][][] getImageNameMatrix() {
    return imageNameMatrix;
  }
}
