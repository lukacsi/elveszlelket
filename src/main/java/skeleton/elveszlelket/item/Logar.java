package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.WinStrategy;

public class Logar extends Item {
    public Logar() {
        name = "Logar";
        strategy = new WinStrategy();
    }
    public void use(Student student) {
        
    }
}
