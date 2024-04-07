package skeleton.elveszlelket.door;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;

public class TwoWayDoor extends Door {
    private Room firstRoom;
    private Room secondRoom;

    public TwoWayDoor() {
        ownerRoom = null;
        firstRoom = null;
        secondRoom = null;
    }

    /**
     * @return Szoba. Vissza adja, melyik (az eredeti szobán kívül) a másik
     *         feljegyzett
     *         szobája.
     */
    public Room getSecondRoom() {
        return secondRoom;
    }

    /**
     * Elfogadja az observer hallgatót.
     * 
     * @param s A hallgató.
     */
    @Override
    public void accept(Student s) {
        s.use(this);
    }

    /**
     * Elfogadja az observer oktatót.
     * 
     * @param t Az oktató.
     */
    @Override
    public void accept(Teacher t) {
        t.use(this);
    }

    /**
     * Átrakja a hallgatót a másik szobába.
     * 
     * @param s A hallgató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    @Override
    public void putMeThrough(Student s) {
        Room destination = firstRoom;
        if (destination == s.getRoom()) {
            destination = secondRoom;
        }
        boolean gasDest = destination.containsGas();
        if (gasDest) {
            s.blockDoor();
        }
        s.getRoom().removeHuman(s);
        destination.addHuman(s);
        s.iHaveArrived();
        s.setCurrentRoom(destination);
    }

    /**
     * Átrakja az oktatót a másik szobába.
     * 
     * @param t Az oktató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    @Override
    public void putMeThrough(Teacher t) {
        Room destination = firstRoom;
        if (destination == t.getRoom()) {
            destination = secondRoom;
        }
        t.getRoom().removeHuman(t);
        destination.addHuman(t);
        t.iHaveArrived();
        t.setCurrentRoom(destination);
    }

    /*
     * Beállítja az szobáit, inicializáláskor kell.
     * 
     * @param r1 Egyik szoba
     * 
     * @param r2 Második szoba
     */
    public void setRooms(Room r1, Room r2) {
        firstRoom = r1;
        ownerRoom = firstRoom;
        secondRoom = r2;
    }
}
