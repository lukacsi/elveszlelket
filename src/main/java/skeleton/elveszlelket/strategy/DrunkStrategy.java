package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

/**
 * A DrunkStrategy egy olyan stratégia, amely a tárgyhasználat hatását valósítja meg egy diákon.
 * Ebben az esetben a stratégia azt ellenőrzi, hogy a sörnek van-e még maradék használata,
 * és ha igen, akkor beállítja a diák immunitását.
 */
public class DrunkStrategy implements ItemUseStrategy {
    
    /**
     * Végrehajtja a stratégiát egy adott diákon és tárgyon.
     * Ha a tárgynak van még használata (itt a sörnek), akkor növeli a diák immunitását és eltávolítja a tárgyat a diák inventory-jából.
     *
     * @param student A diák, aki a tárgyat használja.
     * @param item A tárgy, amelyre a stratégiát alkalmazzuk.
     */
    public void execute(Student student, Item item) {
        // Felhasználói interakció, hogy megtudjuk, van-e még használata a tárgynak.
        boolean result = App.t.askBoolean("Beer has uses left?");

        // Ha van még használata a tárgynak, beállítjuk a diák immunitását és eltávolítjuk a tárgyat.
        if(result) {
            student.setImmunity(10);
            student.removeItem(item);
        }
    }
}
