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
    public void update() {

    }
    public boolean isCollision() {
        return collision;
    }

}
