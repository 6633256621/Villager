package tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import panel.GamePanel;

import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/flower2.png"));
    }

    public void draw(GraphicsContext gc) {
        int x = 0;
        int y = 0;
        int col = 0;
        int row = 0;

        while (col < gp.getMaxColScreen() && row < gp.getMaxRowScreen()) {
            gc.drawImage(tile[0].image,x,y,gp.getTileSize(),gp.getTileSize());
            col++;
            x+= gp.getTileSize();

            if(col==gp.getMaxColScreen()) {
                col=0;
                x=0;
                row++;
                y+=gp.getTileSize();
            }
        }
    }
}
