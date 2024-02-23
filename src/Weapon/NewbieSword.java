package Weapon;

import javafx.scene.image.Image;

public class NewbieSword extends BaseWeapon {
    public NewbieSword() {
        setAttackValue(1);
        image = new Image(ClassLoader.getSystemResourceAsStream("newbieSword.png"));
    }
}
