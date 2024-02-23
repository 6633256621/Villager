package main;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import panel.GamePanel;
import ent.GameLogic;
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
                gc.clearRect(0,0, GamePanel.getInstance().getScreenWidth(), GamePanel.getInstance().getScreenHeight());
                gamepanel.paintComponent();
                logic.logicUpdate();
                renderableHolder.update();
            }
        };
        animation.start();//start animation timer
    }
}
