package skeleton.elveszlelket.tester.Commands;
import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.OneWayDoor;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.item.Beer;
import skeleton.elveszlelket.item.Camember;
import skeleton.elveszlelket.item.FFP2Mask;
import skeleton.elveszlelket.item.Logar;
import skeleton.elveszlelket.item.Rag;
import skeleton.elveszlelket.item.TVSZ;
import skeleton.elveszlelket.item.Transistor;
import skeleton.elveszlelket.tester.Tester;

public class Make implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        if(params[1].equals("STUDENT")) {
            t.addStudent(params[2], new Student());
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
        } else if(params[1].equals("TEACHER")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addTeacher(params[2], new Teacher());
        } else if(params[1].equals("ROOM")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addRoom(params[2], new Room());
        } else if(params[1].equals("ONEWAYDOOR")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addDoor(params[2], new OneWayDoor());
        } else if(params[1].equals("TWOWAYDOOR")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addDoor(params[2], new TwoWayDoor());
        } else if(params[1].equals("CAMEMBERT")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new Camember());
        } else if(params[1].equals("FFP2MASK")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new FFP2Mask());
        } else if(params[1].equals("BEER")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new Beer());
        } else if(params[1].equals("RAG")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new Rag());
        } else if(params[1].equals("TRANSISTOR")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new Transistor());
        } else if(params[1].equals("TVSZ")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new TVSZ());
        } else if(params[1].equals("LOGAR")) {
            System.out.println(params[1] + ": " + params[2] + " hozzaadva.");
            t.addItem(params[2], new Logar());
        } else if(params[1].equals("DOOR")) {
            System.out.println("DOOR mint parameter nem adhato meg, megadhato:\n - ONEWAYDOOR\n - TWOWAYDOOR");
        } 
    }
}
