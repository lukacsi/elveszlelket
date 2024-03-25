package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class DrunkStrategy implements ItemUseStrategy{
    public void execute(Student student, Item item) {
        boolean result = App.t.askBoolean("Beer has uses left?");

        if(result) {
            student.setImmunity(10);
            student.removeItem(item);
        }
    }
}
