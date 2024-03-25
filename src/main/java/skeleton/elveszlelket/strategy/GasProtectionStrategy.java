package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class GasProtectionStrategy implements ItemUseStrategy {
    public void execute(Student student, Item item) {
        boolean result = App.t.askBoolean("Mask has uses?");
        if (result) {
            student.setGasProtection(30);
        }
        
    }
}
