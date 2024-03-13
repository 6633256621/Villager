package utility;

import interfacep.Sellable;

import java.util.ArrayList;

public class ShopUtility {
    public static int calculateAllItems(ArrayList<Sellable> Items) {
        int temp = 0;
        for (Sellable e : Items) {
            temp += e.getPrice();
        }
        return temp;
    }
}
