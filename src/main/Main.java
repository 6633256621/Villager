package main;

import input.InputUtility;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import panel.Gamepanel;
import ent.GameLogic;
import render.Renderable;
import render.RenderableHolder;

public class Main extends Application {
    public static void Main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridpane = new GridPane();
        Gamepanel gamepanel = Gamepanel.getInstance();
        GameLogic logic = new GameLogic();
        gridpane.getChildren().addAll(gamepanel);
        Scene scene = new Scene(gridpane);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                InputUtility.setKeyPressed(keyEvent.getCode(), true);
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                InputUtility.setKeyPressed(keyEvent.getCode(), false);
            }
        });
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.requestFocus();
        stage.show();

        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gamepanel.paintComponent();
                logic.logicUpdate();
                RenderableHolder.getInstance().update();
            }
        };
        animation.start();
    }
}
