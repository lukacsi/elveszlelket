package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.WinStrategy;

public class Logar extends Item {
    private boolean fals;
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

    @Override
    public void setFalse(boolean fals) {
        this.fals = fals;
    }
    
    public boolean getFalse() {
        return fals;
    }
}
