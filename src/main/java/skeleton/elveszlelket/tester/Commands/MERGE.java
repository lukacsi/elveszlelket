package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.tester.Tester;

/**
 * Két szoba összeolvasztását végző parancs.
 */
public class MERGE implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Két szoba összeolvasztását végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] és params[2] tartalmazza a két szoba nevét, amelyet
     *               össze kell olvasztani.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {

        if (params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }
        Room r1 = t.getRoom(params[1]);
        Room r2 = t.getRoom(params[2]);
        if (r1 != null && r2 != null) {
            if (r1 == r2) {
                System.out.println("Parameterul kapott szobak megegyeznek. Merge sikertelen.");
                return;
            }

            boolean answer = t.askBoolean("Van-e hallgato/oktato " + params[1] + " vagy " + params[2] + " szobaban?");
            if (!answer) {
                String base = "MergedRoom";
                String roomNev = base;
                int i = 0;
                while (t.getRoom(roomNev) != null) {
                    i++;
                    roomNev = base.concat(String.valueOf(i));
                    if (i > 1000) {
                        roomNev = String.valueOf(i) + base;
                    }
                }
                String[] makeParams = { "MAKE", "ROOM", roomNev };
                t.getCommand("MAKE").execute(params, t);
                Room uj = t.getRoom(roomNev);
                r1.merge(r2, uj);
                t.removeRoom(r2);
                t.removeRoom(r1);
                System.out.println("Szobak osszemergelodtek.");
            } else {
                System.out.println("Szobak nem mergelhetoek.");
            }
        } else {
            System.out.println("Parameterkent megadott szoba valamelyike meg nincs feljegyezve.");
        }
    }
}