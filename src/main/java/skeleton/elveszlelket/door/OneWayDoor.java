package skeleton.elveszlelket.door;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;

public class OneWayDoor extends Door{
    
    private Room destination;
    
    public OneWayDoor(){}

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
        t.getRoom().removeHuman(t);
        destination.addHuman(t);
        t.iHaveArrived();
    }

    /**
     * Visszaadja, hogy a jó irányban akarják-e használni.
     * @param r A szoba melyből indul a használó.
     * @return Igaz csakis akkor, ha kiinduló szoba nem egyezik
     * azzal az egyetlen szobával, melybe el lehet jutni az ajtón át.
     */
    public boolean isRightDirection(Room r){
        boolean result = true;
        if(r == destination){
            result = false;
        }
        return result;
    }

        /*
     * Beállítja az szobáit, inicializáláskor kell.
     * @param r1 Egyik szoba - ennek most nincs jelentősége
     * @param r2 Második szoba
     */
    @Override
    public void setRooms(Room r1, Room r2) {
        destination = r2;
    }
}
