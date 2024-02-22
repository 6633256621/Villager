package panel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import render.Renderable;
import render.RenderableHolder;

public class Gamepanel extends Canvas {
    public static Gamepanel instance;

    private int originalTileSize = 16;//16X16
    private int scale = 3;//Scale to adjust
    private int tileSize = originalTileSize * scale;//48x48
    private int maxColScreen = 16;//col ratio
    private int maxRowScreen = 12;//row ratio
    private int screenWidth = maxColScreen*tileSize;//768
    private int screenHeight = maxRowScreen*tileSize;//576
    private GraphicsContext gc = this.getGraphicsContext2D();

    //constructor
    public Gamepanel() {
        setWidth(screenWidth);
        setHeight(screenHeight);
    }

    //draw all entity
    public void paintComponent() {
        gc.setFill(Color.BLACK);
        for (Renderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity.isVisible()){
                entity.draw(gc);//draw each entity
            }
        }
    }

    //singleton method
    public static Gamepanel getInstance() {
        if (instance==null) {
            instance= new Gamepanel();
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
}
