package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.door.*;

public class Teacher implements Human {
    private int stunDuration;

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
        return false;
    }

    @Override
    public boolean dropItem(Item item) {
        return false;
    }

    @Override
    public void stun(int duration) {
    }

    @Override
    public boolean decreaseStun(int amount) {
        return false;
    }

    @Override
    public Room getRoom() {
        return null;
    }
}

