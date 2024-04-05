package object;

import interfacep.Storable;
import logic.GameLogic;
import object.potion.HealthPotion;
import object.weapon.*;
import config.Config;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import panel.GamePanel;
import utility.InputUtility;

import java.util.ArrayList;

import static utility.LoadUtility.*;

public class Player extends Entity implements Storable {
    public final int screenX;
    public final int screenY;
    //counter
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int sideSpeed;

    //Character Attributes
    private int maxLife, life, strength, level, dex, attack, defense, exp, nextLevelExp, money;
    private BaseWeapon currentWeapon = new NewbieSword();
    private BaseShield currentShield = new NewbieShield();
    private ArrayList<Item> inventory;
    public static Player instance = new Player();
    public static Player getInstance() {
        return instance;
    }
    GamePanel gp = GamePanel.getInstance();
    GameLogic gl;
    private Image def;//display image at that moment
    public Player() {
        super();
        //where to spawn
        worldX = Config.tileSize * (23+Config.fixedPosition);
        worldY = Config.tileSize * (21+Config.fixedPosition);
        z=2;
        //where to draw
        screenX = Config.screenWidth / 2 - (Config.tileSize) / 2;
        screenY = Config.screenHeight / 2 - (Config.tileSize) / 2;
        direction="down";
        speed=3;
        sideSpeed=this.sidespeed(speed);
        def = down;
        playerLoad();
        setStatus();
        setRectangle();
        setItems();
    }
    private void setItems() {
        inventory = new ArrayList<>(20);
        inventory.add(currentWeapon);
        inventory.add(currentShield);
    }
    private void setRectangle() {
        solidArea = new Rectangle();
        solidArea.setX(8);
        solidArea.setY(16);
        solidArea.setWidth(32);
        solidArea.setHeight(32);
        solidAreaDefaultX = (int) solidArea.getX();
        solidAreaDefaultY = (int) solidArea.getY();
    }
    private void setStatus() {
        //status
        setLevel(1);
        setMaxLife(8);
        setLife(3);
        setStrength(1);
        setDex(1);
        setExp(0);
        setNextLevelExp(5);
        setMoney(100);
        setAttack(getAttack());
        setDefense(getDefense());
    }
//    private void pickUpObject(int i) {
//        if (inventory.size()!=Config.inventorySize) {
//            inventory.add()
//        }
//    }
    private void up() {
        if (spriteNum == 1) {
            def = up;
        }
        if (spriteNum == 2) {
            def = up2;
        }
        if (spriteNum == 3) {
            def = up3;
        }
        direction = "up";
        if (!isCollisionOn()) {
            worldY -= speed;
        }
    }

    private void down() {
        if (spriteNum == 1) {
            def = down;
        }
        if (spriteNum == 2) {
            def = down2;
        }
        if (spriteNum == 3) {
            def = down3;
        }
        direction = "down";
        if (!isCollisionOn()) {
            worldY += speed;
        }
    }

    private void left() {
        if (spriteNum == 1) {
            def = left;
        }
        if (spriteNum == 2) {
            def = left2;
        }
        if (spriteNum == 3) {
            def = left3;
        }
        direction = "left";
        if (!isCollisionOn()) {
            worldX -= speed;
        }
    }

    private void right() {
        if (spriteNum == 1) {
            def = right;
        }
        if (spriteNum == 2) {
            def = right2;
        }
        if (spriteNum == 3) {
            def = right3;
        }
        direction = "right";
        if (!isCollisionOn()) {
            worldX += speed;
        }
    }

    private void upleft() {
        if (spriteNum == 1) {
            def = upleft;
        }
        if (spriteNum == 2) {
            def = upleft2;
        }
        if (spriteNum == 3) {
            def = upleft3;
        }
        direction = "upleft";
        if (!isCollisionOn()) {
            worldX -= sideSpeed;
            worldY -= sideSpeed;
        }
    }

    private void upright() {
        if (spriteNum == 1) {
            def = upright;
        }
        if (spriteNum == 2) {
            def = upright2;
        }
        if (spriteNum == 3) {
            def = upright3;
        }
        direction = "upright";
        if (!isCollisionOn()) {
            worldX += sideSpeed;
            worldY -= sideSpeed;
        }
    }

    private void downright() {
        if (spriteNum == 1) {
            def = downright;
        }
        if (spriteNum == 2) {
            def = downright2;
        }
        if (spriteNum == 3) {
            def = downright3;
        }
        direction = "downright";
        if (!isCollisionOn()) {
            worldX += sideSpeed;
            worldY += sideSpeed;
        }
    }

    private void downleft() {
        if (spriteNum == 1) {
            def = downleft;
        }
        if (spriteNum == 2) {
            def = downleft2;
        }
        if (spriteNum == 3) {
            def = downleft3;
        }
        direction = "downleft";
        if (!isCollisionOn()) {
            worldX -= sideSpeed;
            worldY += sideSpeed;
        }
    }

    //fetch position
    public void update() {
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.A)) {
            upleft();
        } else if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.D)) {
            upright();
        } else if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.D)) {
            downright();
        } else if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.A)) {
            downleft();
        } else if (InputUtility.isKeyPressed(KeyCode.W)) {
            up();
        } else if (InputUtility.isKeyPressed(KeyCode.S)) {
            down();
        } else if (InputUtility.isKeyPressed(KeyCode.A)) {
            left();
        } else if (InputUtility.isKeyPressed(KeyCode.D)) {
            right();
        }
        setCollisionOn(false);
        gp.collisionChecker.checkTile(this);

        //if collision is false,player can move


        spriteCount();
    }

    //sprite counter so my player can walk
    private void spriteCount() {
        spriteCounter++;

        if (spriteCounter > 45) {
            spriteCounter = 0;
        } else {
            if (spriteCounter < 15) {
                spriteNum = 1;
            } else if (spriteCounter > 15 && spriteCounter <= 30) {
                spriteNum = 2;
            } else if (spriteCounter > 30) {
                spriteNum = 3;
            }
        }
    }



    //draw image
    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(def, screenX, screenY, Config.tileSize, Config.tileSize);
    }
    public int getMaxLife() {
        return maxLife;
    }

    public int getLife() {
        return life;
    }

    public int getStrength() {
        return strength;
    }

    public int getLevel() {
        return level;
    }

    public int getDex() {
        return dex;
    }

    public int getAttack() {
        return getStrength() * currentWeapon.getAttackValue();
    }

    public int getDefense() {
        return getDex() * currentShield.getDefenseValue();
    }

    public int getExp() {
        return exp;
    }

    public int getNextLevelExp() {
        return nextLevelExp;
    }

    public int getMoney() {
        return money;
    }

    public void setCurrentWeapon(BaseWeapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public void setCurrentShield(BaseShield currentShield) {
        this.currentShield = currentShield;
    }

    //setter
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public void setLife(int life) {
        this.life = Math.min(life,getMaxLife());
        this.life = Math.max(getLife(),0);
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setNextLevelExp(int nextLevelExp) {
        this.nextLevelExp = nextLevelExp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public BaseWeapon getCurrentWeapon() {
        return currentWeapon;
    }

    public BaseShield getCurrentShield() {
        return currentShield;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

}
