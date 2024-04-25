package skeleton.elveszlelket.tester.Commands;

import java.util.ArrayList;
import java.util.Collection;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.tester.Tester;

public class INCREMENTTIME implements Command {

    @Override
    public void execute(String[] params, Tester t) {
        if(params.length != 2) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }
        int timeI = Integer.parseInt(params[1]);
        Collection<Student> students = t.getStudents();
        Collection<Teacher> teachers = t.getTeachers();
        for (Teacher teacher : teachers) {
            teacher.decreaseStun(timeI);
        }
        for (Student student : students) {
            for(int i = 0; i < timeI; i++) {
                student.decreaseGasProtection();
                student.decreaseImmunity();
            }
            student.decreaseStun(timeI);
        }
        System.out.println("Idő előrehaladva " + timeI + " -el!");
    }
    
}
