package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.CleaningLady;
import skeleton.elveszlelket.Room;
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
     *               A params[1] tartalmazza az eltávolítandó objektumot
     *               A params[2] tartalmazza az objektum nevét, amit elkell
     *               távolítani.
     * @param t      A Tester objektum, amely tartalmazza a szimulációs
     *               objektumokat.
     */
    public void execute(String[] params, Tester t) {

        if (params.length != 3) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }

        String objectType = params[1];
        String objectName = params[2];

        if (objectType.equalsIgnoreCase("STUDENT")) {
            Student studentToRemove = t.getStudent(objectName);
            if (studentToRemove != null) {
                t.removeStudent(objectName);
                System.out.println("A hallgató (" + objectName + ") sikeresen eltávolítva a játékból.");
            } else {
                System.out.println("A megadott hallgató nem található a játékban.");
            }
        } else if (objectType.equalsIgnoreCase("TEACHER")) {
            Teacher teacherToRemove = t.getTeacher(objectName);
            if (teacherToRemove != null) {
                t.removeTeacher(objectName);
                System.out.println("Az oktató (" + objectName + ") sikeresen eltávolítva a játékból.");
            } else {
                System.out.println("A megadott oktató nem található a játékban.");
            }
        } else if (objectType.equalsIgnoreCase("CLEANINGLADY")) {
            CleaningLady cleaningToRemove = t.getCleaningLady(objectName);
            if (cleaningToRemove != null) {
                t.removeCleaningLady(objectName);
                System.out.println("Az takarító (" + objectName + ") sikeresen eltávolítva a játékból.");
            } else {
                System.out.println("A megadott takarító nem található a játékban.");
            }
        } else if (objectType.equalsIgnoreCase("ROOM")) { // Az eltávolítandó objektum nem STUDENT/TEACHER
            Room roomToRemove = t.getRoom(objectName);
            if (roomToRemove != null) {
                if (roomToRemove.getStudents().isEmpty() && roomToRemove.getTeacher().isEmpty()) {
                    t.removeRoom(roomToRemove);
                    System.out.println("A szoba (" + objectName + ") sikeresen eltávolítva a játékból.");
                } else {
                    System.out.println("Torles nem sikerult, adott szobaban talalhato meg hallgato/oktato.");
                }
            } else {
                System.out.println("A megadott szoba nem található a játékban.");
            }
        } else if (objectType.equalsIgnoreCase("DOOR")) {
            Door doorToRemove = t.getDoor(objectName);
            if (doorToRemove != null) {
                doorToRemove.getOwnerRoom().removeDoor(doorToRemove);
                doorToRemove.getSecondRoom().removeDoor(doorToRemove);
                t.removeDoor(doorToRemove);
                System.out.println("Az ajto (" + objectName + ") sikeresen eltávolítva a játékból.");
            } else {
                System.out.println("A megadott ajto nem található a játékban.");
            }
        }
    }
}