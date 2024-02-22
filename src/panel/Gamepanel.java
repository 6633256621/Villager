package panel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import render.Renderable;
import render.RenderableHolder;

public class Gamepanel extends Canvas {
    public static Gamepanel instance;

    final int originalTileSize = 16;//16X16
    final int scale = 3;//Scale to adjust
    final int tileSize = originalTileSize * scale;//48x48
    final int maxColScreen = 16;//col ratio
    final int maxRowScreen = 12;//row ratio
    final int screenWidth = maxColScreen*tileSize;//768
    final int screenHeight = maxRowScreen*tileSize;//576
    GraphicsContext gc = this.getGraphicsContext2D();

    public Gamepanel() {
        setWidth(screenWidth);
        setHeight(screenHeight);
    }

    public void paintComponent() {
        gc.setFill(Color.BLACK);
        for (Renderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity.isVisible()){
                entity.draw(gc);
            }
        }
    }
    public static Gamepanel getInstance() {
        if (instance==null) {
            instance= new Gamepanel();
        }
        return instance;
    }

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
