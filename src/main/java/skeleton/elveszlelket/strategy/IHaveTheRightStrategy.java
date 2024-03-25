package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class IHaveTheRightStrategy implements ItemUseStrategy {
    public void execute(Student student, Item item) {
        boolean result1 = App.t.askBoolean("TVSZ has uses left?");
        if(result1) {
            student.setImmunity(2);
            boolean result2 = App.t.askBoolean("Last use?");
            if(result2) {
                student.removeItem(item);
            }
        }

    }
}