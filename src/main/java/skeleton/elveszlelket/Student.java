package skeleton.elveszlelket;

import skeleton.elveszlelket.item.*;

import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.*;

/**
 * Egy diákot reprezentál a szimulációs környezetben.
 * A diák interakcióba léphet ajtókkal és tárgyakkal, és különböző állapotokkal
 * rendelkezik,
 * amelyek befolyásolják az interakcióit a környezettel.
 */
public class Student implements Human {
    private int stunDuration;
    private int immunityDuration;
    private boolean doorBlocked;
    private boolean logarObtained;
    private int gasProtectionDuration;
    private boolean dead;
    private boolean winCondition;
    private Door lastDoor;
    private List<Item> items;
    private Room currentRoom;

    public boolean isDead() {
        return dead;
    }

    public boolean isLogarObtained() {
        return logarObtained;
    }

    public int getStunDuration() {
        return stunDuration;
    }

    public int getImmunityDuration() {
        return immunityDuration;
    }

    public int getGasProtectionDuration() {
        return gasProtectionDuration;
    }

    public boolean isLastDoorBlocked() {
        return doorBlocked;
    }

    public Door getLastDoor() {
        return lastDoor;
    }

    /**
     * @return A játékos által tárolt tárgyak listája
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * A Student osztály konstruktora.
     * Inicializálja a diákot az alapértelmezett állapotokkal és egy üres
     * tárgylistával.
     */
    public Student() {
        items = new ArrayList<>();
        stunDuration = 0;
        immunityDuration = 0;
        doorBlocked = false;
        logarObtained = false;
        gasProtectionDuration = 0;
        dead = false;
        winCondition = false;
        lastDoor = null;
        currentRoom = null;
    }

    /**
     * A diák használ egy egyirányú ajtót.
     * 
     * @param door Az használandó egyirányú ajtó.
     * @return Igaz, ha a diák sikeresen használja az ajtót, egyébként hamis.
     */
    @Override
    public boolean use(OneWayDoor door) {
        Room currentRoom = getRoom();
        if (doorBlocked == true) {
            if (lastDoor.equals(door)) {
                System.out.println("Szoba valtas sikertelen!");
                return false;
            }
        }
        if (!door.isRightDirection(getRoom())) {
            System.out.println("Szoba valtas sikertelen!");
            return false;
        }
        if (!currentRoom.hasFreePlace()) {
            return false;
        }
        currentRoom.removeHuman(this);
        door.accept(this);
        door.putMeThrough(this);
        lastDoor = door;
        System.out.println("Szoba valtas sikeres!");
        return true;
    }

    /**
     * A diák használ egy kétirányú ajtót.
     * 
     * @param door Az használandó kétirányú ajtó.
     * @return Igaz, ha a diák sikeresen használja az ajtót, egyébként hamis.
     */
    @Override
    public boolean use(TwoWayDoor door) {
        Room currentRoom = getRoom();
        if (doorBlocked == true) {
            if (lastDoor.equals(door)) {
                System.out.println("Szoba valtas sikertelen!");
                return false;
            }
        }
        if (!currentRoom.hasFreePlace()) {
            return false;
        }
        currentRoom.removeHuman(this);
        door.accept(this);
        door.putMeThrough(this);
        System.out.println("Szoba valtas sikeres!");
        lastDoor = door;
        return true;
    }

    /**
     * A diák felvesz egy tárgyat.
     * 
     * @param item A felveendő tárgy.
     * @return Igaz, ha a diák sikeresen felveszi a tárgyat, egyébként hamis.
     */
    @Override
    public boolean pickupItem(Item item) {
        if (item.getRoom() != null) {
            if (!item.getRoom().equals(currentRoom)) {
                return false;
            }
        } else if (item.getHuman() != null) {
            return false;
        }

        if (items.size() >= 5) {
            return false;
        }
        if (item.getName() == "Logar") {
            item.use(this);
            logarObtained = true;
        }
        items.add(item);
        return true;
    }

    /**
     * A diák eldob egy tárgyat.
     * 
     * @param item Az eldobandó tárgy.
     * @return Igaz, ha a diák sikeresen eldobja a tárgyat.
     */
    @Override
    public boolean dropItem(Item item) {
        if (items.contains(item)) {
            currentRoom.addItem(item);
            return items.remove(item);
        }
        return false;
    }

