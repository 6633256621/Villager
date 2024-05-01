package main;

import config.Config;
import config.GameState;
import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import logic.GameLogic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import object.monster.Slime;
import panel.GamePanel;
import panel.MenuPane;
import panel.RootPane;
import render.RenderableHolder;
import utility.InputUtility;
import utility.Timer;
import utility.UserInterface;

import java.net.URL;
import java.util.TimerTask;

public class Main extends Application {
    private final double TIME_DILATION_FACTOR = 240.0;
    private long startTime;

    public static void Main(String[] args) {
        launch(args);
    }

    private boolean firstTime = false;
    public static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws Exception {
        startTime = System.nanoTime();
        //setup pane
        RootPane rootpane = RootPane.getInstance();
        MenuPane menuPane = new MenuPane();
        GamePanel gamepanel = GamePanel.getInstance();
        GraphicsContext gc = gamepanel.getGraphicsContext2D();
        GameLogic logic = new GameLogic();
        RenderableHolder renderableHolder = RenderableHolder.getInstance();
        Timer timer = new Timer();
        rootpane.getChildren().add(gamepanel);
        rootpane.getChildren().add(menuPane);
        gamepanel.setFocusTraversable(true);

        //setup scene
        Scene scene = new Scene(rootpane, Config.screenWidth, Config.screenHeight);
        //setup stage
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5); // Darken the canvas by reducing brightness

        URL resource = getClass().getResource("sounds/bohemian.mp3");
        mediaPlayer = new MediaPlayer(new Media(resource.toString()));
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
            }
        });

        // Apply the ColorAdjust effect to the canvas

        //animation timer for drawing
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (GameState.start) {
                    if(!firstTime) {
                        fadeOutSound();
                        firstTime=true;
                    }
                    System.out.println(RootPane.getInstance().getChildren().size());
                    double elapsedTime = (now - startTime) / 1_000_000_000.0;
                    elapsedTime *= TIME_DILATION_FACTOR;
                    gamepanel.paintComponent();
                    logic.logicUpdate();
                    renderableHolder.update();
                    timer.drawTime(elapsedTime, gc);
                    InputUtility.getKeyPressed().remove(KeyCode.ENTER);
                    InputUtility.getKeyPressed().remove(KeyCode.SPACE);
                }
            }
        };
        animation.start();//start animation timer
    }
    private static void fadeOutSound() {
        // Gradually decrease volume to 0 over 3 seconds
        Timeline fadeOut = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(mediaPlayer.volumeProperty(), mediaPlayer.getVolume())),
                new KeyFrame(Duration.seconds(3), new KeyValue(mediaPlayer.volumeProperty(), 0)));
        fadeOut.play();
    }
}
