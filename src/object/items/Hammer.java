package object.items;

import interfacep.Sellable;
import javafx.scene.image.Image;

public class Hammer extends Item implements Sellable {
    int repairHeath;
    public Hammer() {
        super();
        setRepairHeath(20);
        image = new Image(ClassLoader.getSystemResourceAsStream("image/objects/Hammer.png"));
        name = "Repairing Hammer";
        description = "["+name+"]\nuse for increase house HP\n *Can't be more than max HP";
    }

    public int getRepairHeath() {
        return repairHeath;
    }

    public void setRepairHeath(int repairHeath) {
        this.repairHeath = repairHeath;
    }

    @Override
    public int getPrice() {
        return 20;
    }
    public void use() {
        House.getInstance().setLife(House.getInstance().getLife()+getRepairHeath());
    }
}
