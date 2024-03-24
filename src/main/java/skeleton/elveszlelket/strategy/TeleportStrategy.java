package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Transistor;

public class TeleportStrategy implements ItemUseStrategy{
    public void execute(Student student, Item item) {
        Transistor t = (Transistor) item;
        if(!t.hasPair()) {
            System.out.println("Transistor has no pair");
            return;
        }
        Transistor tp = t.getPair();
        student.dropItem(item);
        t.unPair();
        tp.unPair();
        student.teleport(tp.getLocation());
    }
}
