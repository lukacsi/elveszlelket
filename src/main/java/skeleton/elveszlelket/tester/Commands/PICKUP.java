package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Tester;

public class PICKUP implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {

        Item item = t.getItem(params[1]);
        
        if (item == null) {
            System.out.println("Item '" + params[1] + "' not found.");
            return;
        }

        Human human = t.getStudent(params[2]);
        if (human == null){
            human = t.getTeacher(params[2]);
        }

        if (human == null) {
            System.out.println("Human '" + params[2] + "' not found.");
            return;
        }

        boolean pickedUp = human.pickupItem(item);
        
        if (pickedUp) {
            System.out.println("Item '" + item.getName() + "' picked up '");
        } else {
            System.out.println("Failed to pick up item '" + item.getName());
        }
    }
}