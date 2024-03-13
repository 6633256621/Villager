package main;

import config.Config;
import logic.GameLogic;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import panel.GamePanel;
import panel.RootPane;
import render.RenderableHolder;


public class Main extends Application {
    public static void Main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //setup pane
        RootPane rootpane = new RootPane();
        rootpane.addlistener();
        GamePanel gamepanel = GamePanel.getInstance();
        GameLogic logic = new GameLogic();
        RenderableHolder renderableHolder = RenderableHolder.getInstance();
        GraphicsContext gc = gamepanel.getGraphicsContext2D();
        rootpane.getChildren().addAll(gamepanel);
        //setup scene
        Scene scene = new Scene(rootpane);
        //setup stage
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.setResizable(false);
        stage.show();
        scene.getRoot().requestFocus();

        //animation timer for drawing
        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gamepanel.paintComponent();
                logic.logicUpdate();
                renderableHolder.update();
            }
        };
        animation.start();//start animation timer
    }
}
