package main;

import config.Config;
import config.GameState;
import javafx.scene.input.KeyCode;
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
    public static void Main(String[] args) {
        launch(args);
    }
    public static int count=0;

    @Override
    public void start(Stage stage) throws Exception {
        //setup pane
        RootPane rootpane = new RootPane();
        GamePanel gamepanel = GamePanel.getInstance();
        GameLogic logic = new GameLogic();
        RenderableHolder renderableHolder = RenderableHolder.getInstance();
        rootpane.getChildren().add(gamepanel);
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
            public void handle(long l) {
                gamepanel.paintComponent();
                logic.logicUpdate();
                renderableHolder.update();
                InputUtility.getKeyPressed().remove(KeyCode.ENTER);
                InputUtility.getKeyPressed().remove(KeyCode.SPACE);
            }
        };
        animation.start();//start animation timer
    }
}
