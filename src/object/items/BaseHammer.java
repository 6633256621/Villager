package object.items;

import interfacep.Sellable;

public abstract class BaseHammer extends Item implements Sellable {
    int repairHeath;
    public BaseHammer() {
        super();
    }

    public int getRepairHeath() {
        return repairHeath;
    }

    public void setRepairHeath(int repairHeath) {
        this.repairHeath = repairHeath;
    }

    public void use() {
        House.getInstance().setLife(House.getInstance().getLife()+getRepairHeath());
    }
}
