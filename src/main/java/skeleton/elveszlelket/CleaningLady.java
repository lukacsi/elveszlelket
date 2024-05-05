package skeleton.elveszlelket;
import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.door.OneWayDoor;
import skeleton.elveszlelket.door.TwoWayDoor;
import skeleton.elveszlelket.item.Item;

public class CleaningLady implements Human {
    private Door lastDoor;
    private Room currentRoom;

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

    public void clean() {
        if(currentRoom.containsGas()) {
            currentRoom.setGas(false);
        }
        currentRoom.clean();
    }

    public void setLastDoor(Door d) {
        lastDoor = d;
    } 

    @Override
    public boolean use(OneWayDoor door) {
        if(!door.isRightDirection(getRoom())) {
            if(door.getDest(getRoom()).hasFreePlace()) {
                door.putMeThrough(this);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean use(TwoWayDoor door) {
        if(door.getDest(getRoom()).hasFreePlace()) {
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

    @Override
    public Room getRoom() {
        return currentRoom;
    }

    @Override
    public void iHaveArrived() {
        plsLeave();
        clean();
    }

    @Override
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
}
