package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.DrunkStrategy;

public class Beer extends ProtectionItem {
    /** A Sör konstruktora, beállítja
     * a nevét Beer-re,
     * a használatok számát 1-re,
     * a strategy-t DrunkStrategy-re.
     */
    public Beer() {
        name = "Beer";
        uses = 1;
        strategy = new DrunkStrategy();
    }
    
    /** A Sör használatát implementáló függvény.
     *  Meghívja a saját strategy-jét.
     * @param student A tárgyat használó hallgató
     */
    public void use(Student student) {
        strategy.execute(student, this);
    }

    @Override
    public void setFalse(boolean fals) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFalse'");
    }
}
