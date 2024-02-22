package main;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panel.Gamepanel;
import ent.GameLogic;
import panel.Rootpane;
import render.RenderableHolder;


public class Main extends Application {
    public static void Main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Rootpane rootpane = new Rootpane();
        Gamepanel gamepanel = Gamepanel.getInstance();
        GameLogic logic = new GameLogic();
        rootpane.getChildren().addAll(gamepanel);
        Scene scene = new Scene(rootpane);
        rootpane.addlistener();
        stage.setScene(scene);
        stage.setTitle("Villager");
        stage.setResizable(false);
        stage.show();
        scene.getRoot().requestFocus();


        AnimationTimer animation = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Gamepanel.getInstance().getGc().clearRect(0,0,Gamepanel.getInstance().getScreenWidth(),Gamepanel.getInstance().getScreenHeight());
                gamepanel.paintComponent();
                logic.logicUpdate();
                RenderableHolder.getInstance().update();
            }
        };
        animation.start();
    }
}
