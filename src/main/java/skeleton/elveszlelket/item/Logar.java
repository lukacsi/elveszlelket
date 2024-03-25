package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.WinStrategy;

public class Logar extends Item {
    /**
     * A Logar konstruktora, beaállítja,
     * a nevét Logar-ra,
     * a strategy-t WinStrategy-re.
     */
    public Logar() {
        name = "Logar";
        strategy = new WinStrategy();
    }
    
    /** A Logar használatát implementáló függvény.
     *  Meghívja a saját strategy-jét.
     * @param student A tárgyat használó hallgató
     */
    public void use(Student student) {
        strategy.execute(student, this);
    }
}
