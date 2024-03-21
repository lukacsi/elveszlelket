package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Player;
import strategy.GasStrategy;

public class Camember extends Item {
    private Boolean opened;

    public Camember() {
        name = "Camembert";
        strategy = new GasStrategy();
        opened = false;
    }
    public void use(Player player) {
        
    }
}
