package panel;

import config.Config;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import render.Renderable;
import render.RenderableHolder;
import tile.TileManager;
import utility.CollisionChecker;
import utility.InputUtility;
import utility.UserInterface;

public class GamePanel extends Canvas {
    public static GamePanel instance;
    TileManager tileManager= new TileManager(this);

    public GraphicsContext gc = this.getGraphicsContext2D();
    public UserInterface ui = new UserInterface(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);


    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }

    public GamePanel() {
        setWidth(Config.screenWidth);
        setHeight(Config.screenHeight);
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(0, 0, Config.screenWidth, Config.screenWidth);
    }

    public void paintComponent() {
        //Debug
        long drawStart = System.nanoTime();


        gc.setFill(Color.BLACK);
        tileManager.draw(gc);
        for (Renderable entity : RenderableHolder.getInstance().getObjects()) {
            if (entity.isVisible()) {
                entity.draw(gc);//draw each entity
            }
        }
        ui.draw(gc);
        long drawEnd = System.nanoTime();
        long passed = drawEnd - drawStart;
        if (InputUtility.isKeyPressed(KeyCode.P)){
            gc.setFill(Color.WHITE);
            gc.setFont(new Font(20));
            gc.fillText("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time :" + passed);
        }
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public UserInterface getUi() {
        return ui;
    }

    public void setUi(UserInterface ui) {
        this.ui = ui;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }
}
