package skeleton.elveszlelket.item;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.gui.ItemView;
import skeleton.elveszlelket.strategy.ItemUseStrategy;

public abstract class Item {
    protected String name;
    protected ItemUseStrategy strategy;
    protected Room room;
    protected Human human;
    protected ItemView view;
    
    public ItemView getView() {
    	return view;
    }
    
    public abstract void setView(float x, float y);

    public Item() {
        room = null;
        human = null;
    }

    /**
     * @params Room. A torolni kivant ajto.
     */
    public void setRoom(Room r) {
        room = r;
    }

    /**
     * @params Human. A torolni kivant ember.
     */
    public void setHuman(Human h) {
        human = h;
    }

    /**
     * @return Room Visszaadja melyik szobaban van a targy.
     *         Ha epp egy embernel van a targy, null-lal ter vissza.
     */
    public Room getRoom() {
        return room;
    }

    /**
     * @return Human Visszaadja melyik embernel van a targy.
     *         Ha epp egy szobaban van a targy, null-lal ter vissza.
     */
    public Human getHuman() {
        return human;
    }

    /**
     * @return String Visszaadja a tárgy nevét
     */
    public String getName() {
        return name;
    }

    public abstract void setFalse(boolean fals);
    
    public abstract void use(Student student);
}
