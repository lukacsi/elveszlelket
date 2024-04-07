package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;
import skeleton.elveszlelket.item.Item;

/**
 * A tárgy használatát végző parancs.
 */
public class USEITEM implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * A tárgy használatát végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza a hallgató nevét, aki használja a
     *               tárgyat.
     *               A params[2] tartalmazza a tárgy nevét, amelyet használni kell.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {
        if (params.length != 3) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }
        Student student = t.getStudent(params[1]);

        if (student == null) {
            System.out.println("Hallgato '" + params[1] + "' nincs feljegyezve.");
            return;
        }

        Item item = t.getItem(params[2]);

        if (item == null) {
            System.out.println("Targy '" + params[2] + "' nincs feljegyezve.");
            return;
        }

        if (student.getItems().contains(item)) {
            student.useItem(item);
            System.out.println("Targy '" + item.getName() + "' hasznalva lett: '" + t.getStudentName(student));
        } else {
            System.out.println("A targy nincs a hallgatonal.");
        }
    }
}
