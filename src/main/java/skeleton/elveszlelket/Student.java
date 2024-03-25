package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;

import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.Room.*;

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
    }

    @Override
    public boolean use(OneWayDoor door) {
        Room currentRoom = getRoom();
        if (doorBlocked!=true) {
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
            lastDoor = door;
            return true;
        }
        else{
            return false;
        }
        
    }

    @Override
    public boolean use(TwoWayDoor door) {
        Room currentRoom = getRoom();
        if (doorBlocked!=true) {
        if(!currentRoom.hasFreePlace())
        {
            return false;
        }
        currentRoom.removeHuman(this);
        door.accept(this);
        door.putMeThrough(this);
        lastDoor = door;
        return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean pickupItem(Item item) {
        if (items.size() >= 5) {
            return false;
        }
        if(item.getName() == "Logar")
            item.use(this);
        items.add(item);
        return true;
    }

    @Override
    public boolean dropItem(Item item) {
        currentRoom.addItem(item);
        return items.remove(item);
    }

    public boolean useItem(Item item) {
        item.use(this);
        return true;
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean pairTransistor(Transistor t1, Transistor t2) {
        t1.setLocation(currentRoom);
        t2.setLocation(currentRoom);
        t1.addPair(t2);
        if (t1.hasPair()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean killYourself() {
        if(immunityDuration != 0)
            dead = true;
        return dead;
    }

    public void blockDoor() {
        doorBlocked = true;
    }

    public void stun(int duration) {
        stunDuration += duration;
    }

    public boolean teleport(Room room) {
        if(room.hasFreePlace()) {
            getRoom().removeHuman(this);
            room.addHuman(this);
            return true;
        } else return false;
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
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public boolean hasLogar() {
        return winCondition;
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
        Room currentRoom = getRoom();
        currentRoom.addHuman(this); 
        System.out.println("I have arrived");
    }

    public void setWinCondition() {
        winCondition = true;
    }
}