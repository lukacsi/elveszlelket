package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Beer;
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
     * Ezenkívül esetlegesen elejt egy tárgyat.
     * 
     * @param student A diák, aki a tárgyat használja.
     * @param item A tárgy, amelyre a stratégiát alkalmazzuk.
     */
    public void execute(Student student, Item item) {// Ha van még használata a tárgynak, beállítjuk a diák immunitását és eltávolítjuk a tárgyat.
        Beer b = (Beer) item;
        if(b.getUses() > 0) {
            student.setImmunity(5);
            student.removeItem(item);
            System.out.println("Beer has been used!");
            student.dropRandomItem();
        }
    }
}
