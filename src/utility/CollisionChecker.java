package utility;

import config.Config;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.GameLogic;
import object.Entity;
import object.House;
import object.Item;
import object.Player;
import object.monster.Slime;
import panel.GamePanel;

import java.util.ArrayList;

public class CollisionChecker {
    GamePanel gp;
    GameLogic gl;
    private Player player = Player.getInstance();

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
            if (gl.getGameObjectContainer().get(i) instanceof Item) {
                Item e = (Item) gl.getGameObjectContainer().get(i);
                if (gl.getGameObjectContainer().get(i) != null) {
                    entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
                    entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
                    e.getSolidArea().setX(e.getWorldX() + e.getSolidArea().getX());
                    e.getSolidArea().setY(e.getWorldY() + e.getSolidArea().getY());

                    switch (entity.getDirection()) {
                        case "up":
                            entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "down":
                            entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "left":
                            entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "right":
                            entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "upleft":
                            entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                            entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "downright":
                            entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                            entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "downleft":
                            entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                            entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                        case "upright":
                            entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                            entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                            if (entity.getSolidArea().getBoundsInParent().intersects(e.getSolidArea().getBoundsInParent())) {
                                if (e.isCollision()) {
                                    entity.setCollisionOn(true);
                                }
                                if (player) {
                                    index = i;
                                }
                                e.setInteracted(true);
                            }
                            break;
                    }
                }
                entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
                entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
                e.getSolidArea().setX(e.getSolidAreaDefaultX());
                e.getSolidArea().setY(e.getSolidAreaDefaultY());
            }
        }
        return index;
    }

    public ArrayList<Integer> checkSlime(Entity entity, ArrayList<Entity> target) {
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < target.size(); i++) {
            if (target.get(i) != null) {
                entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
                entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
                target.get(i).getSolidArea().setX(target.get(i).getWorldX() + target.get(i).getSolidArea().getX());
                target.get(i).getSolidArea().setY(target.get(i).getWorldY() + target.get(i).getSolidArea().getY());

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                        break;
                    case "down":
                        entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                        break;
                    case "left":
                        entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                        break;
                    case "right":
                        entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                        break;
                    case "upleft":
                        entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                        entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                        break;
                    case "downright":
                        entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                        entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                        break;
                    case "downleft":
                        entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                        entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                        break;
                    case "upright":
                        entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                        entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                        break;
                }

                if (entity.getSolidArea().getBoundsInParent().intersects(target.get(i).getSolidArea().getBoundsInParent())) {
                    if (target.get(i) != entity) {
                        entity.setCollisionOn(true);
                        index.add(i);
                    }
                }

                entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
                entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
                target.get(i).getSolidArea().setX(target.get(i).getSolidAreaDefaultX());
                target.get(i).getSolidArea().setY(target.get(i).getSolidAreaDefaultY());
            }
        }
        return index;
    }


    public boolean checkPlayer (Entity entity) {
        boolean contactPlayer = false;
        // Get entity's solid area
        entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
        entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
        // Get object's solid area
        player.getSolidArea().setX(player.getWorldX() + player.getSolidArea().getX());
        player.getSolidArea().setY(player.getWorldY() + player.getSolidArea().getY());

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                break;
            case "down":
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "left":
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                break;
            case "right":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                break;
            case "upleft":
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                break;
            case "downright":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "downleft":
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "upright":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                break;
        }

        if (entity.getSolidArea().getBoundsInParent().intersects(player.getSolidArea().getBoundsInParent())) {
            entity.setCollisionOn(true);
            contactPlayer = true;
        }

        entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
        entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
        player.getSolidArea().setX(player.getSolidAreaDefaultX());
        player.getSolidArea().setY(player.getSolidAreaDefaultY());

        return contactPlayer;
    }

    public boolean checkHouse (Entity entity, House house) {
        boolean contactHouse = false;

        entity.getSolidArea().setX(entity.getWorldX() + entity.getSolidArea().getX());
        entity.getSolidArea().setY(entity.getWorldY() + entity.getSolidArea().getY());
        house.getSolidArea().setX(house.getWorldX() + house.getSolidArea().getX());
        house.getSolidArea().setY(house.getWorldY() + house.getSolidArea().getY());

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                break;
            case "down":
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "left":
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                break;
            case "right":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                break;
            case "upleft":
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                break;
            case "downright":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "downleft":
                entity.getSolidArea().setX(entity.getSolidArea().getX() - entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() + entity.getSpeed());
                break;
            case "upright":
                entity.getSolidArea().setX(entity.getSolidArea().getX() + entity.getSpeed());
                entity.getSolidArea().setY(entity.getSolidArea().getY() - entity.getSpeed());
                break;
        }

        if (entity.getSolidArea().getBoundsInParent().intersects(house.getSolidArea().getBoundsInParent())) {
            entity.setCollisionOn(true);
            contactHouse = true;
        }

        entity.getSolidArea().setX(entity.getSolidAreaDefaultX());
        entity.getSolidArea().setY(entity.getSolidAreaDefaultY());
        house.getSolidArea().setX(house.getSolidAreaDefaultX());
        house.getSolidArea().setY(house.getSolidAreaDefaultY());

        return contactHouse;
    }
}
