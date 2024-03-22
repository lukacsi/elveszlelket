package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.GasProtectionStrategy;

public class FFP2Mask extends ProtectionItem{
    public FFP2Mask() {
        name = "FFP2Mask";
        uses = 3;
        strategy = new GasProtectionStrategy();
    }
    public void use(Student student) {
        
    }
}
