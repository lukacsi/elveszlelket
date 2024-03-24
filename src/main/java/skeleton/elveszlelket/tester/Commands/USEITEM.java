package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;
import skeleton.elveszlelket.item.Item;

public class USEITEM implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {

        Student student = t.getStudent(params[1]);
        
        if (student == null) {
            System.out.println("Student '" + params[1] + "' not found.");
            return;
        }

        Item item = t.getItem(params[2]);
        
        if (item == null) {
            System.out.println("Item '" + params[2] + "' not found.");
            return;
        }

        student.useItem(item);
        System.out.println("Item '" + item.getName() + "' used by student '");
    }
}
