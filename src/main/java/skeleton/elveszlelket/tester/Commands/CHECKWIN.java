package skeleton.elveszlelket.tester.Commands;
import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.tester.Tester;

/**
 * Egy parancsot implementál, amely ellenőrzi, hogy a játékban részt vevő diákok megnyerték-e a játékot.
 * A nyerés feltétele, hogy minden diáknak rendelkeznie kell a Logar tárggyal.
 */
public class CHECKWIN implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Ellenőrzi, hogy minden diáknak van-e Logar tárgya a szimulációban.
     * Ha minden diáknak van, akkor a játék nyertes állapotba kerül.
     *
     * @param params A parancs paraméterei (nem használja).
     * @param t A Tester objektum, amely a diákok gyűjteményét tartalmazza.
     */
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
