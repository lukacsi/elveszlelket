package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;

import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.*;

public class Teacher implements Human {
    private int stunDuration;
    private List<Item> items;
    private Room currentRoom;

    public Teacher()
    {
        items = new ArrayList<>();
        stunDuration = 0;
        currentRoom = new Room();
        currentRoom.addHuman(this); 
    }

    @Override
    public boolean use(OneWayDoor door) {
        return false;
    }

    @Override
    public boolean use(TwoWayDoor door) {
        return false;
    }

    @Override
    public boolean pickupItem(Item item) {
        items.add(item);
        return true;
    }

    @Override
    public boolean dropItem(Item item) {
        return items.remove(item);
    }

    @Override
    public void stun(int duration) {
        stunDuration += duration;
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

    @Override
    public Room getRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void iHaveArrived() {
        Room currentRoom = getRoom();
        currentRoom.addHuman(this); 
        System.out.println("I have arrived");
    }
}