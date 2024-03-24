package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.RagStrategy;

public class Rag extends ProtectionItem{
    public Rag() {
        name = "Rag";
        uses = 30;
        strategy = new RagStrategy();
    }
    public void use(Student student) {
        
    }
}
