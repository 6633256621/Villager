package object;

import javafx.scene.canvas.GraphicsContext;
import panel.GamePanel;
import render.Renderable;

public abstract class Object implements Renderable {
    protected int z;
    protected boolean destroyed;//default is false
    protected boolean visible;
    protected GamePanel gp;
    @Override
    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    //@Override
    //public void draw() {
        //depend on that object
        //So I won't deploy on this abstract class
    //}

    @Override
    public boolean isVisible() {
        return visible;
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}