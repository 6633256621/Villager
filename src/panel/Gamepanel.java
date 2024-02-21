package panel;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Gamepanel extends Canvas {
    final int originalTileSize = 16;//16X16
    final int scale = 3;//Scale to adjust
    final int tileSize = originalTileSize * scale;//48x48
    final int maxColScreen = 16;//col ratio
    final int maxRowScreen = 12;//row ratio
    final int screenWidth = maxColScreen*tileSize;//768
    final int screenHeight = maxRowScreen*tileSize;//576

    public Gamepanel() {
        setWidth(screenWidth);
        setHeight(screenHeight);
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,screenWidth,screenHeight);
    }
}
