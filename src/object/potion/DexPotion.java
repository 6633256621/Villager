package object.potion;

import config.Status;
import javafx.scene.image.Image;
import object.Player;

public class DexPotion extends Potion{
    public DexPotion() {
        super();
        z=2;
        image = new Image(ClassLoader.getSystemResourceAsStream("items/blue_potion.png"));
        name ="DEX Potion";
        price=5;
        description="["+name+"]\nFor increase DEX";
        collision=false;
    }

    @Override
    public void use(int index, Player p) {
        Status.dexBuff=true;
    }
}
