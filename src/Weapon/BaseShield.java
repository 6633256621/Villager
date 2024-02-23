package Weapon;

import javafx.scene.image.Image;

public abstract class BaseShield {
    protected Image image;
    protected int defenseValue=0;

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
    public Image getImage() {
        return image;
    }
}
