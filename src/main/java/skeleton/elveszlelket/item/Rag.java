package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Player;
import strategy.RagStrategy;

public class Rag extends ProtectionItem{
    public Rag() {
        name = "Rag";
        uses = 30;
        strategy = new RagStrategy();
    }
    public void use(Player player) {
        
    }
}
