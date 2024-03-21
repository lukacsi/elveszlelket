package strategy;

import skeleton.elveszlelket.Player;
import skeleton.elveszlelket.item.Item;

public interface ItemUseStrategy {
    public void execute(Player player, Item item);

}
