package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.tester.Tester;

public class TOGGLEGAS implements Command {

    @Override
    public void execute(String[] params, Tester t) {

        if (params.length != 3) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }

        Room r = t.getRoom(params[1]);
        if (r != null) {
            String ertek = params[2];
            if (ertek.equalsIgnoreCase("TRUE")) {
                r.setGas(true);
                System.out.println("A szoba " + params[1] + " elgazositva.");
            } else if (ertek.equalsIgnoreCase("FALSE")) {
                r.setGas(false);
                System.out.println("A szoba " + params[1] + " gaztalanitva.");
            } else {
                System.out.println(
                        "Parameterul kapott igazsag ertek ervenytelen. Csak true/false formatumok elfogadottak.");
            }
        } else {
            System.out.println("Parameterul kapott szoba nincs feljegyeze.");
        }
    }

}
