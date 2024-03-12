package Weapon;

import interfacep.Sellable;
import javafx.scene.image.Image;

public abstract class BaseShield implements Sellable {
    protected Image image;
    protected int defenseValue=0;
    int price = 0;

    public int getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(int defenseValue) {
        this.defenseValue = defenseValue;
    }
    public Image getImage() {
        return image;
    }
    @Override
    public int getPrice() {
        return price;
    }
}
