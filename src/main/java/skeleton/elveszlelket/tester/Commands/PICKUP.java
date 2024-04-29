package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Tester;

/**
 * A tárgy felvételét végző parancs.
 */
public class PICKUP implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * A tárgy felvételét végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza a tárgy nevét, amelyet fel kell venni.
     *               A params[2] tartalmazza a hallgató vagy tanár nevét, aki
     *               felveszi a tárgyat.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {
        if (params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }
        Item item = t.getItem(params[1]);

        if (item == null) {
            System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
            return;
        }

        Human human = t.getStudent(params[2]);
        if (human == null) {
            human = t.getTeacher(params[2]);
        }

        if (human == null) {
            System.out.println("Parameterul kapott "+params[2]+" nincs feljegyezve.");
            return;
        }
        
        boolean pickedUp = human.pickupItem(item);

        if (pickedUp) {
            System.out.println(params[1]+" felveve "+params[2]+" altal.");
        } else {
            System.out.println(params[1]+" nem lett felveve "+params[2]+" altal. Tele a leltar.");
        }
    }
}