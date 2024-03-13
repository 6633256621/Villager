package Weapon;

import javafx.scene.image.Image;

public class NewbieShield extends BaseShield{
    public NewbieShield() {
        setDefenseValue(1);
        image = new Image(ClassLoader.getSystemResourceAsStream("shield/newbieShield.png"));
        price = 10;
    }
}
