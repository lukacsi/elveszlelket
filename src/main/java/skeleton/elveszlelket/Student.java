package skeleton.elveszlelket;
import skeleton.elveszlelket.item.*;

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

    public boolean useItem(Item item) {
        return false;
    }

    public void removeItem(Item item) {
    }

    public boolean pairTransistor(Transistor t1, Transistor t2) {
        return false;
    }

    public boolean killYourself() {
        return false;
    }

    public void blockDoor() {
    }

    public void stun(int duration) {
        
    }

    public boolean teleport(Room room) {
        return false;
    }

    @Override
    public boolean decreaseStun(int amount) {
    }

    public void setImmunity(int immunity) {
    }

    public void decreaseImmunity() {
    }

    public void setGasProtection(int duration) {
    }

    public void decreaseGasProtection() {
    }

    @Override
    public Room getRoom() {
        return null;
    }
}

