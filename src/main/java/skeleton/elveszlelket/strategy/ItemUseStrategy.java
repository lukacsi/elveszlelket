package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

/**
 * Az ItemUseStrategy interfész egy stratégiai mintát definiál tárgyak használatához.
 * Különböző implementációk definiálhatók ezen interfész alapján, amelyek különböző
 * viselkedéseket írnak elő különféle tárgyak használata esetén.
 */
public interface ItemUseStrategy {

    /**
     * A metódus, amely meghatározza a tárgy használatának viselkedését egy diákon.
     * Az implementációnak definiálnia kell, hogy a diák hogyan reagál vagy milyen
     * hatással van rá a tárgy használata.
     *
     * @param student A diák, aki a tárgyat használja.
     * @param item A tárgy, amit a diák használni kíván.
     */
    public void execute(Student student, Item item);
}
