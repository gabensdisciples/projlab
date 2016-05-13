package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.cells.Floor;
import model.game.Replicator;
import model.levelbuilder.LevelBuilder;
import controller.GameController;

public class View extends Application {

  private static Map<Integer, ImageView> map;
  private static Map<Integer, Floor> coveringFloors = new HashMap<Integer, Floor>();
  private static Stage stage;
  private static Scene gameScene;
  private static final int CELLSIZE = 96;
  private static Pane mapPane;
  private static GameController controller;
  private static Map<String, ImageView> queuedImageViews = new HashMap<String, ImageView>();
  private static Text oneillZpmCount;
  private static Text jaffaZpmCount;
  private static ImageView oneillCurrentBullet;
  private static ImageView jaffaCurrentBullet;

  /**
   * JavaFX method, calls loadLevel()
   */
  public void init() {
    loadLevel();
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    Replicator replicator = levelBuilder.getReplicator();
    
    //Run replicator on a different thread using javagx Task and Platform.runLater
    Task task = new Task<Void>() {
      @Override
      public Void call() throws Exception {
        while (replicator.running) {
          Platform.runLater(replicator);
          Thread.sleep(1000);
        }
        return null;
      }
    };
    Thread replicatorThread = new Thread(task);
    replicatorThread.setDaemon(true);
    replicatorThread.start();
    new Thread(task).start();
  }

