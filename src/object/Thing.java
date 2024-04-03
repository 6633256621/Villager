package object;

import interfacep.Sellable;

public abstract class Thing extends Item implements Sellable {
    protected int price;
    public abstract void use(int index);

    @Override
    public int getPrice() {
        return price;
    }
}
