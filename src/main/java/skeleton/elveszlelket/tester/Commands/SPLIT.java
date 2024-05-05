package skeleton.elveszlelket.tester.Commands;
import skeleton.elveszlelket.*;
import skeleton.elveszlelket.tester.Tester;

/**
 * A szoba felosztását végző parancs.
 */
public class SPLIT implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * A szoba felosztását végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza a szoba nevét, amelyet fel kell
     *               osztani.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {

        if (params.length != 2) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }

        Room r1 = t.getRoom(params[1]);
        if (r1 != null) {
            boolean answer = r1.getStudents().isEmpty();
            if (answer) {
                answer = r1.getTeacher().isEmpty();
            }
            if (answer) {
                String base = "SplitedRoom";
                String elsoNev, masodikNev;
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
                t.getCommand("MAKE").execute(makeParams, t);
                Room egyik = t.getRoom(roomNev);
                elsoNev = roomNev;
                i = 0;
                while (t.getRoom(roomNev) != null) {
                    i++;
                    roomNev = base.concat(String.valueOf(i));
                    if (i > 1000) {
                        roomNev = String.valueOf(i) + base;
                    }
                }
                makeParams[2] = roomNev;
                masodikNev = roomNev;
                t.getCommand("MAKE").execute(makeParams, t);
                Room masodik = t.getRoom(roomNev);

                if (r1.containsGas()) {
                    float value = t.r.nextFloat();
                    if (value <= 0.5) {
                        egyik.setGas(true);
                    } else {
                        masodik.setGas(true);
                    }
                }
                if (r1.isCursed()) {
                    if (egyik.containsGas()) {
                        masodik.setCursed(true);
                    } else if (masodik.containsGas()) {
                        egyik.setCursed(true);
                    } else {
                        float value = t.r.nextFloat();
                        if (value <= 0.5) {
                            egyik.setCursed(true);
                        } else {
                            masodik.setCursed(true);
                        }
                    }
                }
                for (i = 0; i < r1.getItems().size(); i++) {
                    float value = t.r.nextFloat();
                    if (value <= 0.5) {
                        egyik.addItem(r1.getItems().get(i));
                    } else {
                        masodik.addItem(r1.getItems().get(i));
                    }
                }
                System.out.println(params[1] + " kettebontva. Uj szobak: " + elsoNev + " es " + masodikNev + ".");
            } else {
                System.out.println(params[1] + " lett szetbontva, tartozkodnak benne.");
            }
        } else {
            System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
        }
    }
}