package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.item.Item;

public class GasStrategy implements ItemUseStrategy{
    public void execute(Student student, Item item) {
        Room r = student.getRoom();
        boolean result = App.t.askBoolean("Is camembert unopened?");
        
        if (result) {
            student.dropItem(item);
            r.setGas();
        }
    }
}
