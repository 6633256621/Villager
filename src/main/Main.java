package main;

import config.Config;
import config.GameState;
import javafx.scene.control.Label;
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
import utility.UserInterface;
import java.util.Timer;
import java.util.TimerTask;
public class Main extends Application {
    private final double TIME_DILATION_FACTOR = 240.0;
    private final int INTERVAL_LENGTH = 20;
    private Label timerLabel;
    private long startTime;
    public static void Main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        timerLabel = new Label("00:00");
        timerLabel.setVisible(false);
        startTime = System.nanoTime();
        //setup pane
        RootPane rootpane = new RootPane();
        GamePanel gamepanel = GamePanel.getInstance();
        GraphicsContext gc = gamepanel.getGraphicsContext2D();
        GameLogic logic = new GameLogic();
        RenderableHolder renderableHolder = RenderableHolder.getInstance();
        rootpane.getChildren().add(gamepanel);
        rootpane.getChildren().add(timerLabel);
        gamepanel.setFocusTraversable(true);

        //setup scene
        Scene scene = new Scene(rootpane,Config.screenWidth,Config.screenHeight);
        //setup stage
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.setResizable(false);
        stage.show();

        //animation timer for drawing
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long now) {
                double elapsedTime = (now - startTime) / 1_000_000_000.0;
                elapsedTime *= TIME_DILATION_FACTOR;
                gamepanel.paintComponent();
                logic.logicUpdate();
                renderableHolder.update();
                drawTime(elapsedTime,gc);
                InputUtility.getKeyPressed().remove(KeyCode.ENTER);
                InputUtility.getKeyPressed().remove(KeyCode.SPACE);
            }
        };
        animation.start();//start animation timer
    }
    private void drawTime(double elapsedTime,GraphicsContext gc) {
        gc.setFont(new Font("Georgia",40));
        gc.setFill(Color.WHITE);
        gc.fillText(formatTime((long) elapsedTime),Config.tileSize*29,Config.tileSize);
        gc.setFont(new Font("Georgia",20));
    }
    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
