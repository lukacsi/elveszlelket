package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.tester.Tester;

public class ADDSTOROOM implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Student keresettStudent = t.getStudent(params[1]); 
        // Ha keresettStudent nem null, akkor létezik params[1] nevű hallgató.
        if(keresettStudent != null) {
            Room keresettR = t.getRoom(params[2]); // hasonlóan ha keresettR nem null, akkor van már ilyen nevű
            // felvett szoba a testernél
            if(keresettR != null) {
                keresettR.addHuman(keresettStudent);
                System.out.println("Sikeresen hozzaadta a hallgatot a szobahoz.");
            } else {
                System.out.println("Parameterkent kapott Szoba nincs meg feljegyezve.");
            }
        } else {
            System.out.println("Parameterkent kapott hallgato nincs meg feljegyezve.");
        }
    }
}
