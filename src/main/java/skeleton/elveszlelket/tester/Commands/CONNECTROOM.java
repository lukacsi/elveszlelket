package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.tester.Tester;

public class CONNECTROOM implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Door keresettDoor = t.getDoor(params[1]); 
        // Ha keresettDoor nem null, akkor létezik params[1] nevű ajtó.
        if(keresettDoor != null) {
            Room keresettRoom1 = t.getRoom(params[2]);
            Room keresettRoom2 = t.getRoom(params[3]);

            if(keresettRoom1 != null) {
                if(keresettRoom2 != null) {
                    keresettRoom1.addDoor(keresettDoor);
                    keresettRoom2.addDoor(keresettDoor);
                    keresettDoor.setRooms(keresettRoom1, keresettRoom2);
                    System.out.println("Sikeres connect.");
                } else {
                    System.out.println("Parameterkent kapott masodik szoba nincs meg feljegyezve.");
                }
            } else {
                System.out.println("Parameterkent kapott elso szoba nincs meg feljegyezve.");
            }
        } else {
            System.out.println("Parameterkent kapott ajto nincs meg feljegyezve.");
        }
    }
}