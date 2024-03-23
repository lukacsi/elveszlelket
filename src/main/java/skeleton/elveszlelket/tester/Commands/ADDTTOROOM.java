package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.tester.Tester;

public class ADDTTOROOM implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Teacher keresettTeacher = t.getTeacher(params[1]); 
        // Ha keresettTeacher nem null, akkor létezik params[1] nevű oktató.
        if(keresettTeacher != null) {
            Room keresettR = t.getRoom(params[2]); // hasonlóan ha keresettR nem null, akkor van már ilyen nevű
            // felvett szoba a testernél
            if(keresettR != null) {
                keresettR.addHuman(keresettTeacher);
                System.out.println("Sikeresen hozzaadta az oktatot a szobahoz.");
            } else {
                System.out.println("Parameterkent kapott szoba nincs meg feljegyezve.");
            }
        } else {
            System.out.println("Parameterkent kapott oktato nincs meg feljegyezve.");
        }
    }
}
