package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

/**
 * Egy parancsot implementál, amely ellenőrzi, hogy a játékban részt vevő összes diák halott-e.
 * Ha igen, a játék vesztes állapotba kerül.
 */
public class CHECKLOSE implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Ellenőrzi, hogy minden diák halott-e a szimulációban.
     * Ha minden diák halott, akkor jelzi, hogy a játék véget ért vesztes állapotban.
     * Ha van élő diák, akkor jelzi, hogy a játék folytatódhat.
     *
     * @param params A parancs paraméterei (nem használja).
     * @param t A Tester objektum, amely a diákok gyűjteményét tartalmazza.
     */
    public void execute(String[] params, Tester t) {
        boolean mindenkiHalott = true;
        for (Student hallgato : t.getStudents()) {
            if (!hallgato.halottE()) {
                mindenkiHalott = false;
                break;
            }
        }
        if (mindenkiHalott) {
            System.out.println("Minden hallgató halott. Játék vége!");
        } else {
            System.out.println("Néhány hallgató még mindig él. Folytasd a játékot!");
        }
    }
}

