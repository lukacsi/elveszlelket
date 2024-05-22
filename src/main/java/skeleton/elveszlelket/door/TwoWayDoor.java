package skeleton.elveszlelket.door;

import skeleton.elveszlelket.CleaningLady;
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
    @Override
    public Room getSecondRoom() {
        return secondRoom;
    }

    @Override
    public Room getDest(Room r) {
        Room result = firstRoom;
        if (result == r) {
            result = secondRoom;
        }
        return result;
    }

    /**
     * Elfogadja az observer hallgatót.
     * 
     * @param s A hallgató.
     */
    @Override
    public boolean accept(Student s) {
        return s.use(this);
    }

    /**
     * Elfogadja az observer oktatót.
     * 
     * @param t Az oktató.
     */
    @Override
    public boolean accept(Teacher t) {
        return t.use(this);
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
        s.setLastDoor(this);
        s.setCurrentRoom(destination);
        s.iHaveArrived();
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
        t.setCurrentRoom(destination);
        t.iHaveArrived();
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

    @Override
    public boolean accept(CleaningLady c) {
        return c.use(this);
    }

    @Override
    public void putMeThrough(CleaningLady c) {
        Room destination = firstRoom;
        if (destination == c.getRoom()) {
            destination = secondRoom;
        }
        c.getRoom().removeHuman(c);
        destination.addHuman(c);
        c.setLastDoor(this);
        c.iHaveArrived();
    }
}
