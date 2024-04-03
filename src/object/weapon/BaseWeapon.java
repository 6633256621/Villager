package object.weapon;

import javafx.scene.image.Image;
import object.OBJ;
import object.Thing;

public abstract class BaseWeapon extends Thing {
    int price =0;
    protected Image image;
    protected int attackValue=0;
    @Override
    public void use(int index) {
        getPlayer().getInventory().add(getPlayer().getCurrentWeapon());
        getPlayer().setCurrentWeapon((BaseWeapon) getPlayer().getInventory().get(index));
        getPlayer().getInventory().remove(index);
    }

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
