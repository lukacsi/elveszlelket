package skeleton.elveszlelket.door;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;

public class TwoWayDoor extends Door{
    private Room firsRoom;
    private Room secoundRoom;
    
    public TwoWayDoor(){}

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
        Room destination = firsRoom;
        if(destination == s.getRoom()){
            destination = secoundRoom;
        }
        boolean gasDest = destination.containsGas();
        if(gasDest){
            s.blockDoor();
        }
        s.getRoom().removeHuman(s);
        destination.addHuman(s);
        s.iHaveArrived();
    }
    
    /**
     * Átrakja az oktatót a másik szobába.
     * @param t Az oktató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    @Override
    public void putMeThrough(Teacher t){
        Room destination = firsRoom;
        if(destination == t.getRoom()){
            destination = secoundRoom;
        }
        t.getRoom().removeHuman(t);
        destination.addHuman(t);
        t.iHaveArrived();
    }
}
