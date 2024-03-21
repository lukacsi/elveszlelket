package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Player;
import strategy.ItemUseStrategy;

public abstract class Item {
    protected String name;
    protected ItemUseStrategy strategy;

    public Item() {
        
    }
    public abstract void use(Player player);
}