    /**
     * A diák használ egy tárgyat. Az implementációtól függően ez különböző
     * hatásokat válthat ki.
     * 
     * @param item A használni kívánt tárgy.
     * @return Igaz, ha a tárgy használata sikeres volt.
     */
    public boolean useItem(Item item) {
        item.use(this);
        return true;
    }

    /**
     * Eltávolít egy tárgyat a diák tárgylistájából.
     * 
     * @param item Az eltávolítandó tárgy.
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Párosít két tranzisztort. Ha mindkét tranzisztor párosítatlan, akkor
     * összekapcsolja őket.
     * 
     * @param t1 Az első tranzisztor.
     * @param t2 A második tranzisztor.
     * @return Igaz, ha a párosítás sikeres volt, egyébként hamis.
     */
    public boolean pairTransistor(Transistor t1, Transistor t2) {
        t1.setLocation(currentRoom);
        t2.setLocation(currentRoom);
        t1.addPair(t2);
        if (t1.hasPair()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A diák megpróbál meghalni, ha nincs immunitása akkor ez be is következik,
     * amely valójában az állapotát változtatja meg halottra.
     * 
     * @return Igaz, ha a diák mostantól halott, egyébként hamis.
     */
    public boolean killYourself() {
        if (immunityDuration != 0)
            dead = true;
        return dead;
    }

    /**
     * Blokkolja a legutóbb használt ajtó használatát a diák számára.
     */
    public void blockDoor() {
        doorBlocked = true;
    }

    /**
     * Megbénítja a diákot egy adott időtartamra, eldobja az összess tárgyát.
     * 
     * @param duration A bénulás időtartama.
     */
    public void stun(int duration) {
        if (gasProtectionDuration == 0) {
            stunDuration += duration;
            for (Item item : items) {
                dropItem(item);
            }
        }

    }

    /**
     * Teleportálja a diákot egy másik szobába.
     * 
     * @param room A cél szoba, ahová a diák teleportálni fog.
     * @return Igaz, ha a teleportálás sikeres volt, egyébként hamis.
     */
    public boolean teleport(Room room) {
        if (room.hasFreePlace()) {
            getRoom().removeHuman(this);
            room.addHuman(this);
            return true;
        } else
            return false;
    }

    /**
     * Csökkenti a diák bénulásának időtartamát.
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
     * Beállítja a diák immunitásának időtartamát.
     * 
     * @param immunity Az immunitás új időtartama.
     */
    public void setImmunity(int immunity) {
        immunityDuration = immunity;
    }

    /**
     * Csökkenti a diák immunitásának időtartamát.
     */
    public void decreaseImmunity() {
        immunityDuration--;
    }

    /**
     * Beállítja a diák gázvédelem időtartamát.
     * 
     * @param duration A gázvédelem időtartama.
     */
    public void setGasProtection(int duration) {
        gasProtectionDuration = duration;
    }

    /**
     * Csökkenti a diák gázvédelem időtartamát.
     */
    public void decreaseGasProtection() {
        gasProtectionDuration--;
    }

    /**
     * Visszaadja a szobát, amelyben a diák jelenleg tartózkodik.
     * 
     * @return A jelenlegi szoba, ahol a diák van.
     */
    @Override
    public Room getRoom() {
        return currentRoom;
    }

    /**
     * Beállítja a diák jelenlegi szobáját.
     * 
     * @param room Az új szoba, ahová a diákot helyezzük.
     */
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    /**
     * Ellenőrzi, hogy a diák rendelkezik-e a Logar tárggyal.
     * 
     * @return Igaz, ha a diák rendelkezik a Logar tárggyal, egyébként hamis.
     */
    public boolean hasLogar() {
        return logarObtained;
    }

    /**
     * Ellenőrzi, hogy a diák halott-e.
     * 
     * @return Igaz, ha a diák halott, egyébként hamis.
     */
    public boolean halottE() {
        if (dead) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Jelzi, hogy a diák megérkezett egy új helyszínre. Ezt üzenetküldéssel
     * jelezhetjük.
     */
    public void iHaveArrived() {
        Room currentRoom = getRoom();
        currentRoom.addHuman(this);
        System.out.println("I have arrived");
    }

    /**
     * Beállítja a diák győzelmi feltételét. Ez akkor történik, ha a diák használja
     * a logart.
     */
    public void setWinCondition() {
        winCondition = true;
    }
}