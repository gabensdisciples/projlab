package main;

import java.util.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Application {

  Map<Integer, ImageView> map;
  // TODO GameController controller;

  public void init() {

  }

  @Override
  public void start(Stage stage) throws Exception {
    // Background Image and view
    Image menuBg = new Image("menu_bg.png");
    ImageView menuBgView = new ImageView();
    menuBgView.setImage(menuBg);

    // Menu point labels
    Label start = new Label("Start");
    start.setFont(new Font(80));
    start.setTextFill(Color.WHITE);

    Label help = new Label("Help");
    help.setFont(new Font(80));
    help.setTextFill(Color.WHITE);

    Label exit = new Label("Exit");
    exit.setFont(new Font(80));
    exit.setTextFill(Color.WHITE);

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

    installEventHandler(scene);

    stage.setScene(scene);
    stage.setTitle("GabeN's Disicples Project Laboatory Application");
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

  public static void main(String[] args) {
    View.launch();
  }

  public void remove(int ID) {

  }

  public void move(int fromID, int toID) {

  }

  public void create(int ID, int positionID, String imagename) {

  }

}
