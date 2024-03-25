package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

/**
 * A GasProtectionStrategy egy stratégia, amely növeli a diák gázvédelmét,
 * amikor egy adott tárgyat használnak.
 * Ezt a stratégiát FPP2-es maszkhoz tartozik.
 */
public class GasProtectionStrategy implements ItemUseStrategy {
    
    /**
     * Végrehajtja a gázvédelmi stratégiát egy adott diákon egy adott tárgy használatakor(ffp2maszk).
     * Ha a  maszknak, vannak még használatai, akkor növeli a diák
     * gázvédelmi szintjét.
     *
     * @param student A diák, aki a tárgyat használja.
     * @param item A tárgy, amelynek használata során a stratégia aktiválódik.
     */
    public void execute(Student student, Item item) {
        boolean result = App.t.askBoolean("Mask has uses?");
        if (result) {
            student.setGasProtection(30);
        }
    }
}
