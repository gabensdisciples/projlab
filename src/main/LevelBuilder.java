package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import cells.Door;
import cells.Floor;
import cells.Gap;
import cells.LevelObject;
import cells.Scale;
import cells.SpecWall;
import enumerations.Color;
import enumerations.Direction;
import game.Player;
import game.Replicator;
import items.Box;
import items.Zpm;

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
  public void init() {
    measureLevelSize();
    createStringMatrix();
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
  public void measureLevelSize() {
    try {
      InputStream stream = ClassLoader.getSystemResourceAsStream("level.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
      try {
        int row = 0;
        String startLine = br.readLine();
        String[] startLineSplit = startLine.split("\\s+");
        WIDTH = startLineSplit.length;
        String line;
        while ((line = br.readLine()) != null) {
          row++;
        }
        row = row + 1;
        HEIGHT = row;
        stringMatrix = new String[HEIGHT][WIDTH];
        objectMatrix = new LevelObject[HEIGHT][WIDTH];
      } finally {
        br.close();
      }
    } catch (ArrayIndexOutOfBoundsException exception) {
      throw new ArrayIndexOutOfBoundsException("Set the correct size of the text file matrix!");
    } catch (IOException exception) {
      exception.printStackTrace();
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
  public void createStringMatrix() {
    try {
      InputStream stream = ClassLoader.getSystemResourceAsStream("level.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(stream));
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
      exception.printStackTrace();
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
   * In case of scales and doors it cuts the indentifier from the end of the
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
          objectMatrix[i][j] = new Floor(true, null);
          oneill = new Player(objectMatrix[i][j], Color.BLUE, Direction.NORTH);
          stringMatrix[i][j] = "FW";
        } else if (currentString.equals("R")) {
          objectMatrix[i][j] = new Floor(true, null);
          replicator = new Replicator(objectMatrix[i][j]);
          stringMatrix[i][j] = "FW";
        } else if (currentString.equals("J")) {
          objectMatrix[i][j] = new Floor(true, null);
          jaffa = new Player(objectMatrix[i][j], Color.GREEN, Direction.NORTH);
          stringMatrix[i][j] = "FW";
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
          objectMatrix[i][j] = new Scale(2);
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
   * method on each levelobject.
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
   * the map. It's key is the identifer from the current object.
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
            System.out.println("i = " + i + " j = " + j + " scale = " + currentString);
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
    int[] oneillPosition = getOneillPosition();
    int[] jaffaPosition = getJaffaPosition();
    int[] replicatorPosition = getReplicatorPosition();
    StringBuilder sb = new StringBuilder();
    sb.append(" ");
    for (int s = 0; s < (WIDTH) * 6; s++) {
      sb.append("-");
    }
    System.out.println("\n" + sb.toString());
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        Boolean oneillFound;
        Boolean jaffaFound;
        Boolean replicatorFound;

        if (oneillPosition == null) {
          oneillFound = false;
        } else {
          oneillFound = new Boolean(i == oneillPosition[0] && j == oneillPosition[1]);
        }
        if (jaffaPosition == null) {
          jaffaFound = false;
        } else {
          jaffaFound = new Boolean(i == jaffaPosition[0] && j == jaffaPosition[1]);
        }
        if (replicatorPosition == null) {
          replicatorFound = false;
        } else {
          replicatorFound = new Boolean(i == replicatorPosition[0] && j == replicatorPosition[1]);
        }

        Boolean anyFound = (oneillFound || jaffaFound || replicatorFound);
        String oneillString = String.valueOf(Character.toChars(0x1F600));
        String jaffaString = String.valueOf(Character.toChars(0x1F606));
        String replicatorString = String.valueOf(Character.toChars(0x1F639));
        Boolean[] foundBoolean = { oneillFound, jaffaFound, replicatorFound };
        String[] foundString = { oneillString, jaffaString, replicatorString };

        if (anyFound) {
          System.out.print(" |  ");
          for (int k = 0; k < foundBoolean.length; k++) {
            if (foundBoolean[k] == true) {
              System.out.print(foundString[k]);
            }
          }
          System.out.print("   ");
        } else {
          System.out.print(" | " + String.format("%3s", stringMatrix[i][j]));
        }

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
        if (currentObject instanceof Floor) {
          if (((Floor) currentObject).getBox() != null) {
            stringMatrix[i][j] = "FB";
          } else {
            stringMatrix[i][j] = "FW";
          }
        }
      }
    }
  }

  /**
   * Finds O'neill position in the objectMatrix.
   * 
   * @return the coordinates
   */
  public int[] getOneillPosition() {
    int[] position = new int[2];
    if (oneill == null) {
      throw new NullPointerException("No oneill in level file!");
    }
    LevelObject oneillPositon = oneill.getPosition();
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        if (objectMatrix[i][j].equals(oneillPositon)) {
          position[0] = i;
          position[1] = j;
        }
      }
    }
    return position;
  }

  /**
   * Finds jaffa's position in the objectMatrix.
   * 
   * @return the coordinates
   */
  public int[] getJaffaPosition() {
    int[] position = new int[2];
    if (jaffa == null) {
      return null;
    }
    LevelObject jaffaPositon = jaffa.getPosition();
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        if (objectMatrix[i][j].equals(jaffaPositon)) {
          position[0] = i;
          position[1] = j;
        }
      }
    }
    return position;
  }

  /**
   * Finds the replicator's position in the objectMatrix.
   * 
   * @return the coordinates
   */
  public int[] getReplicatorPosition() {
    int[] position = new int[2];
    if (replicator == null) {
      return null;
    }
    LevelObject replicatorPositon = replicator.getPosition();
    for (int i = 0; i < HEIGHT; i++) {
      for (int j = 0; j < WIDTH; j++) {
        if (objectMatrix[i][j].equals(replicatorPositon)) {
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
}
