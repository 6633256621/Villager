package Weapon;

import interfacep.Sellable;
import javafx.scene.image.Image;

public abstract class BaseWeapon implements Sellable {
    int price =0;
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

    @Override
    public int getPrice() {
        return price;
    }
}
