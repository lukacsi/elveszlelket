package skeleton.elveszlelket.tester.Commands;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

public class CHECKWIN implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        boolean win = true;
        for (Student hallgato : t.getStudents()) {
            if (!hallgato.hasLogar()) {
                win = false;
                break;
            }
        }
        if (win) {
            System.out.println("Gratulálok! Megnyerted a játékot!");
        } else {
            System.out.println("Keresd tovább a Logar tárgyat!");
        }
    }
}
