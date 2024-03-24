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
     * @param s A hozzáadandó hallgató.
     */
    public void addHuman(Student s){
        //értesítés
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * Oktatót ad a szobához.
     * @param t A hozzáadandó oktató.
     */
    public void addHuman(Teacher s){
        //értesítés
    }
    
    /**
     * Elvesz egy hallgatót a szobából.
     * @param s A törlendő hallgató.
     */
    public void removeHuman(Student s){
        //értesítés
    }

    /**
     * Elvesz egy oktatót a szobából.
     * @param t A törlendő oktató.
     */
    public void removeHuman(Teacher t){
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
     * @param r1 A másik összevonandó szoba, mivel ezt egy adott Room példányra hívjuk meg,
     * így ő lesz a merge egyik szereplője
     * @param r2 -> Az a szoba amelyik a két szoba összevonásának a végeredménye lesz.
     * @return Az összevonás során keletkező új szoba.
     */
    public void merge(Room r1, Room r2){
        
    }

    /**
     * Kettéoszt egy szobát.
     * @param r1 Az egyik előre létrehozott szoba.
     * @param r2 A másik előre létrehozott szoba.
     */
    public void split(Room r1, Room r2){
        
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
     * Megbénítja a szobában tartózkodó embereket
     * 3 kör erejéig.
     */
    public void stunHuman(){
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
    public void stunTeachers(){
        int duration = 3;
        for (Teacher t : teachers) {
            t.stun(duration);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeacher() {
        return teachers;
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
     * A szoba, melyre mehgívják az összes benne található
     * tárgyat átrakja a paraméterül kapott szobába.
     * @param r A szoba, melybe a tárgyak kerülnek majd.
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
