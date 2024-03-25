package skeleton.elveszlelket.tester.Commands;
import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

public class CHECKWIN implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        for (Student hallgato : t.getStudents()) {
            boolean result = App.t.askBoolean(App.t.getStudentReturnName(hallgato) + ": has i obtained logar?");
            if(result) {
                System.out.println("A hallgatok megtalaltak a logart, gyozelem!");
                return;
            }
        }
        System.out.println("A hallgatok meg nem talaltak ra a logarra.");
    }
}
