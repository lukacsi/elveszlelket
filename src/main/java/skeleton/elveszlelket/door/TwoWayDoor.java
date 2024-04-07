package skeleton.elveszlelket.door;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;

public class TwoWayDoor extends Door{
    private Room firstRoom;
    private Room secoundRoom;
    
    public TwoWayDoor(){}

    @Override
    public Room getDest(Room r){
        Room result = firstRoom;
        if(result == r){
            result = secoundRoom;
        }
        return result;
    }

    /**
     * Elfogadja az observer hallgatót.
     * @param s A hallgató.
     */
    @Override
    public void accept(Student s){
        s.use(this);
    }

    /**
     * Elfogadja az observer oktatót.
     * @param t Az oktató.
     */
    @Override
    public void accept(Teacher t){
        t.use(this);
    }

    /**
     * Átrakja a hallgatót a másik szobába.
     * @param s A hallgató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    @Override
    public void putMeThrough(Student s){
        Room destination = firstRoom;
        if(destination == s.getRoom()){
            destination = secoundRoom;
        }
        boolean gasDest = destination.containsGas();
        if(gasDest){
            s.blockDoor();
        }
        //redundáns
        s.getRoom().removeHuman(s);
        //redundáns
        destination.addHuman(s);
        //ide kell
        s.setCurrentRoom(destination);
        s.iHaveArrived();
        //s.setCurrentRoom(destination);
    }
    
    /**
     * Átrakja az oktatót a másik szobába.
     * @param t Az oktató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    @Override
    public void putMeThrough(Teacher t){
        Room destination = firstRoom;
        if(destination == t.getRoom()){
            destination = secoundRoom;
        }
        t.getRoom().removeHuman(t);
        destination.addHuman(t);
        t.iHaveArrived();
        t.setCurrentRoom(destination);
    }

    /*
     * Beállítja az szobáit, inicializáláskor kell.
     * @param r1 Egyik szoba
     * @param r2 Második szoba
     */
    public void setRooms(Room r1, Room r2) {
        firstRoom = r1;
        secoundRoom = r2;
    }
}
