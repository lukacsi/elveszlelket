package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;

import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.*;

/**
 * Egy tanárt reprezentál a szimulációs környezetben.
 * A tanár képes használni ajtókat, felvenni tárgyakat, és elkábítható.
 */
public class Teacher implements Human {
    private int stunDuration;
    private List<Item> items;
    private Room currentRoom;

    /**
     * A Teacher osztály konstruktora.
     * Inicializálja a tanárt az alapértelmezett értékekkel.
     */
    public Teacher()
    {
        items = new ArrayList<>();
        stunDuration = 0;
    }

    /**
     * A tanár használ egy egyirányú ajtót.
     *
     * @param door Az egyirányú ajtó, amelyet a tanár megpróbál használni.
     * @return Igaz, ha a tanár sikeresen használja az ajtót, egyébként hamis.
     */
    @Override
    public boolean use(OneWayDoor door) {
        Room currentRoom = getRoom();
            if (!door.isRightDirection(getRoom())) {
                return false;
            }
            if(!currentRoom.hasFreePlace())
            {
                return false;
            }
            currentRoom.removeHuman(this);
            door.accept(this);
            door.putMeThrough(this);
            return true;
        }


    /**
     * A tanár használ egy kétirányú ajtót.
     *
     * @param door A kétirányú ajtó, amelyet a tanár megpróbál használni.
     * @return Igaz, ha a tanár sikeresen használja az ajtót, egyébként hamis.
     */
    @Override
    public boolean use(TwoWayDoor door) {
        Room currentRoom = getRoom();
        if(!currentRoom.hasFreePlace())
        {
            return false;
        }
        currentRoom.removeHuman(this);
        door.accept(this);
        door.putMeThrough(this);
        return true;
    }

    /**
     * A tanár felvesz egy tárgyat.
     *
     * @param item A felveendő tárgy.
     * @return Igaz, ha a tanár sikeresen felveszi a tárgyat, egyébként hamis.
     */
    @Override
    public boolean pickupItem(Item item) {
        if(items.size()<1) {
            items.add(item);
            return true;
        } else return false;
    }

    /**
     * A tanár eldob egy tárgyat.
     *
     * @param item Az eldobandó tárgy.
     * @return Igaz, ha a tanár sikeresen eldobja a tárgyat.
     */
    @Override
    public boolean dropItem(Item item) {
        currentRoom.addItem(item);
        return items.remove(item);
    }

    /**
     * Megbénítja a tanárt egy adott időtartamra.
     * Eldobja a tárgyait.
     * @param duration A bénulás időtartama.
     */
    @Override
    public void stun(int duration) {
        stunDuration += duration;
        dropItem(items.get(0));
    }

    /**
     * Csökkenti a tanár bénulásának időtartamát.
     *
     * @param amount A csökkentés mértéke.
     * @return Igaz, ha a bénulás időtartama nullára csökken, egyébként hamis.
     */
    @Override
    public boolean decreaseStun(int amount) {
        stunDuration -= amount;
        if (stunDuration < 0) {
            stunDuration = 0;
            return true;
        }
        return false;
    }

    /**
     * Visszaadja a szobát, amelyben a tanár jelenleg tartózkodik.
     *
     * @return A jelenlegi szoba, ahol a tanár van.
     */
    @Override
    public Room getRoom() {
        return currentRoom;
    }

    /**
     * Beállítja a tanár jelenlegi szobáját.
     * Ez a metódus lehetővé teszi a tanár számára, hogy egy másik szobába lépjen.
     *
     * @param room Az új szoba, ahová a tanárt helyezzük.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Jelzi, hogy a tanár megérkezett egy új helyszínre.
     * Ezt a metódust hívhatjuk meg, amikor a tanár belép egy szobába,
     * és jelezhetjük a játékban vagy szimulációban, hogy a tanár új helyszínre érkezett.
     */
    public void iHaveArrived() {
        Room currentRoom = getRoom();
        currentRoom.addHuman(this); 
        System.out.println("I have arrived");
    }
}