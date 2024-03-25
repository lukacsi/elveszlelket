package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

/**
 * Az IHaveTheRightStrategy implementálja az ItemUseStrategy interfészt,
 * definiálva egy specifikus viselkedést, amikor egy tárgyat használ egy diák.
 * Ez a stratégia növelheti a diák immunitását, ha a TVSZ-nek van még használata.
 */
public class IHaveTheRightStrategy implements ItemUseStrategy {
    
    /**
     * Végrehajtja a stratégiát egy adott diákon, amikor egy tárgyat használja.
     * Először ellenőrzi, hogy a TVSZ-nek vannak-e még használatai.
     * Ha igen, növeli a diák immunitását. Másodszor, ellenőrzi, hogy ez az utolsó használat-e a tárgyból.
     * Ha ez az utolsó használat, akkor eltávolítja a tárgyat a diák inventory-jából.
     *
     * @param student A diák, aki a tárgyat használja.
     * @param item A tárgy, amelyre a stratégia alkalmazva lesz.
     */
    public void execute(Student student, Item item) {
        boolean result1 = App.t.askBoolean("TVSZ has uses left?");
        if(result1) {
            student.setImmunity(2);
            boolean result2 = App.t.askBoolean("Last use?");
            if(result2) {
                student.removeItem(item);
                App.t.removeItem(item);
            }
        }
    }
}
