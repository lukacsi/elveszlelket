package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

public class ADDITOROOM implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Item keresettItem = t.getItem(params[1]); 
        // Ha keresettItem nem null, akkor létezik params[1] nevű hallgató.
        if(keresettItem != null) {
            Room keresettR = t.getRoom(params[2]); // hasonlóan ha keresettR nem null, akkor van már ilyen nevű
            // felvett szoba a testernél
            if(keresettR != null) {
                keresettR.addItem(keresettItem);
                System.out.println("Sikeresen hozzaadta a targyat a szobahoz.");
            } else {
                System.out.println("Parameterkent kapott szoba nincs meg feljegyezve.");
            }
        } else {
            System.out.println("Parameterkent kapott targy nincs meg feljegyezve.");
        }
    }
}