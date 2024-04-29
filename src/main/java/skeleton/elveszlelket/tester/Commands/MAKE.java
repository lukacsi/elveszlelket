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

        if (params[1].equals("DOOR")) {
            System.out.println("DOOR mint parameter nem adhato meg, megadhato:\n - ONEWAYDOOR\n - TWOWAYDOOR");
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
            boolean foundType = true;
            if (params[1].equals("STUDENT")) {
                t.addStudent(params[2], new Student());
                t.itemUses.add(params[2]);
                t.movement.add(params[2]);
            } else if (params[1].equals("TEACHER")) {
                t.movement.add(params[2]);
                t.addTeacher(params[2], new Teacher());
            } else if (params[1].equals("CLEANINGLADY")) {
                t.movement.add(params[2]);
                t.addCleaningLady(params[2], new CleaningLady());
            } else if (params[1].equals("ROOM")) {
                t.addRoom(params[2], new Room());
            } else if (params[1].equals("ONEWAYDOOR")) {
                t.addDoor(params[2], new OneWayDoor());
            } else if (params[1].equals("TWOWAYDOOR")) {
                t.addDoor(params[2], new TwoWayDoor());
            } else if (params[1].equals("CAMEMBERT")) {
                t.addItem(params[2], new Camember());
            } else if (params[1].equals("FFP2MASK")) {
                t.addItem(params[2], new FFP2Mask());
            } else if (params[1].equals("BEER")) {
                t.addItem(params[2], new Beer());
            } else if (params[1].equals("RAG")) {
                t.addItem(params[2], new Rag());
            } else if (params[1].equals("TRANSISTOR")) {
                t.addItem(params[2], new Transistor());
            } else if (params[1].equals("TVSZ")) {
                t.addItem(params[2], new TVSZ());
            } else if (params[1].equals("LOGAR")) {
                t.addItem(params[2], new Logar());
            } else if (params[1].equals("AIRFRESHENER")) {
                t.addItem(params[2], new AirFreshener());
            }else{
                foundType = false;
            }
            if(!foundType){
                System.out.println("Objektum letrehozva: " + params[2] + " (Tipus: " + params[1]+ ")");
            }
        }
    }
}
