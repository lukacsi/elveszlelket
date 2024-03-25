package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Transistor;

/**
 * A TeleportStrategy implementálja az ItemUseStrategy interfészt, 
 * és egy specifikus viselkedést határoz meg a teleportálásra egy tranzisztor használatával.
 * Ha a tranzisztor rendelkezik egy párjával, a diák a pár helyére teleportálhat.
 */
public class TeleportStrategy implements ItemUseStrategy {
    
    /**
     * Végrehajtja a teleportálási stratégiát a tranzisztor használatával.
     * Ellenőrzi, hogy a tranzisztor rendelkezik-e párjával. Ha igen, a diák teleportál a pár helyére.
     *
     * @param student A diák, aki a tranzisztort használja.
     * @param item A tranzisztor, amelynek használata a teleportálást aktiválja.
     */
    public void execute(Student student, Item item) {
        Transistor t = (Transistor) item;
        if(!t.hasPair()) {
            System.out.println("Transistor has no pair");
            return;
        }
        Transistor tp = t.getPair();
        student.dropItem(item);
        t.unPair();
        tp.unPair();
        student.teleport(tp.getLocation());
    }
}
