package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

public class CHECKLOSE implements skeleton.elveszlelket.tester.Commands.Command {
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

