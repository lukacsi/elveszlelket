package skeleton.elveszlelket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.gui.HumanView;
import skeleton.elveszlelket.gui.RoomView;
import skeleton.elveszlelket.item.Item;

public class Room {
    private List<Teacher> teachers;
    private List<Student> students;
    private List<CleaningLady> cleaners;
    private List<Item> items;
    private List<Door> doors;
    private boolean cursed;
    private boolean hasGas;
    private int stickyness;
    private static int cleanlyness = 3;
    private RoomView view;
    
    public RoomView getView() {
    	return view;
    }

    public void setView(float x, float y) {
    	view = new RoomView(x,y);
    	view.loadTextures();
    }
    
    /**
     * Alapkonstruktor a szobához.
     */
    public Room() {
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        cleaners = new ArrayList<>();
        items = new ArrayList<>();
        doors = new ArrayList<>();
        stickyness = cleanlyness;
        cursed = false;
        hasGas = false;
    }

    /*
    * Vissza adja a szobában található tárgyakat.
    */
    public List<Item> getItems() {
        if(stickyness <= 0) {
            return new ArrayList<>();
        }
        return items;
    }
    
    /**
     * Hallgatót ad a szobához.
     * 
     * @param s A hozzáadandó hallgató.
     */
    public void addHuman(Student s) {
        s.setCurrentRoom(this);
        stickyness--;
        students.add(s);
    }
    
    /**
     * Oktatót ad a szobához.
     * 
     * @param t A hozzáadandó oktató.
     */
    public void addHuman(Teacher t) {
        t.setCurrentRoom(this);
        stickyness--;
        teachers.add(t);
    }

     /**
     * Hallgatót ad a szobához.
     * 
     * @param s A hozzáadandó hallgató.
     */
    public void addHuman(CleaningLady c) {
        c.setCurrentRoom(this);
        cleaners.add(c);
    }

    /**
     * Elvesz egy hallgatót a szobából.
     * 
     * @param s A törlendő hallgató.
     */
    public void removeHuman(Student s) {
        students.remove(s);
    }

    /**
     * Elvesz egy oktatót a szobából.
     * 
     * @param t A törlendő oktató.
     */
    public void removeHuman(Teacher t) {
        students.remove(t);
    }

    /**
     * Elvesz egy takarítőnőt a szobából.
     * 
     * @param t A törlendő CleaningLady.
     */
    public void removeHuman(CleaningLady c) {
        cleaners.remove(c);
    }

    /**
     * Visszaadja, hogy gázos-e a szoba.
     * 
     * @return Igaz csakis akkor, ha gázos.
     */
    public boolean containsGas() {
        return hasGas;
    }

    public void clean() {
        stickyness = cleanlyness;
    }

    /**
     * Visszaadja, hogy van-e szabad hely a szobában.
     * 
     * @return Igaz csakis akkor, ha van hely.
     */
    public boolean hasFreePlace() {
        int tartozkodok = students.size() + teachers.size();
        return tartozkodok < 5;
    }

    /**
     * Összevon két szobát.
     * 
     * @param r1 A másik összevonandó szoba, mivel ezt egy adott Room példányra
     *           hívjuk meg,
     *           így ő lesz a merge egyik szereplője
     * @param uj -> Az a szoba amelyik a két szoba összevonásának a végeredménye
     *           lesz.
     * @return Az összevonás során keletkező új szoba.
     */
    public void merge(Room r1, Room uj) {
        moveItemsToRoom(uj);
        r1.moveItemsToRoom(uj);
        if (cursed || r1.isCursed())
            uj.setCursed(true);
        if (hasGas || r1.containsGas())
            uj.setGas(true);
        
        for(Door d : doors) {
        	if(!d.getDest(r1).equals(r1)) {
        		d.setRooms(uj, d.getDest(r1));
        		uj.addDoor(d);
        	}
        }
        for(Door d : r1.getDoors()) {
        	if(!d.getDest(r1).equals(this)) {
        		d.setRooms(uj, d.getDest(r1));
        		uj.addDoor(d);
        	}
        }
    }

    /**
     * Visszaadja, hogy gázos-e a szoba.
     * 
     * @return Igaz csakis akkor, ha gázos.
     */
    public boolean hasGas() {
        return hasGas;
    }

    /**
     * Kettéoszt egy szobát.
     * 
     * @param r1 Az egyik előre létrehozott szoba.
     * @param r2 A másik előre létrehozott szoba.
     */
    public void split(Room r1, Room r2) {

    }

