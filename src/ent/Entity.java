package ent;
import interfacepackage.Renderable;
import javafx.scene.shape.Rectangle;

public abstract class Entity implements Renderable {
    //position
    protected int worldX, worldY;
    //order of drawing
    protected int z;
    //boolean for drawing or not
    protected boolean visible;//can we see it?
    protected boolean destroyed;//is dead?
    protected String direction;
    protected int speed = 3;//player speed
    protected int sideSpeed = (int) (speed * (Math.cos(Math.toRadians(45.0))));//speed when sidewalk
    //collision check
    protected Rectangle solidArea;
    protected int solidAreaDefaultX,solidAreaDefaultY;
    private boolean collisionOn=false;


    //constructor
    protected Entity() {
        visible = true;
        destroyed=false;
    }


    //getter
    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSideSpeed() {
        return sideSpeed;
    }

    public void setSideSpeed(int sideSpeed) {
        this.sideSpeed = sideSpeed;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}

