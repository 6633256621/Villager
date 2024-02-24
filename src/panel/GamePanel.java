package panel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import interfacepackage.Renderable;
import render.RenderableHolder;
import tile.TileManager;
import utility.UserInterface;

public class GamePanel extends Canvas {
    public static GamePanel instance;

    private int originalTileSize = 16;//16X16
    private int scale = 3;//Scale to adjust
    private int tileSize = originalTileSize * scale;//48x48
    private int maxColScreen = 32;//col ratio
    private int maxRowScreen = 18;//row ratio
    private int screenWidth = maxColScreen * tileSize;//768
    private int screenHeight = maxRowScreen * tileSize;//576

    //world setting
    private int maxWorldCol = 50;
    private int maxWorldRow = 50;
    private int worldWidth = maxWorldCol * tileSize;
    private int worldHeight = maxWorldRow * tileSize;

    private TileManager tileManager = new TileManager(this);
    private GraphicsContext gc = this.getGraphicsContext2D();
    public UserInterface ui = new UserInterface(this);

    //constructor
    public GamePanel() {
        setWidth(screenWidth);
        setHeight(screenHeight);
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(0, 0, screenWidth, screenWidth);
    }

    //draw all entity
    public void paintComponent() {
        gc.setFill(Color.BLACK);
        tileManager.draw(gc);
        for (Renderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity.isVisible()) {
                entity.draw(gc);//draw each entity
            }
        }
        ui.draw(gc);
    }

    //singleton method
    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }


    //getter
    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public int getMaxColScreen() {
        return maxColScreen;
    }

    public int getMaxRowScreen() {
        return maxRowScreen;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }
}
