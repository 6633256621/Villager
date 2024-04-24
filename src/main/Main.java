package main;

import config.Config;
import config.GameState;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import object.monster.Slime;
import panel.GamePanel;
import panel.RootPane;
import render.RenderableHolder;
import utility.InputUtility;
import utility.Timer;
import utility.UserInterface;

import java.util.TimerTask;
public class Main extends Application {
    private final double TIME_DILATION_FACTOR = 240.0;
    private long startTime;
    public static void Main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        startTime = System.nanoTime();
        //setup pane
        RootPane rootpane = new RootPane();
        GamePanel gamepanel = GamePanel.getInstance();
        GraphicsContext gc = gamepanel.getGraphicsContext2D();
        GameLogic logic = new GameLogic();
        RenderableHolder renderableHolder = RenderableHolder.getInstance();
        Timer timer = new Timer();
        rootpane.getChildren().add(gamepanel);
        gamepanel.setFocusTraversable(true);

        //setup scene
        Scene scene = new Scene(rootpane,Config.screenWidth,Config.screenHeight);
        //setup stage
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5); // Darken the canvas by reducing brightness

        // Apply the ColorAdjust effect to the canvas

        //animation timer for drawing
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double elapsedTime = (now - startTime) / 1_000_000_000.0;
                elapsedTime *= TIME_DILATION_FACTOR;
                gamepanel.paintComponent();
                logic.logicUpdate();
                renderableHolder.update();
                timer.drawTime(elapsedTime,gc);
                InputUtility.getKeyPressed().remove(KeyCode.ENTER);
                InputUtility.getKeyPressed().remove(KeyCode.SPACE);
            }
        };
        animation.start();//start animation timer
    }
}
