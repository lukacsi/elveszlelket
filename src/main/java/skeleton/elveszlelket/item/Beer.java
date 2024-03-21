package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Player;
import strategy.DrunkStrategy;

public class Beer extends ProtectionItem {
    public Beer(String name) {
        name = "Beer";
        uses = 1;
        strategy = new DrunkStrategy();
    }
    public void use(Player player) {
        
    }
}
