package ent;
import interfacepackage.Renderable;
import javafx.scene.shape.Rectangle;

public abstract class Entity implements Renderable {
    //position
    protected double worldX, worldY;
    //order of drawing
    protected int z;
    //boolean for drawing or not
    protected boolean visible;//can we see it?
    protected boolean destroyed;//is dead?
    //collision check
    protected Rectangle solidArea;
    protected double solidAreaDefaultX,solidAreaDefaultY;
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

    public double getWorldX() {
        return worldX;
    }

    public double getWorldY() {
        return worldY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public double getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public double getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }
}

