package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.App;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.item.Item;

/**
 * A GasStrategy egy olyan stratégia, amelyet akkor alkalmazunk,
 * amikor egy diák használ egy camembertet, ami befolyásolhatja a szobában lévő
 * gázt.
 * Ez a stratégia ellenőrzi, hogy egy feltétel (itt példaként a "camembert
 * zárt-e?")
 * teljesül-e, és ha igen, akkor a diák eldobja a tárgyat, és a szoba gázos
 * állapotba kerül.
 */
public class GasStrategy implements ItemUseStrategy {

    /**
     * Végrehajtja a gázstratégiát egy adott diákon, amikor egy tárgyat használ.
     * Ha a megadott feltétel igaz (a camembert zárt), akkor a diák eldobja a
     * tárgyat,
     * és a szobába gáz kerül.
     *
     * @param student A diák, aki a tárgyat használja.
     * @param item    A tárgy, amelynek használata során a stratégia aktiválódik.
     */
    public void execute(Student student, Item item) {
        Room r = student.getRoom();
        boolean result = App.t.askBoolean("Is camembert unopened?");

        if (result) {
            student.dropItem(item);
            App.t.removeItem(item);
            r.setGas(true);
        }
    }
}
