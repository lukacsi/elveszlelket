package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import strategy.DrunkStrategy;

public class Beer extends ProtectionItem {
    public Beer(String name) {
        name = "Beer";
        uses = 1;
        strategy = new DrunkStrategy();
    }
    public void use(Student student) {
        
    }
}
