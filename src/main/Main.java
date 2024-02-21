package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import panel.Gamepanel;

public class Main extends Application {
    public static void Main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridpane = new GridPane();
        Gamepanel gamepanel = new Gamepanel();
        gridpane.add(gamepanel,0,0);
        Scene scene = new Scene(gridpane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
