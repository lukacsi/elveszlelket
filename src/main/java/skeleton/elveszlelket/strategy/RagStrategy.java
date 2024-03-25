package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.App;

public class RagStrategy implements ItemUseStrategy {
    public void execute(Student student, Item item) {
        boolean result = App.t.askBoolean("Rag is wet?");
        if(result) {
            student.getRoom().stunTeachers();
        }
    }
    
}
