package render;

import javafx.scene.canvas.GraphicsContext;

public interface Renderable {
    public int getZ();
    public int draw(GraphicsContext gc);
    public boolean isVisible();
}
