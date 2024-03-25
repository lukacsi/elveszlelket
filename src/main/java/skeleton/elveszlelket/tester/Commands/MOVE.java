package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.tester.Tester;


/**
 * Az ajtón való áthaladást végző parancs.
 */
public class MOVE implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Az ajtón való áthaladást végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza az ajtó nevét, amelyen át kell haladni.
     *               A params[2] tartalmazza a hallgató vagy tanár nevét, aki áthalad az ajtón.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs objektumokat.
     */
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