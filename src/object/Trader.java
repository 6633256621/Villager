package object;

import config.Config;
import config.GameState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import utility.UserInterface;

public class Trader extends Item {
    Player p = Player.getInstance();
    GraphicsContext gc = gp.getGraphicsContext2D();
    public Trader() {
        super();
        z = 10;
        name = "Trader";
        solidArea = new Rectangle(0, 0, Config.tileSize * 4, Config.tileSize * 3);
        image = new Image(ClassLoader.getSystemResourceAsStream("objects/trader.png"));
        setWorldX(64 * Config.tileSize);
        setWorldY(66 * Config.tileSize);
        setCollision(true);
    }

    @Override
    public void draw(GraphicsContext gc) {

        int screenX = worldX - player.getWorldX() + player.getScreenX();
        int screenY = worldY - player.getWorldY() + player.getScreenY();

        if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
            gc.drawImage(image, screenX, screenY, Config.tileSize * 5, Config.tileSize * 3);
        }
    }

    public void update() {
        if (isInteracted()) {
            GameState.traderState=true;
            drawTradeFrame();
        } else {
            GameState.traderState=false;
        }
        setInteracted(false);
    }

    public void drawTradeFrame() {
        UserInterface.drawInventory(p,gc,"right");
    }

    public void drawSubWindow(int x, int y, int width, int height, GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRoundRect(x, y, width, height, 35, 35);
        gc.setGlobalAlpha(1);
        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
        gc.strokeRoundRect(x + 1, y + 1, width - 1, height - 1, 25, 25);
    }
}
