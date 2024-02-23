package Weapon;

import javafx.scene.image.Image;

public abstract class BaseWeapon {
    protected Image image;
    protected int attackValue=0;
    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public Image getImage() {
        return image;
    }
}
