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
            System.out.println("Item '" + params[1] + "' not found.");
            return;
        }

        Human human = t.getStudent(params[2]);
        if (human == null) {
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