package tile;

import config.Config;
import object.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
        tile = new Tile[15];
        mapTileNum = new int[Config.maxWorldCol][Config.maxWorldRow];
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
        tile[8] = new Tile();
        tile[8].image = new Image(ClassLoader.getSystemResourceAsStream("tiles/forest.png"));
        tile[8].collision=true;
        for (int i = 0; i < 9; i++) {
            tile[i].makeScale(gp);
        }
    }

    public void loadMap() {
        try {
            InputStream is = ClassLoader.getSystemResourceAsStream("maps/world01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < Config.maxWorldCol && row < Config.maxWorldRow) {
                String line = br.readLine();

                while (col < Config.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == Config.maxWorldCol) {
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

        while (worldCol < Config.maxWorldCol && worldRow < Config.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * Config.tileSize;
            int worldY = worldRow * Config.tileSize;
            int screenX = worldX - player.getWorldX() + player.screenX;
            int screenY = worldY - player.getWorldY() + player.screenY;

            if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                    worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                    worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                    worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
                gc.drawImage(tile[tileNum].image, screenX, screenY, tile[tileNum].getImageView().getFitWidth(),
                        tile[tileNum].getImageView().getFitHeight());
            }


            worldCol++;

            if (worldCol == Config.maxWorldCol) {
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
