package object.weapon;

import javafx.scene.image.Image;

public class NewbieShield extends BaseShield{
    public NewbieShield() {
        setDefenseValue(1);
        image = new Image(ClassLoader.getSystemResourceAsStream("shield/newbie_shield.png"));
        price = 10;
        name = "Newbie Shield";
        description = "["+name+"]\nSilly ass shield.";
    }
}
