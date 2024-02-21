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
    GraphicsContext gc = this.getGraphicsContext2D();
    public Gamepanel() {
        setWidth(screenWidth);
        setHeight(screenHeight);
        addlistener();
    }
    public void addlistener() {
        public void paintComponent() {
            gc.setFill(Color.WHITE);

            draw(gc);
        }
    }
}
