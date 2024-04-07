package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

/**
 * A NeutralizeGasStrategy egy olyan stratégia, amelyet akkor alkalmazunk,
 * amikor egy légfrissítőt használnak, ami semlegesíti a gázhatást a szobában.
 */
public class NeutralizeGasStrategy implements ItemUseStrategy {

    @Override
    public void execute(Student student, Item item) {
        Room room = student.getRoom();

        // Ellenőrizzük, hogy van-e gáz a szobában
        if (room.hasGas()) {
            // Gáz van, használjuk a légfrissítőt
            room.setGas(false); // Gáz semlegesítése
            System.out.println("A légfrissítő semlegesítette a szoba gázhatását.");
        } else {
            // Nincs gáz, nincs teendő
            System.out.println("Nincs gáz a szobában.");
        }
    }
}