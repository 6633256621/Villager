package object.monster;

import javafx.scene.paint.Color;
import logic.GameLogic;
import object.Entity;
import object.House;
import object.OBJ;
import object.Player;
import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import panel.GamePanel;
import utility.LoadUtility;

import static utility.LoadUtility.*;

public abstract class Slime extends Entity {
    private Player player = Player.getInstance();
    private House house = House.getInstance();
    private boolean knockBack = false;
    private boolean added = false;
    //counter
    private int spriteCounter = 0;
    private int value=5;
    private int spriteNum = 1;
    private int defaultSpeed;
    private int knockBackCounter=0;

    //Character Attributes
    GamePanel gp = GamePanel.getInstance();
    GameLogic gl;

    //display image at that moment
    private Image def;

    public Slime(int x, int y) {
        super();
        //spawn position
        worldX = Config.tileSize * (x + Config.fixedPosition);
        worldY = Config.tileSize * (y + Config.fixedPosition);
        z = 2;
        direction = "down";
        defaultSpeed = 3;
        speed = defaultSpeed;
        setSideSpeed(speed);
        def = slime_jump_1;
        playerLoad();
        setStatus();
        setRectangle();
        LoadUtility.slimeLoad();
    }

    private void setRectangle() {
        // solid area
        solidArea = new Rectangle();
        solidArea.setX(0);
        solidArea.setY(2);
        solidArea.setWidth(30);
        solidArea.setHeight(28);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
    }

    public abstract void setStatus();

    private void up() {
        direction = "up";
        if (!isCollisionOn()) {
            worldY -= speed;
        }
    }

    private void down() {
        direction = "down";
        if (!isCollisionOn()) {
            worldY += speed;
        }
    }

    private void left() {
        direction = "left";
        if (!isCollisionOn()) {
            worldX -= speed;
        }
    }

    private void right() {
        direction = "right";
        if (!isCollisionOn()) {
            worldX += speed;
        }
    }

    private void upleft() {
        direction = "upleft";
        if (!isCollisionOn()) {
            worldX -= sideSpeed;
            worldY -= sideSpeed;
        }
    }

    private void upright() {
        direction = "upright";
        if (!isCollisionOn()) {
            worldX += sideSpeed;
            worldY -= sideSpeed;
        }
    }

    private void downright() {
        direction = "downright";
        if (!isCollisionOn()) {
            worldX += sideSpeed;
            worldY += sideSpeed;
        }
    }

    private void downleft() {
        direction = "downleft";
        if (!isCollisionOn()) {
            worldX -= sideSpeed;
            worldY += sideSpeed;
        }
    }

    private void follow(OBJ obj) {
        if (obj.getWorldY() - worldY > 0&&obj.getWorldX() - worldX < 0) {
            downleft();
        }
        else if (obj.getWorldY() - worldY < 0&&obj.getWorldX() - worldX < 0) {
            upleft();
        }
        else if (obj.getWorldX() - worldX < 0&&obj.getWorldY() - worldY > 0) {
            downright();
        }
        else if (obj.getWorldX() - worldX > 0&&obj.getWorldY() - worldY < 0) {
            upright();
        }
        else if (obj.getWorldY() - worldY > 0) {
            down();
        }
        else if (obj.getWorldY() - worldY < 0) {
            up();
        }
        else if (obj.getWorldX() - worldX < 0) {
            left();
        }
        else if (obj.getWorldX() - worldX > 0) {
            right();
        }
    }


    // set bouncing slime
    public abstract void setAction();

    // fetch house position
    public void update() {
        if (knockBack) {
                switch (player.getDirection()) {
                    case "up" : worldY-=speed; break;
                    case "down" : worldY+=speed; break;
                    case "left" : worldX-=speed; break;
                    case "right" : worldX+=speed; break;
                    case "upright" : worldY-=speed; worldX+=speed; break;
                    case "upleft" : worldY-=speed; worldX-=speed; break;
                    case "downleft" : worldY+=speed; worldX-=speed; break;
                    case "downright" : worldY+=speed; worldX+=speed; break;
                }
            knockBackCounter++;
            if (knockBackCounter==10) {
                knockBackCounter=0;
                knockBack=false;
                speed=defaultSpeed;
                setSideSpeed(speed);
            }
        }
        else {
            if (isDying()) {
                drawDead(gp.getGc());
                if (!added) {
                    player.setMoney(player.getMoney()+this.value);
                    added=true;
                }
            } else {
                spriteCounter++;
                setAction();
            }
            gp.collisionChecker.checkTile(this);

            // calculate distance from player to slime
            double playerDist = Math.pow((player.getWorldY() - worldY), 2) + Math.pow((player.getWorldX() - worldX), 2);
            playerDist = Math.sqrt(playerDist);
            double houseDist = Math.pow((house.getWorldY() - worldY), 2) + Math.pow((house.getWorldX() - worldX), 2);
            houseDist = Math.sqrt(houseDist);

            if (playerDist <= 300 || houseDist > playerDist) {
                follow(player);
            } else {
                follow(house);
            }

            setCollisionOn(false);
        }
    }

    //draw image
    @Override
    public abstract void draw(GraphicsContext gc);

    public abstract void drawDead(GraphicsContext gc);

    public void drawHPBar (GraphicsContext gc, int screenX, int screenY) {
        double oneScale = (double)Config.tileSize/getMaxLife();
        double hpBarValue = oneScale*getLife();

        gc.setFill(Color.BLACK);
        gc.fillRect(screenX-1, screenY - 16, Config.tileSize + 2, 12);

        gc.setFill(Color.RED);
        gc.fillRect(screenX, screenY - 15, hpBarValue, 10);
    }

    // getter & setter
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isKnockBack() {
        return knockBack;
    }

    public void setKnockBack(boolean knockBack) {
        this.knockBack = knockBack;
    }

    public Image getDef() {
        return def;
    }

    public void setDef(Image def) {
        this.def = def;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}