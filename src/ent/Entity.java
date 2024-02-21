package ent;

import javafx.scene.canvas.GraphicsContext;
import render.Renderable;

public abstract class Entity implements Renderable {
    protected double x,y;
    protected int z;
    boolean visible;
    protected Entity() {
        visible=true;
    }
    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }
}
