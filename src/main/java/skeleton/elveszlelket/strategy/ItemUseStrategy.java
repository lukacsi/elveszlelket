package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public interface ItemUseStrategy {
    public void execute(Student student, Item item);

}
