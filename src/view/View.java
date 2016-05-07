package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.game.IdentifiedObject;
import model.levelbuilder.LevelBuilder;
import controller.GameController;

public class View extends Application {

  private static Map<Integer, ImageView> map;
  private Stage stage;
  private static Scene gameScene;
  private static final int CELLSIZE = 96;
  private static Pane mapPane;
  private GameController controller;

  /**
   * Initializes the level and its elements. Creates an ImageView for every
   * element with its correct coordinates and puts it into the map.
   * 
   * @author
   */
  public void init() {
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init("level/level.txt");

    map = new HashMap<Integer, ImageView>();

    // int[][] idArray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    // String[][] imageNameArray = { { "oneill.png", "jaffa.png", "floor.png" },
    // { "floor.png", "floor.png", "specwall.png" }, { "gap.png", "floor.png",
    // "specwall.png" } };
    int[][][] idArray = levelBuilder.getIdMatrix();
    String[][][] imageNameArray = levelBuilder.getImageNameMatrix();
    int posX = 0;
    int posY = 0;
    for (int i = 0; i < idArray.length; i++) {
      posY = i * CELLSIZE;
      for (int j = 0; j < idArray[i].length; j++) {
        posX = j * CELLSIZE;
        for (int z = 0; z < idArray[i][j].length; z++) {
          if (imageNameArray[i][j][z] != null && idArray[i][j][z] != 0) {
            Image img = new Image(imageNameArray[i][j][z], CELLSIZE, CELLSIZE, true, false);
            ImageView imgView = new ImageView(img);
            imgView.setX(posX);
            imgView.setY(posY);
            imgView.getStyleClass().add(getObjectNameFromImage(imageNameArray[i][j][z]));
            
            map.put(idArray[i][j][z], imgView);
          }
        }
      }
    }
    controller = new GameController();
    controller.startWorkerThread(levelBuilder.getOneill(), levelBuilder.getJaffa(), levelBuilder.getReplicator());
  }

  /**
   * Starts the JavaFX app and sets the scene.
   */
  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    Scene menuScene = setupMenuScene();
    gameScene = setupGameScene();

    stage.setScene(menuScene);
    stage.setTitle("GabeN's Disicples Project Laboratory Application");
    stage.show();

  }

  /**
   * Installs the event handler on the given scene. The event handler gives the
   * keyCodes to the GameController.
   * 
   * @param the
   *          scene which gets the eventhandler
   */

  private void installEventHandler(final Scene keyNode) {
    final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
      public void handle(final KeyEvent keyEvent) {
        controller.addPressedKey(keyEvent.getCode().toString());
        System.out.println(keyEvent.getCode());
        //move(4,15);
        // create(11,3, "replicator.png");
        // remove(1);
        keyEvent.consume();
      }
    };
    keyNode.setOnKeyPressed(keyEventHandler);
  }

  /**
   * Sets up the menu scene with the menu buttons with a vbox and the
   * background.
   * 
   * @return the complete scene
   */
  private Scene setupMenuScene() {
    // Background Image and view
    Image menuBg = new Image("menu_bg.png");
    ImageView menuBgView = new ImageView();
    menuBgView.setImage(menuBg);

    // Menu point labels
    Button start = new Button("Start");
    start.getStyleClass().clear();
    start.setFont(new Font(80));
    start.setTextFill(Color.WHITE);
    start.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        stage.setScene(gameScene);
      }
    });

    Button help = new Button("Help");
    help.getStyleClass().clear();
    help.setFont(new Font(80));
    help.setTextFill(Color.WHITE);
    help.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        help.setText("Helped");
      }
    });

    Button exit = new Button("Exit");
    exit.getStyleClass().clear();
    exit.setFont(new Font(80));
    exit.setTextFill(Color.WHITE);
    exit.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Platform.exit();
      }
    });

    // Menu container and centering
    VBox menu = new VBox(10);
    menu.getChildren().addAll(start, help, exit);
    menu.setPrefWidth(menuBg.getWidth());
    menu.setPrefHeight(menuBg.getHeight());
    menu.setAlignment(Pos.CENTER);

    // Init JavaFX Group, scene
    Group root = new Group();
    Scene scene = new Scene(root);
    scene.setFill(Color.BLACK);
    root.getChildren().add(menuBgView);
    root.getChildren().add(menu);

    return scene;
  }

  /**
   * Creates the game scene, iterates through the map, and adds the ImageViews
   * to the scene.
   * 
   * @return the complete game scene
   */

  private Scene setupGameScene() {
    Group root = new Group();
    Scene scene = new Scene(root, stage.getWidth(), stage.getHeight(), Color.BEIGE);
    mapPane = new Pane();
    for (ImageView imgView : map.values()) {
      mapPane.getChildren().add(imgView);
    }
    root.getChildren().add(mapPane);

    installEventHandler(scene);
    return scene;
  }

  private static String getObjectNameFromImage(String imageName) {
    //get object name from image filename
    Pattern objNamePattern = Pattern.compile("(.*).png");
    Matcher objNameMatcher = objNamePattern.matcher(imageName);
    if (objNameMatcher.matches()) {
     return objNameMatcher.group(1);
    }
    return "";
  }
  public static void main(String[] args) {
    View.launch();
  }
  
  /**
   * Removes an element from the game scene and map
   * @param ID the element to remove
   */
  public static void remove(int ID) {
    ImageView toRemove = map.get(ID);
    System.out.println("Removing this: " + ID + " ");

    mapPane.getChildren().removeAll(toRemove);
    map.remove(ID);
  }
/**
 * Moves an element.
 * @param fromID
 * @param toID
 */
  public static void move(int fromID, int toID) {
    System.out.println("Moving this: " + fromID);
    System.out.println("Moving to here: " + toID);  
    ImageView toCell = map.get(toID);
    ImageView toMove = map.get(fromID);
    if (toMove != null && toMove != null) {
      toMove.setX(toCell.getX());
      toMove.setY(toCell.getY());
      toMove.toFront();
    }
  }
/**
 * creates an element
 * @param ID of new element
 * @param positionID position of its position
 * @param imagename 
 */
  public static void create(int ID, int positionID, String imagename) {
    Image img = new Image(imagename, CELLSIZE, CELLSIZE, true, false);
    ImageView created = new ImageView(img);
    ImageView position = map.get(positionID);
    created.setX(position.getX());
    created.setY(position.getY());
    created.getStyleClass().add(getObjectNameFromImage(imagename));
    System.out.println("Creating this: " + ID + " "  + " here: " + positionID);
    map.put(ID, created);
    mapPane.getChildren().add(created);
    created.toFront();
  }
}
