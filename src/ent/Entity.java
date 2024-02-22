package ent;
import render.Renderable;

public abstract class Entity implements Renderable {
    protected double x, y;//position
    protected int z;//the layer of entity
    boolean visible;//can we see it?


    //constructor
    protected Entity() {
        visible = true;
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
}
