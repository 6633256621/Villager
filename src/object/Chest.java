package object;

import config.Config;
import javafx.scene.image.Image;

public class Chest extends Item {
    public Chest(int x, int y) {
        super();
        z=1;
        name = "Chest";
        image = new Image(ClassLoader.getSystemResourceAsStream("objects/normal_chest.png"));
        setWorldX(x * Config.tileSize);
        setWorldY(y * Config.tileSize);
    }

    @Override
    public boolean isVisible() {
        return true;
    }
}
