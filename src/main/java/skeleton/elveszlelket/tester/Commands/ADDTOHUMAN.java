package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

/**
 * Egy parancsot implementál, amely lehetővé teszi egy tárgy hozzáadását egy
 * diákhoz vagy tanárhoz.
 */
public class ADDTOHUMAN implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Végrehajtja a tárgy hozzáadását egy diákhoz vagy tanárhoz.
     *
     * @param params A parancs paraméterei, amelyek tartalmazzák a tárgy nevét és a
     *               diák/tanár nevét.
     * @param t      A Tester objektum, amely a parancsot végrehajtja, és
     *               tartalmazza a diákok, tanárok és tárgyak gyűjteményét.
     */
    public void execute(String[] params, Tester t) {
        if (params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }

        Item keresettItem = t.getItem(params[1]);
        // Ha keresettItem nem null, akkor létezik params[1] nevű tárgy.
        if (keresettItem != null) {

            if (keresettItem.getHuman() != null) {
                System.out.println("Parameterul kapott targy mar megtalalhato egy masik embernel.");
                return;
            }
            if (keresettItem.getRoom() != null) {
                System.out.println("Parameterul kapott targy mar megtalalhato egy masik szobaban.");
                return;
            }

            Teacher keresettOktato = t.getTeacher(params[2]); // hasonlóan ha keresettOktato nem null, akkor van már
                                                              // ilyen nevű
            Student keresettHallgato = t.getStudent(params[2]); // hasonlóan ha keresettHallgato nem null, akkor van már
                                                                // ilyen nevű

            if (keresettHallgato != null) {
                if (keresettHallgato.pickupItem(keresettItem)) {
                    keresettItem.setHuman(keresettHallgato);
                    System.out.println(params[1]+" sikeresen hozzaadva "+params[2]+" targyaihoz.");
                } else {
                    System.out.println(params[1]+" nem lett hozzaadva "+params[2]+" targyaihoz, leltar tele.");
                }
            } else if (keresettOktato != null) {
                if (keresettOktato.pickupItem(keresettItem)) {
                    keresettItem.setHuman(keresettOktato);
                    System.out.println(params[1]+" sikeresen hozzaadva "+params[2]+" targyaihoz.");
                } else {
                    System.out.println(params[1]+" nem lett hozzaadva "+params[2]+" targyaihoz, leltar tele.");
                }
            } else {
                System.out.println("Parameterul kapott "+params[2]+" nincs feljegyezve.");
            }
        } else {
            System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
        }
    }
}