  /**
   * Initializes the level and its elements. Creates an ImageView for every
   * element with its correct coordinates and puts it into the map.
   * 
   * @author
   */
  private static void loadLevel() {
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init("level/level.txt");

    map = new HashMap<Integer, ImageView>();

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
            ImageView imgView;
            if (imageNameArray[i][j][z].equals("oneill_sprites.png")
                || imageNameArray[i][j][z].equals("jaffa_sprites.png")) {
              Image img = new Image(imageNameArray[i][j][z], CELLSIZE * 10, CELLSIZE, true, false);
              imgView = new ImageView(img);
              imgView.setViewport(new Rectangle2D(posX, posY, CELLSIZE, CELLSIZE));
            } else {
              Image img = new Image(imageNameArray[i][j][z], CELLSIZE, CELLSIZE, true, false);
              imgView = new ImageView(img);
            }
            imgView.setX(posX);
            imgView.setY(posY);
            imgView.getStyleClass().addAll(getObjectNameFromImage(imageNameArray[i][j][z]));
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
    View.stage = stage;
    Scene menuScene = setupMenuScene();

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

  private static void installEventHandler(final Scene keyNode) {
    final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
      public void handle(final KeyEvent keyEvent) {
        controller.addPressedKey(keyEvent.getCode().toString());
        // System.out.println(keyEvent.getCode());
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
  private static Scene setupMenuScene() {
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
        gameScene = setupGameScene();
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

  private static Scene setupGameScene() {
    BorderPane root = new BorderPane();
    Scene scene = new Scene(root);
    mapPane = new Pane();
    for (ImageView imgView : map.values()) {
      mapPane.getChildren().add(imgView);
    }

    HBox gameBar = new HBox();
    gameBar.getChildren().addAll(new ImageView(new Image("oneill.png", CELLSIZE / 2, CELLSIZE / 2, true, false)),
        oneillCurrentBullet = new ImageView(new Image("blue_bullet.png", CELLSIZE / 2, CELLSIZE / 2, true, false)),
        new ImageView(new Image("zpm.png", CELLSIZE / 2, CELLSIZE / 2, true, false)), oneillZpmCount = new Text("0"),
        new ImageView(new Image("jaffa.png", CELLSIZE / 2, CELLSIZE / 2, true, false)),
        jaffaCurrentBullet = new ImageView(new Image("green_bullet.png", CELLSIZE / 2, CELLSIZE / 2, true, false)),
        new ImageView(new Image("zpm.png", CELLSIZE / 2, CELLSIZE / 2, true, false)), jaffaZpmCount = new Text("0"));
    oneillZpmCount.setFont(new Font(CELLSIZE / 2));
    jaffaZpmCount.setFont(new Font(CELLSIZE / 2));
    gameBar.setStyle("-fx-background-color: #999;");
    root.setTop(gameBar);
    root.setCenter(mapPane);
    // root.getChildren().add(mapPane);
    scene.setOnKeyPressed(null);
    installEventHandler(scene);
    return scene;
  }

  public static void main(String[] args) {
    View.launch();
  }

  /**
   * Removes an element from the game scene and map
   * 
   * @param ID
   *          the element to remove
   */
  public static void remove(int ID) {
    ImageView toRemove = map.get(ID);
    System.out.println("Removing this: " + ID + " ");
    if (toRemove != null && toRemove.getStyleClass().contains("stargate")) {
      // Shrinking animation for dissappearing stargate
      addShrinkingAnimation(toRemove);
    } else if (toRemove.getStyleClass().contains("replicator")) {
      // Queue replicator for removal when bullet gets to it
      queuedImageViews.put("replicator", toRemove);
    } else
      if (toRemove.getStyleClass().contains("oneill_sprites") || toRemove.getStyleClass().contains("jaffa_sprites")) {
      addShrinkingAnimation(toRemove);
    } else {
      if (toRemove != null && !toRemove.getStyleClass().contains("flying")) {
        mapPane.getChildren().removeAll(toRemove);
        map.remove(ID);
      }
    }

  }

  /**
   * Moves an element.
   * 
   * @param fromID
   * @param toID
   */
  public static void move(int fromID, int toID) {
    System.out.println("Moving this: " + fromID);
    System.out.println("Moving to here: " + toID);
    ImageView toCell = map.get(toID);
    ImageView toMove = map.get(fromID);
    if (toMove != null && toCell != null) {
      toMove.toFront();
      int duration = 500;
      // If bullet, rotate to correct direction
      if (toMove.getStyleClass().contains("bullet")) {
        toMove.getStyleClass().add("flying");
        duration = 500;
        double rotation = 0;
        if (toCell.getY() < toMove.getY()) {
          // Face north
          rotation = -90;
        } else if (toCell.getY() > toMove.getY()) {
          // Face south
          rotation = 90;
        } else if (toCell.getX() < toMove.getX()) {
          // Face west
          rotation = -180;
        } else if (toCell.getX() > toMove.getX()) {
          // Face east
          rotation = 0;
        }
        toMove.setRotate(rotation);
      }

      // Rotate players using sprites
      int spriteWalking = 0;
      int spriteWalking2 = 0;
      int sprite = 0;
      if (toMove.getStyleClass().contains("oneill_sprites") || toMove.getStyleClass().contains("jaffa_sprites")) {
        duration = 500;
        // Have to subtract one from sprite's number
        if (toCell.getY() < toMove.getY()) {
          // Face north
          // 2nd sprite
          // 9th 10th sprite walking
          sprite = 1;
          spriteWalking = 8;
          spriteWalking2 = 9;
        } else if (toCell.getY() > toMove.getY()) {
          // Face south
          // 1st sprite standing
          // 7th 8th sprite walking
          sprite = 0;
          spriteWalking = 6;
          spriteWalking2 = 7;
        } else if (toCell.getX() < toMove.getX()) {
          // Face west
          // 6th sprite standing
          // 5th sprite walking
          sprite = 5;
          spriteWalking = 4;
          spriteWalking2 = 5;
        } else if (toCell.getX() > toMove.getX()) {
          // Face east
          // 3rd sprite standing
          // 4th sprite walking
          sprite = 2;
          spriteWalking = 3;
          spriteWalking2 = 2;
        }
        toMove.setViewport(new Rectangle2D(CELLSIZE * sprite, 0, CELLSIZE, CELLSIZE));
      }
      final KeyValue kvStand = new KeyValue(toMove.viewportProperty(),
          new Rectangle2D(CELLSIZE * sprite, 0, CELLSIZE, CELLSIZE));
      final KeyValue kvStep = new KeyValue(toMove.viewportProperty(),
          new Rectangle2D(CELLSIZE * spriteWalking, 0, CELLSIZE, CELLSIZE));
      final KeyValue kvStep2 = new KeyValue(toMove.viewportProperty(),
          new Rectangle2D(CELLSIZE * spriteWalking2, 0, CELLSIZE, CELLSIZE));

      // Animation
      final Timeline timeline = new Timeline();
      // Animate from current position to next position
      final KeyValue kv = new KeyValue(toMove.xProperty(), toCell.getX());
      final KeyValue kv2 = new KeyValue(toMove.yProperty(), toCell.getY());
      final KeyFrame kfStanding = new KeyFrame(Duration.millis(duration), kvStand);

      final KeyFrame kfMove = new KeyFrame(Duration.millis(duration), kv, kv2);
      // Add walking sprites 6 times while walking
      for (int i = 0; i < 6; i += 2) {
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis((duration / 6) * i), kvStep));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis((duration / 6) * (i + 1)), kvStep2));
      }
      // Add standing frame before stopping movement
      timeline.getKeyFrames().add(kfStanding);
      // Add movement
      timeline.getKeyFrames().add(kfMove);

      // Remove bullet when animation finished and create queued stargate
      timeline.setOnFinished(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
          if (toMove.getStyleClass().contains("bullet")) {
            toMove.getStyleClass().remove("flying");
            ImageView stargate = queuedImageViews.remove(getColorFromClasses(toMove.getStyleClass()));
            if (stargate != null) {
              mapPane.getChildren().add(stargate);
            }
            remove(fromID);
            if (queuedImageViews.containsKey("replicator")) {
              // If we got here, a proper remove call already queued the
              // replicator for removal, therefore a bullet killed it
              ImageView replicator = queuedImageViews.get("replicator");
              // Animate replicator death, remove when finished
              addShrinkingAnimation(replicator);
            }
          }
        }
      });
      timeline.playFromStart();
    }
  }

  /**
   * creates an element
   * 
   * @param ID
   *          of new element
   * @param positionID
   *          position of its position
   * @param imagename
   */
  public static void create(int ID, int positionID, String imagename) {
    Image img = new Image(imagename, CELLSIZE, CELLSIZE, true, false);
    ImageView created = new ImageView(img);
    ImageView position = map.get(positionID);
    created.setX(position.getX());
    created.setY(position.getY());
    // Store entity name as CSS class
    created.getStyleClass().addAll(getObjectNameFromImage(imagename));
    // System.out.println("Creating this: " + ID + " " + " here: " +
    // positionID);

    if (created.getStyleClass().contains("stargate")) {
      // Expanding effect for stargate
      ScaleTransition expandsTransition = new ScaleTransition(Duration.millis(1000), created);
      expandsTransition.setFromX(0);
      expandsTransition.setFromY(0);
      expandsTransition.setToX(1.0);
      expandsTransition.setToY(1.0);
      expandsTransition.play();
      // Breathing effect for stargates
      FadeTransition ft = new FadeTransition(Duration.millis(1000), created);
      ft.setFromValue(1.0);
      ft.setToValue(0.7);
      ft.setCycleCount(Timeline.INDEFINITE);
      ft.setAutoReverse(true);
      ft.play();
      // Queue stargate for creation when bullet finished animation
      queuedImageViews.put(getColorFromClasses(created.getStyleClass()), created);
    } else {
      mapPane.getChildren().add(created);
      created.toFront();
    }
    map.put(ID, created);
  }

  // TODO Uj metodusok, dokumentalni kell
  public static void addCover(int doorID, Floor floor) {
    // If the door is already covered, remove the cover and add a new one
    ImageView doorView = map.get(doorID);
    doorView.setImage(new Image("door_sprites.png", CELLSIZE * 4, CELLSIZE, true, false));
    doorView.setViewport(new Rectangle2D(0, 0, CELLSIZE, CELLSIZE));
    KeyValue[] kvs = new KeyValue[4];
    KeyFrame[] kfs = new KeyFrame[4];
    Timeline timeline = new Timeline();
    for (int i = 0; i < kvs.length; i++) {
      kvs[i] = new KeyValue(doorView.viewportProperty(), new Rectangle2D(CELLSIZE * i, 0, CELLSIZE, CELLSIZE));
      kfs[i] = new KeyFrame(Duration.millis(200 * (i + 1)), kvs[i]);
      timeline.getKeyFrames().add(kfs[i]);
    }
    timeline.play();
    // removeCover(doorID);
    // coveringFloors.put(doorIDd, floor);
    // create(floor.ID, doorID, "floor.png");
  }

  public static void removeCover(int doorID) {
    ImageView doorView = map.get(doorID);
    doorView.setImage(new Image("door_sprites.png", CELLSIZE * 4, CELLSIZE, true, false));
    doorView.setViewport(new Rectangle2D(0, 0, CELLSIZE, CELLSIZE));
    KeyValue[] kvs = new KeyValue[4];
    KeyFrame[] kfs = new KeyFrame[4];
    Timeline timeline = new Timeline();
    for (int i = kvs.length - 1; i >= 0; i--) {
      kvs[i] = new KeyValue(doorView.viewportProperty(), new Rectangle2D(CELLSIZE * i, 0, CELLSIZE, CELLSIZE));
      kfs[i] = new KeyFrame(Duration.millis(200 * (kvs.length - i - 1)), kvs[i]);
      timeline.getKeyFrames().add(kfs[i]);
    }
    // timeline.setRate(-1.0);
    timeline.play();
    // If the door is already covered, remove the cover
    // if (coveringFloors.containsKey(doorID)) {
    // remove(coveringFloors.get(doorID).ID);
    // coveringFloors.remove(doorID);
    // }
  }

  /**
   * Displays a game over text, with explanation why the game ended. Sets the
   * keyEventHandler, so you can jump back to the menu with pressing Enter.
   * 
   * @param why
   */
  public static void gameOver(String why) {
    BorderPane bg = new BorderPane();
    bg.setMinWidth(mapPane.getWidth());
    bg.setMinHeight(mapPane.getHeight());
    bg.setStyle("-fx-background-color: #000;");
    stage.getScene().setFill(Color.BLACK);
    Text text = new Text(why);
    text.setFont(new Font(60));
    text.setFill(Color.WHITE);
    bg.setCenter(text);

    Text pressAnyText = new Text("Press enter to show the menu");
    pressAnyText.setFont(new Font(35));
    pressAnyText.setFill(Color.WHITE);
    bg.setBottom(pressAnyText);
    BorderPane.setAlignment(pressAnyText, Pos.BOTTOM_CENTER);
    mapPane.getChildren().add(bg);
    FadeTransition ft = new FadeTransition(Duration.millis(2000), bg);
    ft.setFromValue(0);
    ft.setToValue(1.0);
    ft.play();
    gameScene.setOnKeyPressed(null);
    installAnyKeyEventHandler(gameScene, bg);
    // stage.setScene(gameOver);
  }

  /**
   * Changes the Player's Zpm count in the gameBar
   * 
   * @param player
   * @param count
   */
  public static void refreshZpmCount(String player, int count) {
    if (player.equals("oneill")) {
      oneillZpmCount.setText(((Integer) count).toString());
    } else if (player.equals("jaffa")) {
      jaffaZpmCount.setText(((Integer) count).toString());
    }
  }

  /**
   * Changes the player's current bulletcolor in the gamebar
   * 
   * @param color
   */
  public static void refreshBulletColor(String color) {
    System.out.println(color);
    if (color.equals("BLUE") || color.equals("YELLOW")) {
      oneillCurrentBullet
          .setImage(new Image(color.toLowerCase() + "_bullet.png", CELLSIZE / 2, CELLSIZE / 2, true, false));
    } else if (color.equals("GREEN") || color.equals("RED")) {
      jaffaCurrentBullet
          .setImage(new Image(color.toLowerCase() + "_bullet.png", CELLSIZE / 2, CELLSIZE / 2, true, false));
    }

  }

  /**
   * Key event handler for game over screen. Reloads the level, jumps to menu.
   * Pushes game over pane to background.
   * 
   * @param keyNode
   * @param gameOverBg
   */
  private static void installAnyKeyEventHandler(final Scene keyNode, BorderPane gameOverBg) {
    final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
      public void handle(final KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
          loadLevel();
          stage.setScene(setupMenuScene());
          gameOverBg.toBack();
          // System.out.println(keyEvent.getCode());
        }
        keyEvent.consume();
      }
    };
    keyNode.setOnKeyPressed(keyEventHandler);
  }

  /**
   * Returns the name of the entity without the image file extension
   * 
   * @param imageName
   *          entity image name to remove file extension from
   * 
   * @return entity name, if file extension was found
   */
  private static ArrayList<String> getObjectNameFromImage(String imageName) {
    // get object name from image filename
    ArrayList<String> names = new ArrayList<String>();
    String[] splitName = imageName.split("\\.");
    if (splitName.length > 0) {
      names.add(splitName[0]);

      if (splitName[0].endsWith("bullet")) {
        names.add("bullet");
        String[] colorSplit = splitName[0].split("_");
        names.add(colorSplit[0]);
      } else if (splitName[0].endsWith("stargate")) {
        names.add("stargate");
        String[] colorSplit = splitName[0].split("_");
        names.add(colorSplit[0]);
      }
    }
    return names;
  }

  /**
   * Gets color class from classes list
   * 
   * @param styleClasses
   * @return color of bullet or stargate
   */
  private static String getColorFromClasses(ObservableList<String> styleClasses) {
    String[] colors = { "blue", "yellow", "green", "red" };
    String color = null;
    for (String styleClass : styleClasses) {
      for (int i = 0; i < colors.length; i++) {
        if (styleClass.equals(colors[i])) {
          color = colors[i];
        }
      }
    }
    return color;
  }

  /**
   * Adds a shrinking animation to an ImageView, and deletes it when finished
   * 
   * @param toAnimate
   */
  private static void addShrinkingAnimation(ImageView toAnimate) {
    ScaleTransition shrinksTransition = new ScaleTransition(Duration.millis(1000), toAnimate);
    shrinksTransition.setFromX(1.0);
    shrinksTransition.setFromY(1.0);
    shrinksTransition.setToX(0);
    shrinksTransition.setToY(0);
    shrinksTransition.play();
    shrinksTransition.setOnFinished(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        mapPane.getChildren().remove(toAnimate);
        map.remove(toAnimate);
      }
    });
  }

}
