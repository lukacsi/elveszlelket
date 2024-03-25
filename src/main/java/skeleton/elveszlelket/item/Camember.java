package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.GasStrategy;

public class Camember extends Item {
    private Boolean opened;

    public Camember() {
        name = "Camembert";
        strategy = new GasStrategy();
        opened = false;
    }
    public void use(Student student) {
        
    }
}
