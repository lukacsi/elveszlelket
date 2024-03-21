package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Player;
import skeleton.elveszlelket.Room;
import strategy.TeleportStrategy;

public class Transistor extends Item{
    private Transistor pair;
    private Room location;


    public Transistor() {
        name = "Transistor";
        strategy = new TeleportStrategy();
    }
    public void use(Player player) {
        
    }

    public boolean hasPair() {
        return pair != null;
    }

    public void addPair(Transistor other) {
        pair = other;
    }

    public Transistor getPair() {
        return pair;
    }

    public void unPair() {
        pair = null;
    }

    public void setLocation(Room room) {
        location = room;
    }

    public Room getLocation() {
        return location;
    }
}
