package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.gui.ItemView;
import skeleton.elveszlelket.strategy.RagStrategy;

public class Rag extends ProtectionItem{
    /** A Rongy konstruktora, beállítja
     * a nevét Rag-ra,
     * a használatok számát 30-re,
     * a strategy-t RagStrategy-re.
     */
    public Rag() {
        name = "Rag";
        uses = 30;
        strategy = new RagStrategy();
    }
    /** A Rag használatát implementáló függvény.
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

    @Override
    public void setView(float x, float y) {
    	view = new ItemView(x, y, "textures/rag.png");
    }
}
