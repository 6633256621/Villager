package tile;

import ent.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import panel.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Player player;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        this.player = Player.getInstance();
        tile = new Tile[10];
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/plain.png"));
        tile[1] = new Tile();
        tile[1].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/flower1.png"));
        tile[2] = new Tile();
        tile[2].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/flower2.png"));
        tile[3] = new Tile();
        tile[3].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/rock1.png"));
        tile[4] = new Tile();
        tile[4].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/rock2.png"));
        tile[5] = new Tile();
        tile[5].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/grass1.png"));
        tile[6] = new Tile();
        tile[6].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/grass2.png"));
        tile[7] = new Tile();
        tile[7].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/wood.png"));
        tile[7].collision=true;
        for (int i = 0; i < 8; i++) {
            tile[i].makeScale(gp);
        }
    }

    public void loadMap() {
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream("maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();

                while (col < gp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext gc) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - player.getWorldX() + player.screenX;
            int screenY = worldY - player.getWorldY() + player.screenY;

            if (worldX + gp.getTileSize() > player.getWorldX() - player.getScreenX() &&
                    worldX - gp.getTileSize() < player.getWorldX() + player.getScreenX() &&
                    worldY + gp.getTileSize() > player.getWorldY() - player.getScreenY() &&
                    worldY - gp.getTileSize() < player.getWorldY() + player.getScreenY()) {
                gc.drawImage(tile[tileNum].image, screenX, screenY, tile[tileNum].getImageView().getFitWidth(),
                        tile[tileNum].getImageView().getFitHeight());
            }


            worldCol++;

            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public Tile[] getTile() {
        return tile;
    }

    public void setTile(Tile[] tile) {
        this.tile = tile;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMapTileNum(int[][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }
}
