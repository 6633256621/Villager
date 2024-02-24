package ent;
import interfacepackage.Renderable;

public abstract class Entity implements Renderable {
    protected double worldX, worldY;//position
    protected int z;//the layer of entity
    protected boolean visible;//can we see it?
    protected boolean destroyed;//is dead?


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
}
