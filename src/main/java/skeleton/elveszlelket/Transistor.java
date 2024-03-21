package skeleton.elveszlelket;

public class Transistor extends Item{
    private Transistor pair;
    private Room location;

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
