package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

public class ADDTOTEACHER implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Item keresettItem = t.getItem(params[1]); 
        // Ha keresettItem nem null, akkor létezik params[1] nevű tárgy.
        if(keresettItem != null) {
            Teacher keresettOktato = t.getTeacher(params[2]); // hasonlóan ha keresettOktato nem null, akkor van már ilyen nevű
            // felvett szoba a testernél
            if(keresettOktato != null) {
                if(keresettOktato.pickupItem(keresettItem)) {
                    System.out.println(params[1] + " tárgy felvétele sikeres.");
                } else {
                    System.out.println(params[1] + " tárgy felvétele sikertelen.");
                }
            } else {
                System.out.println("Parameterkent kapott oktato nincs meg feljegyezve.");
            } 
        } else {
            System.out.println("Parameterkent kapott targy nincs meg feljegyezve.");
        } 
    }
}