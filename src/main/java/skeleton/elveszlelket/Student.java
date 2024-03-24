package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;

import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.*;

public class Student implements Human {
    private int stunDuration;
    private int immunityDuration;
    private boolean doorBlocked;
    private boolean logarObtained;
    private int protectedDuration;
    private int gasProtectionDuration;
    private boolean dead;
    private boolean winCondition;
    private Door lastDoor;
    private List<Item> items;

    public Student() {
        items = new ArrayList<>();
        stunDuration = 0;
        immunityDuration = 0;
        doorBlocked = false;
        logarObtained = false;
        protectedDuration = 0;
        gasProtectionDuration = 0;
        dead = false;
        winCondition = false;
        lastDoor = null;  
    }

    @Override
    public boolean use(OneWayDoor door) {
        if (!door.isRightDirection(getRoom())) {
            return false;
        }
        /*Room currentRoom = getRoom(); // Get the current room
        currentRoom.removeHuman(this);
        Room destinationRoom = door.getDestinationRoom(); // Get the destination room
        destinationRoom.addHuman(this);
        lastDoor = door;*/
        return true;
        
    }

    @Override
    public boolean use(TwoWayDoor door) {
        return false;
    }

    @Override
    public boolean pickupItem(Item item) {
        if (items.size() >= 5) {
            return false;
        }
        items.add(item);
        return true;
    }

    @Override
    public boolean dropItem(Item item) {
        return items.remove(item);
    }

    public boolean useItem(Item item) {
            return false;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean pairTransistor(Transistor t1, Transistor t2) {
        return false;
    }

    public boolean killYourself() {
        return false;
    }

    public void blockDoor() {
        doorBlocked = true;
    }

    public void stun(int duration) {
        stunDuration += duration;
    }

    public boolean teleport(Room room) {
        getRoom().removeHuman(this);
        room.addHuman(this);
        return true;
    }

    @Override
    public boolean decreaseStun(int amount) {
        stunDuration -= amount;
        if (stunDuration < 0) {
            stunDuration = 0;
            return true;
        }
        return false;
    }

    public void setImmunity(int immunity) {
        immunityDuration = immunity;
    }

    public void decreaseImmunity() {
        immunityDuration--;
    }

    public void setGasProtection(int duration) {
        gasProtectionDuration = duration;
    }

    public void decreaseGasProtection() {
        gasProtectionDuration--;
    }

    @Override
    public Room getRoom() {
        return null;
    }

    public boolean hasLogar() {
        for (Item item : items) {
            if (item instanceof Logar) {
                logarObtained = true;
                return logarObtained;
            }
        }
        return false;
    }

    public boolean halottE() {
        if(dead)
        {
            return true;
        }
        else{
            return false;
        }
    }
    public void iHaveArrived() {
        
    }
}