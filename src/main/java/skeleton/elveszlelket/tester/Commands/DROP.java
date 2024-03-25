package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Tester;

/**
 * A tárgy eldobását végző parancs.
 */
public class DROP implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * A tárgy eldobását végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza a tárgy nevét, amelyet el kell dobni.
     *               A params[2] tartalmazza a hallgató vagy tanár nevét, aki eldobja a tárgyat.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs objektumokat.
     */
    public void execute(String[] params, Tester t) {

        Item item = t.getItem(params[1]);

        if (item == null) {
            System.out.println("Item '" + params[1] + "' not found.");
            return;
        }

        Human human = t.getStudent(params[2]);
        if (human == null){
            human = t.getTeacher(params[2]);
        }
        
        if (human == null) {
            System.out.println("Human '" + params[2] + "' not found.");
            return;
        }

        if (human.dropItem(item)) {
            System.out.println("Item '" + item.getName() + "' dropped by human.");
        } else {
            System.out.println("Unable to drop item '" + item.getName() + "' for human .");
        }
    }
}