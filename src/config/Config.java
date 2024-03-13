package config;

public class Config {
    public static int originalTileSize = 16;//16X16
    public static int scale = 3;//Scale to adjust
    public static int tileSize = originalTileSize * scale;//48x48
    public static int maxColScreen = 32;//col ratio
    public static int maxRowScreen = 18;//row ratio
    public static int screenWidth = maxColScreen * tileSize;//768
    public static int screenHeight = maxRowScreen * tileSize;//576

    //world setting
    public static int maxWorldCol = 90;
    public static int maxWorldRow = 90;
    public static int worldWidth = maxWorldCol * tileSize;
    public static int worldHeight = maxWorldRow * tileSize;
}
