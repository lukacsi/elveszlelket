package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.tester.Tester;

public class MOVE implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Door keresettDoor = t.getDoor(params[1]); 
        // Ha keresettDoor nem null, akkor létezik params[1] nevű ajtó.
        if(keresettDoor != null) {
            Student keresettStudent = t.getStudent(params[2]);
            Teacher keresettTeacher = t.getTeacher(params[2]);

            if(keresettStudent != null) {
                keresettDoor.accept(keresettStudent);
            } else if(keresettTeacher != null) {
                keresettDoor.accept(keresettTeacher);
            }
            else {
                System.out.println("Parameterkent kapott human entitas nincs meg feljegyezve.");
            }
        } else {
            System.out.println("Parameterkent kapott ajto nincs meg feljegyezve.");
        }
    }
}