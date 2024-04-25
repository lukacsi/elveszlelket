package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.GasProtectionStrategy;

public class FFP2Mask extends ProtectionItem{
    private boolean fals;
    /** A FFP2Mask konstruktora, beállítja
     * a nevét FFP2Mask-ra,
     * a használatok számát 3-re,
     * a strategy-t GasProtectionStrategy-re.
     */
    public FFP2Mask() {
        name = "FFP2Mask";
        uses = 3;
        strategy = new GasProtectionStrategy();
    }
    /** A FFP2Mask használatát implementáló függvény.
     *  Meghívja a saját strategy-jét.
     * @param student A tárgyat használó hallgató
     */
    public void use(Student student) {
        strategy.execute(student, this);
    }
    public void setFalse(boolean fals) {
        this.fals = fals;
    }
    public boolean getFalse() {
        return fals;
    }
}
