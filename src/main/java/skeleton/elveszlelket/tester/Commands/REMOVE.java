package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

/**
 * Az ember eltávolítását végző parancs.
 */
public class REMOVE implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Az ember eltávolítását végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza az ember típusát ('STUDENT' vagy 'TEACHER').
     *               A params[2] tartalmazza az ember nevét, akit eltávolítani kell.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs objektumokat.
     */
    public void execute(String[] params, Tester t) {
        String humanType = params[1];
        String humanName = params[2];
        
        if (humanType.equalsIgnoreCase("STUDENT")) {
            Student studentToRemove = t.getStudent(humanName);
            if (studentToRemove != null) {
                t.removeStudent(humanName);
                System.out.println("A hallgató (" + humanName + ") sikeresen eltávolítva a játékból.");
            } else {
                System.out.println("A megadott hallgató nem található a játékban.");
            }
        } else if (humanType.equalsIgnoreCase("TEACHER")) {
            Teacher teacherToRemove = t.getTeacher(humanName);
            if (teacherToRemove != null) {
                t.removeTeacher(humanName);
                System.out.println("Az oktató (" + humanName + ") sikeresen eltávolítva a játékból.");
            } else {
                System.out.println("A megadott oktató nem található a játékban.");
            }
        } else {
            System.out.println("Ismeretlen ember típus. Használja 'STUDENT' vagy 'TEACHER'.");
        }
    }
}