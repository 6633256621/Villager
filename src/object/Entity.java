package object;

import javafx.scene.shape.Rectangle;

public abstract class Entity extends OBJ {
    protected int speed;
    protected Rectangle solidArea;
    protected Rectangle attackArea;

    protected int solidAreaDefaultX,solidAreaDefaultY;
    protected boolean collisionOn=false;
    protected boolean collision = false;
    protected String direction;
    protected boolean invincible = false;
    protected boolean hpBarOn = false;
    protected int invincibleCounter = 0;
    protected int hpBarCounter = 0;
    private int life;
    private int maxLife;

    //Constructor
    public Entity() {
        setVisible(true);
        setDestroyed(false);
    }

   //Getter and Setter

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
    public int sidespeed(int speed) {
        return (int) (speed * (Math.cos(Math.toRadians(45.0))));
    }

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public int getInvincibleCounter() {
        return invincibleCounter;
    }

    public void setInvincibleCounter(int invincibleCounter) {
        this.invincibleCounter = invincibleCounter;
    }

    public void update() {

    }
    public boolean isCollision() {
        return collision;
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = Math.min(life, getMaxLife());
        this.life = Math.max(getLife(), 0);
    }

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public boolean isHpBarOn() {
        return hpBarOn;
    }

    public void setHpBarOn(boolean hpBarOn) {
        this.hpBarOn = hpBarOn;
    }

    public int getHpBarCounter() {
        return hpBarCounter;
    }

    public void setHpBarCounter(int hpBarCounter) {
        this.hpBarCounter = hpBarCounter;
    }
}
