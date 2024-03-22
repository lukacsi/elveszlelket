package skeleton.elveszlelket;

import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.item.Item;

public class Room {

    private List<Teacher> teachers;
    private List<Student> students;
    private List<Item> items;
    private List<Door> doors;

    /**
     * Alapkonstruktor a szobához.
     */
    public Room(){
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        items = new ArrayList<>();
        doors = new ArrayList<>();
    }

    /**
     * Hallgatót ad a szobához.
     * @param h A hozzáadandő hallgató.
     */
    public void addHuman(Human h){
        //értesítés
    }

    /**
     * Elvesz egy embert a szobából.
     * @param h A törlendő ember.
     */
    public void removeHuman(Human h){// nem lesz ez így jó szerintem (külön student és teacher kell)
        //értesítés
    }

    /**
     * Visszaadja, hogy gázos-e a szoba.
     * @return Igaz csakis akkor, ha gázos.
     */
    public boolean containsGas(){
        boolean result = false;
        //kérdés a teszternek
        return result;
    }

    /**
     * Visszaadja, hogy van-e szabad hely a szobában.
     * @return Igaz csakis akkor, ha van hely.
     */
    public boolean hasFreePlace(){
        boolean result = false;
        //kérdés a teszternek
        return result;
    }

    /**
     * Összevon két szobát.
     * @param r1 Az egyik összevonandó szoba.
     * @param r2 A másik összevonandó szoba.
     * @return Az összevonás során keletkező új szoba.
     */
    public Room merge(Room r1, Room r2){
        Room result = new Room();
        return result;
    }

    /**
     * Kettéoszt egy szobát.
     * @param r1 ?
     * @param r2 ?
     * @return Az osztás során eletkező két új szoba.
     */
    public List<Room> split(Room r1, Room r2){
        List result = new ArrayList<>();
        return result;
    }

    /**
     * Hozzáad egy ajtót a szobához.
     * @param d A hozzáadandó ajtó.
     */
    public void addDoor(Door d){
        doors.add(d);
    }

    /**
     * Hozzáad egy tárgyat a szobához.
     * @param i A hozzáadandó tárgy.
     */
    public void addItem(Item i){
        items.add(i);
    }

    /**
     * Töröl egy tárgyat a szobából.
     * @param i A törlendő tárgy.
     */
    public void removeItem(Item i){
        items.remove(i);
    }

    /**
     * Megbénítja a szobában tartózkodó embereket (?)
     * 3 kör erejéig. (?)
     */
    public void stunHuman(/*Human h?*/){
        int duration = 3;
        for (Student s : students) {
            s.stun(duration);
        }
        for (Teacher t : teachers) {
            t.stun(duration);
        }
    }

    /**
     * Megváltoztatja az ajtók státuszát.
     */
    public void changeDoorStatus(){

    }

    /**
     * Visszaadja, hogy a szoba elátkozott-e.
     * @return Igaz csakis akkor, ha elátkozott.
     */
    public boolean isCursed(){
        boolean result = false;
        //kérdés a teszternek
        return result;
    }

    /**
     * Elgázosítja a szobát.
     */
    public void setGas(){

    }

    /**
     * Elátkozza a szobát.
     */
    public void setCursed(){

    }

    /**
     * Megbénítja a szobában tartózkodó összes tanárt.
     */
    public void stunTechers(){
        int duration = 3; //(?)
        for (Teacher t : teachers) {
            t.stun(duration);
        }
    }

    /**
     * Visszaad egy ajtót.
     * @param i Az ejtó indexe.
     * @return Az ajtó. Ha az index értéke nem megfelelő,
     * egy olyan fantom ajtó kerül visszaadásra, mely nem 
     * látható (érvénytelen) és nem tartozik a szoba ajtajai
     * közé.
     */
    public Door getDoor(int i){
        Door result = new TwoWayDoor();
        //result.setVisible(false);
        if(i >= 0 && i < doors.size()){
            result = doors.get(i);
        }
        return result;
    }

    /**
     * (?)
     * @param r
     */
    public void moveItemsToRoom(Room r){

    }

    /**
     * Megpróbál végezni a szobában tartózodó összes hallgatóval.
     */
    public void killStudents(){
        for (Student s : students) {
            s.killYourself();
        }
    }
}
