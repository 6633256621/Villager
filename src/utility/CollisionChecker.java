package utility;

import config.Config;
import object.Entity;
import panel.GamePanel;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = (int) (entity.getWorldX()+entity.getSolidArea().getX());
        int entityRightWorldX = (int) (entity.getWorldX() + entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
        int entityTopWorldY = (int) (entity.getWorldY() + entity.getSolidArea().getY());
        int entityBottomWorldY = (int) (entity.getWorldY() + entity.getSolidArea().getY() + entity.getSolidArea().getHeight());
        
        int entityLeftCol = entityLeftWorldX/ Config.tileSize;
        int entityRightCol = entityRightWorldX/Config.tileSize;
        int entityTopRow = entityTopWorldY/Config.tileSize;
        int entityBottomRow = entityBottomWorldY/Config.tileSize;
        
        int tileNum1,tileNum2;
        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY-entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY+entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX-entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX+entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "upright":
                entityTopRow = (entityTopWorldY-entity.getSpeed())/Config.tileSize;
                entityRightCol = (entityRightWorldX+entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "upleft":
                entityTopRow = (entityTopWorldY-entity.getSpeed())/Config.tileSize;
                entityLeftCol = (entityLeftWorldX-entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityTopRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "downright":
                entityBottomRow = (entityBottomWorldY+entity.getSpeed())/Config.tileSize;
                entityRightCol = (entityRightWorldX+entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityRightCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "downleft":
                entityBottomRow = (entityBottomWorldY+entity.getSpeed())/Config.tileSize;
                entityLeftCol = (entityLeftWorldX-entity.getSpeed())/Config.tileSize;
                tileNum1=gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileManager().getMapTileNum()[entityLeftCol][entityBottomRow];
                if (gp.getTileManager().getTile()[tileNum1].isCollision()||gp.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
}
