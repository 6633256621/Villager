package object;

import ent.Player;
import interfacepackage.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import panel.GamePanel;

public abstract class SuperObject implements Renderable {
    protected Image image;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;
    protected int z;
    GamePanel gp;
    private Rectangle solidArea = new Rectangle(0, 0, 40, 40);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    Player player;

    public SuperObject() {
        this.gp = GamePanel.getInstance();
        this.player = Player.getInstance();
    }

    public void draw(GraphicsContext gc) {
        int screenX = worldX - player.getWorldX() + player.getScreenX();
        int screenY = worldY - player.getWorldY() + player.getScreenY();

        if (worldX + gp.getTileSize() > player.getWorldX() - player.getScreenX() &&
                worldX - gp.getTileSize() < player.getWorldX() + player.getScreenX() &&
                worldY + gp.getTileSize() > player.getWorldY() - player.getScreenY() &&
                worldY - gp.getTileSize() < player.getWorldY() + player.getScreenY()) {
            gc.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize());
        }
    }

    @Override
    public int getZ() {
        return z;
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
}
