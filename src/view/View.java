package view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import model.levelbuilder.LevelBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {

  Map<Integer, ImageView> map;
  private Stage stage;
  Scene gameScene;
  // TODO GameController controller;

  public void init() {
    LevelBuilder levelBuilder = LevelBuilder.getInstance();
    levelBuilder.init("level/level.txt");
    
    map = new HashMap<Integer, ImageView>();
    
    int[][] idArray = {{1,2,3}, {4,5,6}, {7,8,9}};
    String[][] imageNameArray = {{"oneill.png", "jaffa.png", "floor.png"}, {"floor.png", "floor.png", "specwall.png"}, {"gap.png", "floor.png", "specwall.png"}};
  
    int posX = 0;
    int posY = 0;
    for (int i=0; i<idArray.length; i++) {
      posX = i*128;
      for (int j=0; j<idArray.length; j++) {
        posY = j*128;
        Image img = new Image(imageNameArray[i][j], 128, 128, true, false);
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
        stage.setScene(setupGameScene());
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

    final EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
      public void handle(final KeyEvent keyEvent) {
        KeyCode[] keys = { KeyCode.UP, KeyCode.DOWN, KeyCode.ENTER };
        if (Arrays.asList(keys).contains(keyEvent.getCode())) {
          System.out.println(keyEvent.getCode());
          menu.getChildren().iterator().next().setStyle("-fx-border-color: #fff;");
        }
        keyEvent.consume();
      }
    };
    scene.setOnKeyPressed(keyEventHandler);

    return scene;
  }
  
  private Scene setupGameScene() {
    // TODO Auto-generated method stub
    Group root = new Group();
    Scene scene = new Scene(root, 800, 400, Color.BEIGE);
    Pane pane = new Pane();
    for (ImageView imgView : map.values()) {
      pane.getChildren().add(imgView);
    }
    

    root.getChildren().add(pane);


    
    installEventHandler(scene);
    return scene;
  }

  public static void main(String[] args) {
    View.launch();
  }

  public static void remove(int ID) {

  }

  public static void move(int fromID, int toID) {

  }

  public static void create(int ID, int positionID, String imagename) {

  }
}
