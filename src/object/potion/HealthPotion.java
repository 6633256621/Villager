package object.potion;

import config.Config;
import javafx.scene.image.Image;
import object.Thing;

public class HealthPotion extends Thing {
    public HealthPotion() {
        super();
        z=2;
        image = new Image(ClassLoader.getSystemResourceAsStream("items/healthpotion.png"));
        name ="Health Potion";
        price=5;
        description="["+name+"]\nFor healing";
        collision=false;
    }
    public HealthPotion(int x, int y) {
        super();
        z=2;
        name = "Health Potion";
        image = new Image(ClassLoader.getSystemResourceAsStream("items/healthpotion.png"));
        name ="Health Potion";
        price=5;
        description="["+name+"]\nFor healing";
        setWorldX(x * Config.tileSize);
        setWorldY(y * Config.tileSize);
        collision=false;
    }
}