package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.DrunkStrategy;

public class Beer extends ProtectionItem {
    public Beer() {
        name = "Beer";
        uses = 1;
        strategy = new DrunkStrategy();
    }
    public void use(Student student) {
        
    }
}
