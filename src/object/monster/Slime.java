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

public class Slime extends Entity {
    private Player player = Player.getInstance();
    private House house = House.getInstance();
    private boolean knockBack = false;
    private boolean added=false;
    //counter
    private int spriteCounter = 0;
    private int value=5;
    private int spriteNum = 1;
    private int defaultSpeed;
    private int knockBackCounter=0;

    //Character Attributes
    private int strength, attack, defense;

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
        defaultSpeed=3;
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

    private void setStatus() {
        //status
        setMaxLife(4);
        setLife(getMaxLife());
        setStrength(1);
        setAttack(1);
        setDefense(1);
    }

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
    private void setAction() {
        if (spriteCounter > 40) {
            spriteCounter = 0;
        } else {

            if (spriteCounter <= 10) {
                spriteNum = 1;
            } else if (spriteCounter > 10 && spriteCounter <= 20) {
                spriteNum = 2;
            } else if (spriteCounter > 20 && spriteCounter <= 30) {
                spriteNum = 3;
            } else if (spriteCounter > 30) {
                spriteNum = 4;
            }
        }

        if (spriteNum == 1) {
            def = slime_jump_1;
        }
        if (spriteNum == 2) {
            def = slime_jump_2;
        }
        if (spriteNum == 3) {
            def = slime_jump_3;
        }
        if (spriteNum == 4) {
            def = slime_jump_2;
        }
    }

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
    public void draw(GraphicsContext gc) {
        int screenX = worldX - player.getWorldX() + player.getScreenX();
        int screenY = worldY - player.getWorldY() + player.getScreenY();

        if (worldX + Config.tileSize > player.getWorldX() - player.getScreenX() &&
                worldX - Config.tileSize < player.getWorldX() + player.getScreenX() &&
                worldY + Config.tileSize > player.getWorldY() - player.getScreenY() &&
                worldY - Config.tileSize < player.getWorldY() + player.getScreenY()) {
            gc.drawImage(def, screenX, screenY, Config.tileSize, Config.tileSize);
        }

        if (hpBarOn) {
            drawHPBar(gc, screenX, screenY);

            hpBarCounter++;
            if (hpBarCounter > 600) {
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }
    }

    public void drawDead(GraphicsContext gc) {
        setDyingCounter(getDyingCounter() + 1);

        if (getDyingCounter() <= 10) {
            def = new Image(ClassLoader.getSystemResourceAsStream("monster/slime/slime_death1.png"));
        } else if (getDyingCounter() > 10 && getDyingCounter() <= 20) {
            def = new Image(ClassLoader.getSystemResourceAsStream("monster/slime/slime_death2.png"));
        } else if (getDyingCounter() > 20 && getDyingCounter() <= 30) {
            def = new Image(ClassLoader.getSystemResourceAsStream("monster/slime/slime_death3.png"));
        } else if (getDyingCounter() > 30) {
            setDying(false);
            setAlive(false);
        }
    }

    public void drawHPBar (GraphicsContext gc, int screenX, int screenY) {
        double oneScale = (double)Config.tileSize/getMaxLife();
        double hpBarValue = oneScale*getLife();

        gc.setFill(Color.BLACK);
        gc.fillRect(screenX-1, screenY - 16, Config.tileSize + 2, 12);

        gc.setFill(Color.RED);
        gc.fillRect(screenX, screenY - 15, hpBarValue, 10);
    }

    // getter & setter

    public int getStrength() {
        return strength;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isKnockBack() {
        return knockBack;
    }

    public void setKnockBack(boolean knockBack) {
        this.knockBack = knockBack;
    }
}