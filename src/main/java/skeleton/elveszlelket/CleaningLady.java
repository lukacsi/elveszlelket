package skeleton.elveszlelket;

import java.util.List;

import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.door.OneWayDoor;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.gui.HumanView;
import skeleton.elveszlelket.item.Item;

/**
 * A CleaningLady osztály egy takarítónőt reprezentál a játékban.
 * Képes ajtókkal interakcióba lépni, szobákat takarítani, valamint diákok és tanárok mozgását kezelni.
 */
public class CleaningLady implements Human {
    private Door lastDoor;
    private Room currentRoom;
    private HumanView view;

    /**
     * Kényszeríti az összes diákot és tanárt, hogy hagyják el az aktuális szobát az utoljára használt ajtón keresztül.
     */
    public void plsLeave() {
        List<Student> s = currentRoom.getStudents();
        for (int i = 0; i < s.size(); i++) {
            lastDoor.accept(s.get(i));
        }

        List<Teacher> t = currentRoom.getTeacher();
        for (int i = 0; i < s.size(); i++) {
            lastDoor.accept(t.get(i));
        }
    }

    /**
     * Kitakarítja az aktuális szobát. Ha a szoba gázt tartalmaz, azt eltávolítja takarítás előtt.
     */
    public void clean() {
        if (currentRoom.containsGas()) {
            currentRoom.setGas(false);
        }
        currentRoom.clean();
    }

    /**
     * Beállítja az utoljára használt ajtót.
     *
     * @param d az ajtó, amelyet utolsóként használt
     */
    public void setLastDoor(Door d) {
        lastDoor = d;
    }

    /**
     * Megpróbál egy egyirányú ajtót használni.
     *
     * @param door az egyirányú ajtó
     * @return true, ha az ajtó sikeresen használva lett, különben false
     */
    @Override
    public boolean use(OneWayDoor door) {
        if (!door.isRightDirection(getRoom())) {
            if (door.getDest(getRoom()).hasFreePlace()) {
                door.putMeThrough(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Megpróbál egy kétirányú ajtót használni.
     *
     * @param door a kétirányú ajtó
     * @return true, ha az ajtó sikeresen használva lett, különben false
     */
    @Override
    public boolean use(TwoWayDoor door) {
        if (door.getDest(getRoom()).hasFreePlace()) {
            door.putMeThrough(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean pickupItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pickupItem'");
    }

    @Override
    public boolean dropItem(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dropItem'");
    }

    @Override
    public void stun(int duration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stun'");
    }

    @Override
    public boolean decreaseStun(int amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decreaseStun'");
    }

    /**
     * Lekéri a CleaningLady aktuális szobáját.
     *
     * @return az aktuális szoba
     */
    @Override
    public Room getRoom() {
        return currentRoom;
    }

    /**
     * Meghívódik, amikor a CleaningLady megérkezik egy szobába. Meghívja a plsLeave() és clean() metódusokat.
     */
    @Override
    public void iHaveArrived() {
        plsLeave();
        clean();
    }

    /**
     * Beállítja a CleaningLady aktuális szobáját.
     *
     * @param room a beállítandó szoba
     */
    @Override
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    /**
     * Beállítja a CleaningLady nézetét a GUI-ban.
     *
     * @param x a nézet x-koordinátája
     * @param y a nézet y-koordinátája
     */
    public void setView(float x, float y) {
        String resourcePath = "file:textures/cleaningady.png";
        HumanView view = new HumanView(x, y, resourcePath);
    }

    /**
     * Megkapja a CleaningLady nézetét a GUI-tól.
     *
     * @return view a CleaningLady nézete
     */
    public HumanView getView() {
        return view;
    }

}
