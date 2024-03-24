package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

public class REMOVE implements skeleton.elveszlelket.tester.Commands.Command {
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