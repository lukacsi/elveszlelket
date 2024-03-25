import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Commands.PICKUP;
import skeleton.elveszlelket.tester.Tester;

public class PickupParancsTeszt {

    private Tester tester;
    private Student hallgato;
    private Room szoba;
    private Item targy;

    @Before
    public void felkeszules() {
        tester = new Tester();
        hallgato = new Student();
        szoba = new Room();
        targy = new Item("Teszttárgy"); // Teszttárgy létrehozása
        szoba.addItem(targy); // A teszttárgy hozzáadása a szobához
        hallgato.setCurrentRoom(szoba); // A hallgató jelenlegi szobájának beállítása
    }

    @Test
    public void tesztPickupParancs() {
        // Ellenőrizzük, hogy a tárgy kezdetben a szobában van
        assertTrue(szoba.getItems().contains(targy));

        // Végrehajtjuk a PICKUP parancsot, hogy szimuláljuk a tárgy felvételét
        PICKUP pickupParancs = new PICKUP();
        pickupParancs.execute(new String[]{"PICKUP", "Teszttárgy", "Teszthallgató"}, tester);

        // Ellenőrizzük, hogy a tárgy most már a hallgató birtokában van
        assertTrue(hallgato.getItems().contains(targy));

        // Ellenőrizzük, hogy a tárgy már nincs a szobában a felvétel után
        assertFalse(szoba.getItems().contains(targy));
    }
}

public class DropParancsTeszt {

    private Tester tester;
    private Student hallgato;
    private Room szoba;
    private Item targy;

    @Before
    public void felkeszules() {
        tester = new Tester();
        hallgato = new Student();
        szoba = new Room();
        targy = new Item("Teszttárgy"); // Teszttárgy létrehozása
        hallgato.setCurrentRoom(szoba); // A hallgató jelenlegi szobájának beállítása
        hallgato.pickupItem(targy); // A hallgató felveszi a tárgyat
    }

    @Test
    public void tesztDropParancs() {
        // Ellenőrizzük, hogy a tárgy kezdetben a hallgató birtokában van
        assertTrue(hallgato.getItems().contains(targy));

        // Végrehajtjuk a DROP parancsot, hogy szimuláljuk a tárgy eldobását
        DROP dropParancs = new DROP();
        dropParancs.execute(new String[]{"DROP", "Teszttárgy", "Teszthallgató"}, tester);

        // Ellenőrizzük, hogy a tárgy már nincs a hallgató birtokában az eldobás után
        assertFalse(hallgato.getItems().contains(targy));

        // Ellenőrizzük, hogy a tárgy most már a szobában van az eldobás után
        assertTrue(szoba.getItems().contains(targy));
    }
}

public class UseItemParancsTeszt {

    private Tester tester;
    private Student hallgato;
    private Room szoba;
    private Item targy;

    @Before
    public void felkeszules() {
        tester = new Tester();
        hallgato = new Student();
        szoba = new Room();
        targy = new Item("Teszttárgy"); // Teszttárgy létrehozása
        szoba.addItem(targy); // A teszttárgy hozzáadása a szobához
        hallgato.setCurrentRoom(szoba); // A hallgató jelenlegi szobájának beállítása
    }

    @Test
    public void tesztUseItemParancs() {
        // Ellenőrizzük, hogy a tárgy kezdetben a szobában van
        assertTrue(szoba.getItems().contains(targy));

        // Végrehajtjuk a USEITEM parancsot, hogy szimuláljuk a tárgy használatát a hallgató által
        USEITEM useItemParancs = new USEITEM();
        useItemParancs.execute(new String[]{"USEITEM", "Teszthallgató", "Teszttárgy"}, tester);

        // Ellenőrizzük, hogy a tárgy már nincs a szobában a használat után
        assertFalse(szoba.getItems().contains(targy));

        // Ellenőrizzük, hogy a tárgy használata sikeres volt
        assertFalse(hallgato.getItems().contains(targy)); // Feltételezve, hogy a tárgy használat után elfogy vagy megváltozik
    }
}

public class FFP2MaskUseTest {

    private Tester tester;
    private Student student;
    private Item ffp2Mask;

    @Before
    public void setUp() {
        tester = new Tester();
        student = new Student();
        ffp2Mask = new FFP2Mask();
        student.pickupItem(ffp2Mask); // Hallgató felveszi az FFP2 maszkot
    }

    @Test
    public void testFFP2MaskUse() {
        // Ellenőrizzük, hogy az FFP2 maszk kezdetben a hallgató birtokában van-e
        assertTrue(student.getItems().contains(ffp2Mask));

        // Végrehajtjuk a USEITEM parancsot, hogy szimuláljuk az FFP2 maszk használatát
        USEITEM useItemCommand = new USEITEM();
        useItemCommand.execute(new String[]{"USEITEM", "TestHallgató", "FFP2Mask"}, tester);

        // Ellenőrizzük, hogy az FFP2 maszk használat után már nincs a hallgató birtokában
        assertFalse(student.getItems().contains(ffp2Mask));

        assertEquals(2, ((FFP2Mask)ffp2Mask).getUses()); // Az FFP2 maszk használatok számának csökkenése
    }
}
