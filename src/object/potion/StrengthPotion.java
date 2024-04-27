package object.potion;

import config.Status;
import javafx.scene.image.Image;
import object.Player;

public class StrengthPotion extends Potion{
    public StrengthPotion() {
        super();
        z=2;
        image = new Image(ClassLoader.getSystemResourceAsStream("items/orange_potion.png"));
        name ="Strength Potion";
        price=5;
        description="["+name+"]\nFor increase strength";
        collision=false;
    }

    @Override
    public void use(int index, Player p) {
        Status.strengthBuff=true;
    }
}