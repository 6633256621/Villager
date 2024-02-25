package object;

import ent.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import panel.GamePanel;

public class Chest extends SuperObject{
    public int z =1;
    public Chest(double x,double y) {
        super();
        name="Chest";
        image = new Image(ClassLoader.getSystemResourceAsStream("objects/normal_chest.png"));
        setWorldX(x*gp.getTileSize());
        setWorldY(y*gp.getTileSize());
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
