package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.door.*;

public interface Human {
    boolean use(OneWayDoor door);
    boolean use(TwoWayDoor door);
    boolean pickupItem(Item item);
    boolean dropItem(Item item);
    void stun(int duration);
    boolean decreaseStun(int amount);
    Room getRoom();
}
