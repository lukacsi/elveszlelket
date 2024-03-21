package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Player;
import strategy.WinStrategy;

public class Logar extends Item {
    public Logar() {
        name = "Logar";
        strategy = new WinStrategy();
    }
    public void use(Player player) {
        
    }
}
