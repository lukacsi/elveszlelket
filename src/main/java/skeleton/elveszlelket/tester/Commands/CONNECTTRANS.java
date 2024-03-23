package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Transistor;
import skeleton.elveszlelket.tester.Tester;

public class CONNECTTRANS implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Student keresettStudent = t.getStudent(params[1]); 
        // Ha keresettStudent nem null, akkor létezik params[1] nevű hallgató.
        if(keresettStudent != null) {
            Item keresettTransistor1 = t.getItem(params[2]);
            Item keresettTransistor2 = t.getItem(params[3]);

            if(keresettTransistor1 != null) {
                if(keresettTransistor2 != null) {
                    if(keresettTransistor1.getName().equals("Transistor") && keresettTransistor2.getName().equals("Transistor")) {
                        boolean vissza1 = t.askBoolean(params[2] + " parositott?");
                        boolean vissza2 = t.askBoolean(params[3] + " parositott?");
                        if(!vissza1 && !vissza2) {
                            // ez a kód elegge kerdeses hogy jo-e
                            // !!! + nincs ellenőrízve, hogy a két tranzisztor tényleg a hallgatónál van-e.
                            Transistor t1 = (Transistor)keresettTransistor1;
                            Transistor t2 = (Transistor)keresettTransistor2;
                            t1.addPair(t2);
                            t2.addPair(t1);
                            System.out.println("Tranzisztorok parosítva");
                        } else {
                            System.out.println("Tranzisztorok nem lettek parositva.");
                        }
                    } else {
                        System.out.println("Parameterkent kapott targyak valamelyike nem tranzisztor.");
                    }
                } else {
                    System.out.println("Parameterkent kapott masodik targy nincs meg feljegyezve.");
                }
            } else {
                System.out.println("Parameterkent kapott elso targy nincs meg feljegyezve.");
            }
        } else {
            System.out.println("Parameterkent kapott hallgato nincs meg feljegyezve.");
        }
    }
}