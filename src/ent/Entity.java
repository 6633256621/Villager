package ent;
import render.Renderable;

public abstract class Entity implements Renderable {
    protected double x, y;//position
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

}
