package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Transistor;
import skeleton.elveszlelket.tester.Tester;

/**
 * Parancs a tranzisztorok összekapcsolására egy hallgatóval.
 */
public class CONNECTTRANS implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * A tranzisztorok összekapcsolását végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza a hallgató nevét.
     *               A params[2] és params[3] tartalmazza a két tranzisztor nevét,
     *               amelyeket össze kell kapcsolni.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {

        if (params.length != 4) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }

        Student keresettStudent = t.getStudent(params[1]);
        // Ha keresettStudent nem null, akkor létezik params[1] nevű hallgató.
        if (keresettStudent != null) {
            Item keresettTransistor1 = t.getItem(params[2]);
            Item keresettTransistor2 = t.getItem(params[3]);

            if (keresettTransistor1 != null) {
                if (keresettTransistor2 != null) {

                    if (keresettTransistor1.equals(keresettTransistor2)) {
                        System.out.println("Egy tranzisztort sajat magaval nem lehet parositani.");
                        return;
                    }

                    if (keresettTransistor1.getName().equals("Transistor")
                            && keresettTransistor2.getName().equals("Transistor")) {
                        Transistor t1 = (Transistor) keresettTransistor1;
                        Transistor t2 = (Transistor) keresettTransistor2;
                        if (keresettStudent.getItems().contains(keresettTransistor1)
                                && keresettStudent.getItems().contains(keresettTransistor2)) {
                            if (!t1.hasPair() && !t2.hasPair()) {
                                t1.addPair(t2);
                                t2.addPair(t1);
                                System.out.println(params[2]+" es "+params[3]+" osszekapcsolva "+params[1]+" tranzisztorai kozott.");
                            } else {
                                System.out.println(params[2]+" es "+params[3]+" nem lett osszekapcsolva "+params[1]+" tranzisztorai kozott. Mivel valamelyik tranzisztor mar paros.");
                            }
                        } else {
                            System.out.println("Megadott tranzisztorok valamelyike nincs a hallgatonal.");
                        }
                    } else {
                        System.out.println("Parameterkent kapott targyak valamelyike nem tranzisztor.");
                    }
                } else {
                    System.out.println("Parameterul kapott "+params[3]+" nincs feljegyezve.");
                }
            } else {
                System.out.println("Parameterul kapott "+params[2]+" nincs feljegyezve.");
            }
        } else {
            System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
        }
    }
}