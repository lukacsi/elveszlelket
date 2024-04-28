package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.door.OneWayDoor;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

/**
 * Az objektumok létrehozását végző parancs.
 */
public class MAKE implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Az objektumok létrehozását végző parancs végrehajtása.
     *
     * @param params A parancs paraméterei tömbként.
     *               A params[0] tartalmazza a parancs nevét.
     *               A params[1] tartalmazza az objektum típusát, amelyet létre kell
     *               hozni.
     *               A params[2] tartalmazza az objektum nevét.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {

        if (params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }

        Student s = t.getStudent(params[2]);
        Teacher teacher = t.getTeacher(params[2]);
        Room r = t.getRoom(params[2]);
        Item i = t.getItem(params[2]);
        Door d = t.getDoor(params[2]);
        if (s != null || d != null || r != null || i != null || teacher != null) {
            System.out.println(params[2] + " nevvel rendelkezo objektumot mar vett fel.");
        } else {
            if (params[1].equals("STUDENT")) {
                t.addStudent(params[2], new Student());
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            } else if (params[1].equals("TEACHER")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addTeacher(params[2], new Teacher());
            } else if (params[1].equals("CLEANINGLADY")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addCleaningLady(params[2], new CleaningLady());
            } else if (params[1].equals("ROOM")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addRoom(params[2], new Room());
            } else if (params[1].equals("ONEWAYDOOR")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addDoor(params[2], new OneWayDoor());
            } else if (params[1].equals("TWOWAYDOOR")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addDoor(params[2], new TwoWayDoor());
            } else if (params[1].equals("CAMEMBERT")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new Camember());
            } else if (params[1].equals("FFP2MASK")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new FFP2Mask());
            } else if (params[1].equals("BEER")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new Beer());
            } else if (params[1].equals("RAG")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new Rag());
            } else if (params[1].equals("TRANSISTOR")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new Transistor());
            } else if (params[1].equals("TVSZ")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new TVSZ());
            } else if (params[1].equals("LOGAR")) {
                System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
                t.addItem(params[2], new Logar());
            } else if (params[1].equals("AIRFRESHENER")) {
                System.out.println(params[1] + ": " + params[2] + " hozzáadva.");
                t.addItem(params[2], new AirFreshener());
            } else if (params[1].equals("DOOR")) {
                System.out.println("DOOR mint parameter nem adhato meg, megadhato:\n - ONEWAYDOOR\n - TWOWAYDOOR");
            }
        }
    }
}
