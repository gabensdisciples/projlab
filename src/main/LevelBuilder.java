package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.LevelObject;
import cells.Scale;
import cells.SpecWall;
import enumerations.Color;
import enumerations.Direction;
import game.Player;
import items.Box;
import items.Zpm;

/**
 * Singleton utility class, builds a level from text file.
 * 
 * @author Gaben's Disciples
 * 
 */
public class LevelBuilder {

  private static int SIZE;

  private static LevelBuilder instance = null;

  private Player oneill;

  // Parsed from stringMatrix
  public LevelObject[][] objectMatrix;
  // Parsed from txt file
  public String[][] stringMatrix;

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
   * Constructs the level.
   * 
   * @param size
   *          - size of the square matrix in the text file
   */
  public void init(int size) {
    SIZE = size;
    stringMatrix = new String[size][size];
    objectMatrix = new LevelObject[size][size];
    createStringMatrix();
    createObjectMatrix();
    initNeighborDiscovery();
    setScaleDoor();
  }

  /**
   * Creates a square matrix from the text file.
   */
  public void createStringMatrix() {
    try {
      InputStream stream = ClassLoader.getSystemResourceAsStream("level.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      try {
        StringBuilder sb = new StringBuilder();
        String line;
        int row = 0;
        while ((line = br.readLine()) != null) {
          String[] values = line.split("\\s+");
          for (int column = 0; column < values.length; column++) {
            stringMatrix[row][column] = values[column];
          }
          row++;
        }
        String everything = sb.toString();
        System.out.println(everything);
      } finally {
        br.close();
      }
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ArrayIndexOutOfBoundsException("Set the correct size of the text file matrix!");
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  // Door <--> Scale összerendelés hiányzik
  /**
   * Creates a matrix with the appropriate objects from the string one.
   *
   * In case of scales and doors it cuts the indentifier from the end of the
   * string.
   * 
   * @return the object matrix
   */
  public LevelObject[][] createObjectMatrix() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        String currentString = stringMatrix[i][j];
        if (currentString.equals("O")) {
          // Oneill gets an empty walkable floor.
          objectMatrix[i][j] = new Floor(true, null);
          oneill = new Player(objectMatrix[i][j], Color.BLUE, Direction.NORTH);
        } else if (currentString.equals("FW")) {
          objectMatrix[i][j] = new Floor(true, null);
        } else if (currentString.equals("FNW")) {
          objectMatrix[i][j] = new Floor(false, null);
        } else if (currentString.equals("FB")) {
          objectMatrix[i][j] = new Floor(true, new Box());
        } else if (currentString.equals("FZ")) {
          objectMatrix[i][j] = new Floor(true, new Zpm());
        } else if (currentString.equals("G")) {
          objectMatrix[i][j] = new Gap();
        } else if (currentString.equals("SP")) {
          objectMatrix[i][j] = new SpecWall();
        } else if (currentString.equals("E")) {
          objectMatrix[i][j] = null;
        } else if (currentString.substring(0, 1).equals("D")) {
          objectMatrix[i][j] = new Door();
        } else if (currentString.length() >= 2 && currentString.substring(0, 2).equals("SC")) {
          objectMatrix[i][j] = new Scale();
        } else {
          throw new RuntimeException("Wrong abbreviation in text file.");
        }
      }
    }
    return objectMatrix;
  }

  /**
   * Loops through the matrix and sets every object's neighbors.
   */
  public void initNeighborDiscovery() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        setNeighbours(objectMatrix[i][j], i, j);
      }
    }
  }

  /**
   * Checks the valid neighbors around a level object and sets them.
   * 
   * @param levelObject
   *          - the one who we find neighbors for
   * @param columnIndex
   *          - column index
   * @param rowIndex
   *          - row index
   */
  public void setNeighbours(LevelObject levelObject, int columnIndex, int rowIndex) {
    // SOUTH
    if (columnIndex + 1 >= 0 && columnIndex + 1 < SIZE) {
      levelObject.setNeighbour(Direction.SOUTH, objectMatrix[columnIndex + 1][rowIndex]);
    }
    // NORTH
    if (columnIndex - 1 >= 0 && columnIndex - 1 < SIZE) {
      levelObject.setNeighbour(Direction.NORTH, objectMatrix[columnIndex - 1][rowIndex]);
    }
    // EAST
    if (rowIndex + 1 >= 0 && rowIndex + 1 < SIZE) {
      levelObject.setNeighbour(Direction.EAST, objectMatrix[columnIndex][rowIndex + 1]);
    }
    // WEST
    if (rowIndex - 1 >= 0 && rowIndex - 1 < SIZE) {
      levelObject.setNeighbour(Direction.WEST, objectMatrix[columnIndex][rowIndex - 1]);
    }
  }

  /**
   * Iterates through the matrix, and sets the doors to their scales.
   */
  public void setScaleDoor() {
    Map<String, LevelObject> pairMap = new HashMap<>();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        String currentString = stringMatrix[i][j];

        // Check if it's a door
        if (currentString.substring(0, 1).equals("D")) {
          // If the key is already in the map, we found its scale.
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
   */
  public void printStringMatrix() {
    int[] position = getPlayerPosition();
    StringBuilder sb = new StringBuilder();
    sb.append(" ");
    for (int s = 0; s < (SIZE - 1) * 7 + 2; s++) {
      sb.append("-");
    }
    System.out.println("\n" + sb.toString());
    for (int i = 0; i < stringMatrix[0].length; i++) {
      for (int j = 0; j < stringMatrix[0].length; j++) {
        if (j == 0) {
          if (i == position[0] && j == position[1]) {
            System.out.print("  | ");
            System.out.print(Character.toChars(0x1F600));
            System.out.print(" | ");
          } else {
            System.out.print(" | " + stringMatrix[i][j] + " | ");
          }

        } else {
          if (i == position[0] && j == position[1]) {
            System.out.print(" ");
            System.out.print(Character.toChars(0x1F600));
            System.out.print("    | ");
          } else {
            System.out.print(String.format("%3s", stringMatrix[i][j]) + " | ");
          }
        }
      }
      System.out.println("\n" + sb.toString());
    }
  }

  /**
   * Prints the classes of objects in the object matrix.
   */
  public void printObjectMatrix() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
      }
      System.out.println();
    }
  }

  /**
   * Finds O'neill position in the objectMatrix.
   * 
   * @return the coordinates
   */
  public int[] getPlayerPosition() {
    int[] position = new int[2];

    LevelObject playerPositon = oneill.getPosition();
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (objectMatrix[i][j].equals(playerPositon)) {
          position[0] = i;
          position[1] = j;
        }
      }
    }
    return position;
  }

  /**
   * Private constructor for the singleton class.
   */
  private LevelBuilder() {
  }

  public static int getSize() {
    return SIZE;
  }

  public static void setSize(int size) {
    SIZE = size;
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

}
