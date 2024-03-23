package skeleton.elveszlelket.door;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;

public abstract class Door {
    
    /*
     * Beállítja az szobáit, inicializáláskor kell.
     * @param r1 Egyik szoba
     * @param r2 Második szoba
     * OneWayDoor esetén az r1-nek nincs jelentősége.
     */
    public void setRooms(Room r1, Room r2) {}

    /**
     * Eltűnteti az ajtót.
     */
    public void hide(){}

    /**
     * Megjeleníti az ajtót.
     */
    public void show(){}

    /**
     * Elfogadja az observer hallgatót.
     * @param s A hallgató.
     */
    public void accept(Student s){}

    /**
     * Elfogadja az observer oktatót.
     * @param t Az oktató.
     */
    public void accept(Teacher t){}

    /**
     * Átrakja a hallgatót a másik szobába.
     * @param s A hallgató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    public void putMeThrough(Student s){}

    /**
     * Átrakja az oktatót a másik szobába.
     * @param t Az oktató.
     * @return A művelet sikeressége: Igaz csakis akkor, ha sikeres.
     */
    public void putMeThrough(Teacher t){}


}
