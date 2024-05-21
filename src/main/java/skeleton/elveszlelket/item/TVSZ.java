package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.gui.ItemView;
import skeleton.elveszlelket.strategy.IHaveTheRightStrategy;

/**
 * A TVSZ osztály egy védelmi elemet reprezentál, amelyet a diákok
 * használhatnak.
 * Ez az elem egy adott stratégiát használ, amelyet a diákok védelmére
 * alkalmaznak.
 */
public class TVSZ extends ProtectionItem {

    private boolean fals;

    /**
     * Konstruktor, amely létrehoz egy TVSZ objektumot.
     * Inicializálja a név és a használatok számát, valamint beállítja a védelmi
     * stratégiát.
     */
    public TVSZ() {
        name = "TVSZ";
        uses = 3;
        strategy = new IHaveTheRightStrategy();
    }

    /**
     * Egy metódus, amely lehetővé teszi a TVSZ használatát egy diákon.
     * A stratégia végrehajtásakor a diák védelem alá kerül.
     *
     * @param student A diák, aki a TVSZ-t használja.
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

    @Override
    public void setView(float x, float y) {
        view = new ItemView(x, y, "file:textures/tvsz.png");
    }
}
