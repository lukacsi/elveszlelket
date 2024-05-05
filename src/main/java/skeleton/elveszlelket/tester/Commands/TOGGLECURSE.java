package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.tester.Tester;

public class TOGGLECURSE implements Command {

    @Override
    public void execute(String[] params, Tester t) {
        if (params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }

        Room r = t.getRoom(params[1]);
        if (r != null) {
            String ertek = params[2];
            if (ertek.equalsIgnoreCase("TRUE")) {
                r.setCursed(true);
                System.out.println(params[1] + " atkozottsaga bekapcsolva.");
            } else if (ertek.equalsIgnoreCase("FALSE")) {
                r.setCursed(false);
                System.out.println(params[1] + " atkozottsaga kikapcsolva.");
            } else {
                System.out.println("Parameterul kapott igazsag ertek ervenytelen. Csak true/false formatumok elfogadottak.");
            }
        } else {
            System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
        }
    }
}