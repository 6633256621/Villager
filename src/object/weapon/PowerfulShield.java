package object.weapon;

import javafx.scene.image.Image;

public class PowerfulShield extends BaseShield{
    public PowerfulShield() {
        setDefenseValue(10);
        image = new Image(ClassLoader.getSystemResourceAsStream("shield/powerful_shield.png"));
        price = 50;
        name = "Powerful Shield";
        description = "["+name+"]\nMaybe better than\nof normal shield.";
    }
}