    /**
     * Hozzáad egy ajtót a szobához.
     * 
     * @param d A hozzáadandó ajtó.
     */
    public void addDoor(Door d) {
        doors.add(d);
    }

    /**
     * Hozzáad egy tárgyat a szobához.
     * 
     * @param i A hozzáadandó tárgy.
     */
    public void addItem(Item i) {
        i.setRoom(this);
        items.add(i);
    }

    /**
     * Töröl egy tárgyat a szobából.
     * 
     * @param i A törlendő tárgy.
     */
    public void removeItem(Item i) {
        items.remove(i);
    }

    /**
     * Megbénítja a szobában tartózkodó embereket
     * 3 kör erejéig.
     */
    public void stunHuman() {
        int duration = 3;
        for (Student s : students) {
            s.stun(duration);
        }
        stunTeachers();
    }

    /**
     * Megváltoztatja az ajtók státuszát.
     */
    public void changeDoorStatus() {
        if (cursed) {
            for (Door door : doors) {
                door.changeVisibility();
            }
        }
    }

    /**
     * Visszaadja, hogy a szoba elátkozott-e.
     * 
     * @return Igaz csakis akkor, ha elátkozott.
     */
    public boolean isCursed() {
        return cursed;
    }

    /**
     * @param boolean. Ha a paraméter igaz, elátkozza a szobát.
     *                 Ha a paraméter hamis, megszűnteti az átkot.
     */
    public void setGas(boolean ertek) {
        hasGas = ertek;
    }

    /**
     * @param boolean. Ha a paraméter igaz, elgázosítja a szobát.
     *                 Ha a paraméter hamis, megszűnteti a gázt.
     */
    public void setCursed(boolean ertek) {
        cursed = ertek;
    }

    /**
     * Megbénítja a szobában tartózkodó összes tanárt.
     */
    public void stunTeachers() {
        int duration = 3;
        for (Teacher t : teachers) {
            t.stun(duration);
        }
    }

    /**
    * Visszaadja az ajtók listáját.
    *
    * @return az ajtók listája
    */
    public List<Door> getDoors() {
        return doors;
    }

    /**
    * Visszaadja a diákok listáját.
    *
    * @return a diákok listája
    */
    public List<Student> getStudents() {
        return students;
    }

    /**
    * Visszaadja a tanárok listáját.
    *
    * @return a tanárok listája
    */
    public List<Teacher> getTeacher() {
        return teachers;
    }
    
    /**
    * Visszaadja a takarítónők listáját.
    *
    * @return a takarítónők listája
    */
    public List<CleaningLady> getCleaningLadies() {
        return cleaners;
    }

    /**
     * Visszaad egy ajtót.
     * 
     * @param i Az ajtó indexe.
     * @return Az ajtó. Ha az index értéke nem megfelelő,
     *         egy olyan fantom ajtó kerül visszaadásra, mely nem
     *         látható (érvénytelen) és nem tartozik a szoba ajtajai
     *         közé.
     */
    public Door getDoor(int i) {
        Door result = new TwoWayDoor();
        // result.setVisible(false);
        if (i >= 0 && i < doors.size()) {
            result = doors.get(i);
        }
        return result;
    }

    /**
     * Visszaad egy ajtót.
     * 
     * @param i Az ajtó indexe.
     * @return Ha volt találat a szoba feljegyzett ajtajai között,
     *         azt adja vissza. Egyéb esetben null-lal tér vissza.
     */
    public Door getDoorAt(int i) {
        if (i >= 0 && i < doors.size()) {
            return doors.get(i);
        }
        return null;
    }

    /**
     * Visszaad egy ajtót.
     * 
     * @param i A törölni kívánt ajtó.
     * @return Ha volt találat a szoba feljegyzett ajtajai között,
     *         azt törli és igaz értékkel tér vissza,
     *         Egyéb esetben false értékkel.
     */
    public boolean removeDoor(Door d) {
        return doors.remove(d);
    }

    /**
     * A szoba, melyre mehgívják az összes benne található
     * tárgyat átrakja a paraméterül kapott szobába.
     * 
     * @param r A szoba, melybe a tárgyak kerülnek majd.
     */
    public void moveItemsToRoom(Room r) {
        for (Item i : items) {
            r.addItem(i);
        }
        items.clear();
    }

    /**
     * Megpróbál végezni a szobában tartózodó összes hallgatóval.
     */
    public void killStudents() {
        for (Student s : students) {
            s.killYourself();
        }
    }
}
