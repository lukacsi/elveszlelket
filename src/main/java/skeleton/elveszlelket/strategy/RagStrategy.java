package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.App;

/**
 * A RagStrategy implementálja az ItemUseStrategy interfészt, 
 * és egy specifikus viselkedést határoz meg a rongy használatára.
 * Ha a rongy nedves, akkor a jelenlegi szobában lévő összes tanár el lesz kábítva.
 */
public class RagStrategy implements ItemUseStrategy {

    /**
     * Végrehajtja a rongy használatának stratégiáját egy diákon.
     * Ellenőrzi, hogy a rongy nedves-e, és ha igen, akkor a szobában lévő
     * tanárok elkábulnak.
     *
     * @param student A diák, aki a rongyot használja.
     * @param item A rongy, amelynek használata a stratégiát aktiválja.
     */
    public void execute(Student student, Item item) {
        boolean result = App.t.askBoolean("Rag is wet?");
        if(result) {
            student.getRoom().stunTeachers();
        }
    }
}
