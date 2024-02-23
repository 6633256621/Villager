package ent;

import Weapon.BaseShield;
import Weapon.BaseWeapon;
import Weapon.NewbieShield;
import Weapon.NewbieSword;
import utility.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import panel.GamePanel;
import javafx.scene.image.Image;

import static utility.LoadUtility.*;

public class Player extends Entity {
    //counter
    private int spriteCounter = 0;
    private int spriteNum = 1;

    //Character Attributes
    private double speed = 2;//player speed
    private double sideSpeed = speed * (Math.cos(Math.toRadians(45.0)));//speed when sidewalk
    private int maxLife, life, strength, level, dex, attack, defense, exp, nextLevelExp, money;
    private BaseWeapon currentWeapon = new NewbieSword();
    private BaseShield currentShield = new NewbieShield();

    public static Player instance = new Player();

    public static Player getInstance() {
        return instance;
    }

    private Image def;//display image at that moment

    //constructor
    public Player() {
        super();
        x = 355;
        y = 250;
        z = 2;
        def = down;
        playerLoad();

        //status
        setLevel(1);
        setMaxLife(3);
        setLife(getMaxLife());
        setStrength(1);
        setDex(1);
        setExp(0);
        setNextLevelExp(5);
        setMoney(0);
        setAttack(getAttack());
        setDefense(getDefense());
    }

    //movement
    private void up() {
        y -= speed;
        if (spriteNum == 1) {
            def = up;
        }
        if (spriteNum == 2) {
            def = up2;
        }
        if (spriteNum == 3) {
            def = up3;
        }
    }

    private void down() {
        y += speed;
        if (spriteNum == 1) {
            def = down;
        }
        if (spriteNum == 2) {
            def = down2;
        }
        if (spriteNum == 3) {
            def = down3;
        }
    }

    private void left() {
        x -= speed;
        if (spriteNum == 1) {
            def = left;
        }
        if (spriteNum == 2) {
            def = left2;
        }
        if (spriteNum == 3) {
            def = left3;
        }
    }

    private void right() {
        x += speed;
        if (spriteNum == 1) {
            def = right;
        }
        if (spriteNum == 2) {
            def = right2;
        }
        if (spriteNum == 3) {
            def = right3;
        }
    }

    private void upleft() {
        x -= sideSpeed;
        y -= sideSpeed;
        if (spriteNum == 1) {
            def = upleft;
        }
        if (spriteNum == 2) {
            def = upleft2;
        }
        if (spriteNum == 3) {
            def = upleft3;
        }
    }

    private void upright() {
        x += sideSpeed;
        y -= sideSpeed;
        if (spriteNum == 1) {
            def = upright;
        }
        if (spriteNum == 2) {
            def = upright2;
        }
        if (spriteNum == 3) {
            def = upright3;
        }
    }

    private void downright() {
        x += sideSpeed;
        y += sideSpeed;
        if (spriteNum == 1) {
            def = downright;
        }
        if (spriteNum == 2) {
            def = downright2;
        }
        if (spriteNum == 3) {
            def = downright3;
        }
    }

    private void downleft() {
        x -= sideSpeed;
        y += sideSpeed;
        if (spriteNum == 1) {
            def = downleft;
        }
        if (spriteNum == 2) {
            def = downleft2;
        }
        if (spriteNum == 3) {
            def = downleft3;
        }
    }

    //fetch position
    public void update() {
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isOnlyOneKeyPressed()) {
            up();
        }
        if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isOnlyOneKeyPressed()) {
            down();
        }
        if (InputUtility.isKeyPressed(KeyCode.A) && InputUtility.isOnlyOneKeyPressed()) {
            left();
        }
        if (InputUtility.isKeyPressed(KeyCode.D) && InputUtility.isOnlyOneKeyPressed()) {
            right();
        }
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.A)) {
            upleft();
        }
        if (InputUtility.isKeyPressed(KeyCode.W) && InputUtility.isKeyPressed(KeyCode.D)) {
            upright();
        }
        if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.D)) {
            downright();
        }
        if (InputUtility.isKeyPressed(KeyCode.S) && InputUtility.isKeyPressed(KeyCode.A)) {
            downleft();
        }
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
        gc.drawImage(def, x, y, GamePanel.getInstance().getTileSize(), GamePanel.getInstance().getTileSize());
    }

    public double getSpeed() {
        return speed;
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


    //setter
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public void setLife(int life) {
        this.life = life;
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
}
