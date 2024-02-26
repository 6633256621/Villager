package panel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import interfacepackage.Renderable;
import javafx.scene.text.Font;
import render.RenderableHolder;
import tile.TileManager;
import utility.CollisionChecker;
import utility.InputUtility;
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
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    //constructor
    public GamePanel() {
        setWidth(screenWidth);
        setHeight(screenHeight);
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(0, 0, screenWidth, screenWidth);
    }

    //draw all entity
    public void paintComponent() {
        //Debug
        long drawStart = System.nanoTime();


        gc.setFill(Color.BLACK);
        tileManager.draw(gc);
        for (Renderable entity : RenderableHolder.getInstance().getEntities()) {
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

    public static void setInstance(GamePanel instance) {
        GamePanel.instance = instance;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public void setOriginalTileSize(int originalTileSize) {
        this.originalTileSize = originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void setMaxColScreen(int maxColScreen) {
        this.maxColScreen = maxColScreen;
    }

    public void setMaxRowScreen(int maxRowScreen) {
        this.maxRowScreen = maxRowScreen;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setMaxWorldCol(int maxWorldCol) {
        this.maxWorldCol = maxWorldCol;
    }

    public void setMaxWorldRow(int maxWorldRow) {
        this.maxWorldRow = maxWorldRow;
    }

    public void setWorldWidth(int worldWidth) {
        this.worldWidth = worldWidth;
    }

    public void setWorldHeight(int worldHeight) {
        this.worldHeight = worldHeight;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
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
