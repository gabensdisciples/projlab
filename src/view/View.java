package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

public class View extends Application {

  private static Map<Integer, ImageView> map;
  private Stage stage;
  private static Scene gameScene;
  private static final int CELLSIZE = 128;
  private static Pane mapPane;

  // TODO GameController controller;

  public void init() {
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init("level/level.txt");

    map = new HashMap<Integer, ImageView>();

    //int[][] idArray = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
    //String[][] imageNameArray = { { "oneill.png", "jaffa.png", "floor.png" },
    //    { "floor.png", "floor.png", "specwall.png" }, { "gap.png", "floor.png", "specwall.png" } };
    int[][] idArray = levelBuilder.getIdMatrix();
    String[][] imageNameArray = levelBuilder.getImageNameMatrix();
    int posX = 0;
    int posY = 0;
    for (int i = 0; i < idArray.length; i++) {
      posY = i * CELLSIZE;
      for (int j = 0; j < idArray[i].length; j++) {
        posX = j * CELLSIZE;
        Image img = new Image(imageNameArray[i][j], CELLSIZE, CELLSIZE, true, false);
        ImageView imgView = new ImageView(img);
        imgView.setX(posX);
        imgView.setY(posY);
        map.put(idArray[i][j], imgView);
      }
    }
  }

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    Scene menuScene = setupMenuScene();
    gameScene = setupGameScene();

    
    stage.setScene(menuScene);
    stage.setTitle("GabeN's Disicples Project Laboratory Application");
    stage.show();

  }

  private void installEventHandler(final Scene keyNode) {
    final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
      public void handle(final KeyEvent keyEvent) {
        // TODO controller.addPressedKey(keyEvent.getCode());
        System.out.println(keyEvent.getCode());
        //move(4,5);
        //create(11,3, "replicator.png");
        //remove(1);
        keyEvent.consume();
      }
    };
    keyNode.setOnKeyPressed(keyEventHandler);
  }

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

  private Scene setupGameScene() {
    Group root = new Group();
    Scene scene = new Scene(root, stage.getWidth(), stage.getHeight(), Color.BEIGE);
    mapPane = new Pane();
    for (ImageView imgView : map.values()) {
//      String[] needsFloor = {"oneill.png", "replicator.png", "gap.png", "jaffa.png", "scale.png", "door.png", "box.png", "zpm.png"};
//      if (Arrays.asList(needsFloor).contains(imgView.getImage().toString())) {
//        Image floor = new Image("floor.png", CELLSIZE, CELLSIZE, true, false);
//        ImageView floorView = new ImageView(floor);
//        floorView.setX(imgView.getX());
//        floorView.setY(imgView.getY());
//      }
      mapPane.getChildren().add(imgView);
    }
    root.getChildren().add(mapPane);

    installEventHandler(scene);
    return scene;
  }

  public static void main(String[] args) {
    View.launch();
  }

  public static void remove(int ID) {
    ImageView toRemove = map.get(ID);
    mapPane.getChildren().remove(toRemove);
    map.remove(ID);
  }

  public static void move(int fromID, int toID) {
    ImageView toCell = map.get(toID);
    ImageView toMove = map.get(fromID);
    toMove.setX(toCell.getX());
    toMove.setY(toCell.getY());
  }

  public static void create(int ID, int positionID, String imagename) {
    Image img = new Image(imagename, CELLSIZE, CELLSIZE, true, false);
    ImageView created = new ImageView(img);
    ImageView position = map.get(positionID);
    created.setX(position.getX());
    created.setY(position.getY());
    map.put(IdentifiedObject.maxID+1, created);
    mapPane.getChildren().add(created);
    created.toFront();
  }

}
