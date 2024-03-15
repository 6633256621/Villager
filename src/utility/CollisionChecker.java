package utility;

import config.Config;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.GameLogic;
import object.Entity;
import object.Item;
import panel.GamePanel;

public class CollisionChecker {
    GamePanel gp;
    GameLogic gl;

    public CollisionChecker(GamePanel gp, GameLogic gl) {
        this.gp = gp;
        this.gl = gl;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.getWorldX() + entity.getSolidArea().getX());
        int entityRightWorldX = (int) (entity.getWorldX() + entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
        int entityTopWorldY = (int) (entity.getWorldY() + entity.getSolidArea().getY());
        int entityBottomWorldY = (int) (entity.getWorldY() + entity.getSolidArea().getY() + entity.getSolidArea().getHeight());

        int entityLeftCol = entityLeftWorldX / Config.tileSize;
        int entityRightCol = entityRightWorldX / Config.tileSize;
        int entityTopRow = entityTopWorldY / Config.tileSize;
        int entityBottomRow = entityBottomWorldY / Config.tileSize;

        int tileNum1, tileNum2;
        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "upright":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Config.tileSize;
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "upleft":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Config.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "downright":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Config.tileSize;
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "downleft":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Config.tileSize;
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / Config.tileSize;
                tileNum1 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision() || gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        for (int i = 0; i < gl.getGameObjectContainer().size(); i++) {
            if(gl.getGameObjectContainer().get(i) instanceof Item){
                if (gl.getGameObjectContainer().get(i) != null) {
                    entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidAreaDefaultX());
                    entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidAreaDefaultY());
                    Item e = (Item) gl.getGameObjectContainer().get(i);
                    e.getSolidArea().setX(e.getWorldX() + e.getSolidArea().getX());
                    e.getSolidArea().setY(e.getWorldY() + e.getSolidArea().getY());

                    switch (entity.getDirection()) {
                        case "up":
                            entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                System.out.println("up collision");
                            }
                            break;
                        case "down":
                            entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                            break;
                        case "left":
                            entity.getSolidArea().setX(entity.getSolidArea().getY() - entity.getSpeed());
                            break;
                        case "right":
                            entity.getSolidArea().setX(entity.getSolidArea().getY() + entity.getSpeed());
                            break;
                    }
                }
                entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
                entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
                entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
                entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
            }
        }
        return index;
    }
}
