package skeleton.elveszlelket.strategy;

import java.util.Random;

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
     * Ezenkívül esetlegesen elejt egy tárgyat.
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
            App.t.removeItem(item);
            System.out.println("Beer has been used!");
            dropRandomItem(student);
        }
    }

    private void dropRandomItem(Student student) {
        // Meghatározzuk, hány tárgy van a hallgatónál
        int numItems = student.getInventory().size();
        if (numItems > 0) {
            // Véletlenszerűen kiválasztunk egy tárgyat
            Random random = new Random();
            int index = random.nextInt(numItems);

            // Tárgy eltávolítása a hallgatótól és eldobása a szobába
            Item itemToDrop = student.getInventory().get(index);
            boolean dropped = student.dropItem(itemToDrop);
            if (dropped) {
                System.out.println("Student dropped item: " + itemToDrop.getName());
            } else {
                System.out.println("Failed to drop item: " + itemToDrop.getName());
            }
        }
    }
}
