package skeleton.elveszlelket;

import skeleton.elveszlelket.item.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.gui.HumanView;

/**
 * Egy diákot reprezentál a szimulációs környezetben.
 * A diák interakcióba léphet ajtókkal és tárgyakkal, és különböző állapotokkal
 * rendelkezik,
 * amelyek befolyásolják az interakcióit a környezettel.
 */
public class Student implements Human {
    private HumanView view;
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

    /**
     * Visszaadja a diák nézetét.
     *
     * @return a diák nézete
     */
    public HumanView getView() {
        return view;
    }

    /**
     * Ellenőrzi, hogy a diák halott-e.
     *
     * @return igaz, ha a diák halott, különben hamis
     */
    public boolean isDead() {
        return dead;
    }

     /**
     * Ellenőrzi, hogy a diák megszerezte-e a Logart.
     *
     * @return igaz, ha a diák megszerezte a Logart, különben hamis
     */
    public boolean isLogarObtained() {
        return logarObtained;
    }

    /**
     * Visszaadja a diák elkábításának időtartamát.
     *
     * @return az elkábítás időtartama
     */
    public int getStunDuration() {
        return stunDuration;
    }

    /**
     * Visszaadja a diák immunitásának időtartamát.
     *
     * @return az immunitás időtartama
     */
    public int getImmunityDuration() {
        return immunityDuration;
    }

     /**
     * Visszaadja a diák gázvédelem időtartamát.
     *
     * @return a gázvédelem időtartama
     */
    public int getGasProtectionDuration() {
        return gasProtectionDuration;
    }

     /**
     * Ellenőrzi, hogy a diák utoljára használt ajtója blokkolva van-e.
     *
     * @return igaz, ha az ajtó blokkolva van, különben hamis
     */
    public boolean isLastDoorBlocked() {
        return doorBlocked;
    }

    /**
     * Visszaadja az utoljára használt ajtót.
     *
     * @return az utoljára használt ajtó
     */
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

    public void setView(float x, float y) {
        String resourcePath = "file:textures/student.png";
        view = new HumanView(x, y, resourcePath);
    }

    /**
     * A Student osztály konstruktora.
     * Inicializálja a diákot az alapértelmezett állapotokkal és egy üres tárgylistával.
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
        if (stunDuration > 0) {
            return false;
        }
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
        if (stunDuration > 0) {
            return false;
        }
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
        door.putMeThrough(this);
        System.out.println("Szoba valtas sikeres!");
        lastDoor = door;
        return true;
    }

    /**
     * Visszaadja a Inventory listáját
     * 
     * @return items tárgyak listája
     */
    public List<Item> getInventory() {
        return items;
    }

    /**
     * A diák felvesz egy tárgyat.
     * 
     * @param item A felveendő tárgy.
     * @return Igaz, ha a diák sikeresen felveszi a tárgyat, egyébként hamis.
     */
    @Override
    public boolean pickupItem(Item item) {
        if (stunDuration > 0) {
            return false;
        }
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
        item.setHuman(this);
        item.getRoom().getItems().remove(item);
        item.setRoom(null);
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
        if (stunDuration > 0) {
            return false;
        }
        if (items.contains(item)) {
            item.setHuman(null);
            currentRoom.addItem(item);
            item.setRoom(currentRoom);
            return items.remove(item);
        }
        return false;
    }

    /**
     * A diák eldob egy tárgyat.
     * 
     * @param item Az eldobandó tárgy.
     * @return Igaz, ha a diák sikeresen eldobja a tárgyat.
     */
    public void dropRandomItem() {
        // Meghatározzuk, hány tárgy van a hallgatónál
        int numItems = items.size();
        if (numItems > 0) {
            // int index = Main.t.r.nextInt(numItems);
            Random r = new Random();
            int index = r.nextInt(numItems);

            // Tárgy eltávolítása a hallgatótól és eldobása a szobába
            Item itemToDrop = items.get(index);
            boolean dropped = dropItem(itemToDrop);
            if (dropped) {
                System.out.println("Student dropped item: " + itemToDrop.getName());
            } else {
                System.out.println("Failed to drop item: " + itemToDrop.getName());
            }
        }
    }

    /**
     * A diák használ egy tárgyat. Az implementációtól függően ez különböző
     * hatásokat válthat ki.
     * 
     * @param item A használni kívánt tárgy.
     * @return Igaz, ha a tárgy használata sikeres volt.
     */
    public boolean useItem(Item item) {
        if (stunDuration > 0) {
            return false;
        }
        item.use(this);
        return true;
    }

    /**
     * Eltávolít egy tárgyat a diák tárgylistájából.
     * 
     * @param item Az eltávolítandó tárgy.
     */
    public void removeItem(Item item) {
        // Main.t.removeItem(item);
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
        if (stunDuration > 0) {
            return false;
        }
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
        if (immunityDuration == 0) {
            for (Item item : items) {
                useItem(item);
            }
            if (immunityDuration == 0) {
                dead = true;
            }
        }
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
        System.out.println("Hallgato elkabitva.");
        if (gasProtectionDuration == 0) {
            while (!items.isEmpty()) {
                Item i = items.get(0);
                dropItem(i);
            }
            stunDuration += duration;
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

    public void setLastDoor(Door d) {
        lastDoor = d;
    }

    /**
     * Ellenőrzi, hogy a diák rendelkezik-e a Logar tárggyal.
     * 
     * @return Igaz, ha a diák rendelkezik a Logar tárggyal, egyébként hamis.
     */
    public boolean won() {
        return winCondition;
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
        if (currentRoom.containsGas()) {
            stun(3);
        }
        if (currentRoom.getTeacher().size() > 0) {
            currentRoom.killStudents();
        }
    }

    /**
     * Beállítja a diák győzelmi feltételét. Ez akkor történik, ha a diák használja
     * a logart.
     */
    public void setWinCondition() {
        winCondition = true;
    }
}