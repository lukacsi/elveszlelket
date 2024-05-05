package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

/**
 * Egy parancsot implementál, amely lehetővé teszi egy tárgy hozzáadását egy
 * megadott szobához.
 */
public class ADDITOROOM implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Végrehajtja a tárgy hozzáadását egy szobához.
     *
     * @param params A parancs paraméterei, amelyek tartalmazzák a tárgy nevét és a
     *               szoba nevét.
     * @param t      A Tester objektum, amely a parancsot végrehajtja, és
     *               tartalmazza a tárgyak és szobák gyűjteményét.
     */
    public void execute(String[] params, Tester t) {

        if (params.length != 3) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }

        Item keresettItem = t.getItem(params[1]);
        // Ha keresettItem nem null, akkor létezik params[1] nevű hallgató.
        if (keresettItem != null) {
            if (keresettItem.getHuman() != null) {
                System.out.println("Parameterul kapott targy mar megtalalhato egy masik embernel.");
                return;
            }
            if (keresettItem.getRoom() != null) {
                System.out.println("Parameterul kapott targy mar megtalalhato egy masik szobaban.");
                return;
            }

            Room keresettR = t.getRoom(params[2]); // hasonlóan ha keresettR nem null, akkor van már ilyen nevű
            // felvett szoba a testernél
            if (keresettR != null) {
                keresettR.addItem(keresettItem);
                keresettItem.setRoom(keresettR);
                System.out.println(params[1]+" sikeresen hozzaadva a "+params[2]+" targyaihoz.");
            } else {
                System.out.println("Parameterul kapott "+params[2]+" nincs feljegyezve.");
            }
        } else {
            System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
        }
    }
}