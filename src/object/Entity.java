package object;

import javafx.scene.shape.Rectangle;

public abstract class Entity extends OBJ {
    protected int speed,
            sideSpeed;
    private int strength, attack, defense;
    protected Rectangle solidArea;
    protected Rectangle attackArea;

    protected int solidAreaDefaultX,solidAreaDefaultY;
    protected boolean collisionOn=false;
    protected boolean collision = false;
    protected String direction;
    protected boolean invincible = false;
    protected boolean hpBarOn = false;
    protected boolean alive = true;
    private boolean dying = false;
    protected int invincibleCounter = 0;
    protected int hpBarCounter = 0;
    protected int dyingCounter = 0;
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
    public void setSideSpeed(int speed) {
        int sideSpeed =(int) (speed * (Math.cos(Math.toRadians(45.0))));
        if (sideSpeed==0) {
            sideSpeed=1;
        }
        this.sideSpeed=sideSpeed;
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public int getDyingCounter() {
        return dyingCounter;
    }

    public void setDyingCounter(int dyingCounter) {
        this.dyingCounter = dyingCounter;
    }

    public int getSideSpeed() {
        return sideSpeed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
