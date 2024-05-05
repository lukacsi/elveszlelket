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

public class Teststrategy {

    private Tester tester;
    private Student student;
    private Room room;
    private Item item;

    @Before
    public void setUp() {
        tester = new Tester();
        student = new Student();
        room = new Room();
        item = new Item("TesztTárgy");
        room.addItem(item);
        student.setCurrentRoom(room);
    }

    @Test
    public void testDrunkStrategy() {
        // A tárgyhoz rendeljük a drunkStrategy-t
        item.setStrategy(new DrunkStrategy());

        // A USEITEM parancs végrehajtása a tárgy használatának szimulálására
        USEITEM useItemCommand = new USEITEM();
        useItemCommand.execute(new String[]{"USEITEM", "TesztDiák", "TesztTárgy"}, tester);

        // Ellenőrizzük, hogy a diák immunitása nőtt-e a tárgy használata után
        assertEquals(10, student.getImmunity());
    }

    @Test
    public void testGasProtectionStrategy() {
        // A tárgyhoz rendeljük a testGasProtectionStrategy
        item.setStrategy(new GasProtectionStrategy());

        // A USEITEM parancs végrehajtása a tárgy használatának szimulálására
        USEITEM useItemCommand = new USEITEM();
        useItemCommand.execute(new String[]{"USEITEM", "TesztDiák", "TesztTárgy"}, tester);

        // Ellenőrizzük, hogy a diák gázvédelmi szintje nőtt-e a tárgy használata után
        assertEquals(30, student.getGasProtection());
    }

    @Test
    public void testTeleportStrategy() {
        // Létrehozunk egy párt a Tranzisztor tárgynak
        Transistor párosítottTranzisztor = new Transistor();
        Transistor eredetiTranzisztor = (Transistor) item;
        eredetiTranzisztor.addPair(párosítottTranzisztor);

        // A tárgyhoz rendeljük a testTeleportStrategy
        item.setStrategy(new TeleportStrategy());

        // A USEITEM parancs végrehajtása a tárgy használatának szimulálására
        USEITEM useItemCommand = new USEITEM();
        useItemCommand.execute(new String[]{"USEITEM", "TesztDiák", "TesztTárgy"}, tester);

        // Ellenőrizzük, hogy a diák a párosított tranzisztor helyére teleportált-e
        assertEquals(párosítottTranzisztor.getLocation(), student.getCurrentRoom());
    }
}

public class CommandTests {

    private Tester tester;
    private Student student;
    private Room room;
    private Door door;
    private Item item;

    @Before
    public void setUp() {
        tester = new Tester();
        student = new Student();
        room = new Room();
        door = new Door(room, room, "Tesztajtó");
        item = new Item("Teszttárgy");
        tester.addStudent("Teszthallgató", student);
        tester.addRoom("Tesztszoba", room);
        tester.addDoor("Tesztajtó", door);
        tester.addItem("Teszttárgy", item);
    }

    @Test
    public void tesztMakeParancs() {
        // Teszteljük, hogy a MAKE parancs létrehozza-e a megfelelő objektumot
        MAKE makeCommand = new MAKE();
        makeCommand.execute(new String[]{"MAKE", "Student", "Teszthallgató"}, tester);
        assertNotNull(tester.getStudent("Teszthallgató"));
    }

    @Test
    public void tesztAddHToRoomParancs() {
        // Teszteljük, hogy az ADDHTOROOM parancs hozzáadja-e a hallgatót a megfelelő szobához
        ADDHTOROOM addHToRoomCommand = new ADDHTOROOM();
        addHToRoomCommand.execute(new String[]{"ADDHTOROOM", "Teszthallgató", "Tesztszoba"}, tester);
        assertTrue(room.getOccupants().contains(student));
    }

    @Test
    public void tesztAddToHumanParancs() {
        // Teszteljük, hogy az ADDTOHUMAN parancs hozzáadja-e a tárgyat a megfelelő hallgatóhoz
        ADDTOHUMAN addToHumanCommand = new ADDTOHUMAN();
        addToHumanCommand.execute(new String[]{"ADDTOHUMAN", "Teszttárgy", "Teszthallgató"}, tester);
        assertTrue(student.getItems().contains(item));
    }

    @Test
    public void tesztAddIToRoomParancs() {
        // Teszteljük, hogy az ADDITOROOM parancs hozzáadja-e a tárgyat a megfelelő szobához
        ADDITOROOM addIToRoomCommand = new ADDITOROOM();
        addIToRoomCommand.execute(new String[]{"ADDITOROOM", "Teszttárgy", "Tesztszoba"}, tester);
        assertTrue(room.getItems().contains(item));
    }

    @Test
    public void tesztConnectRoomParancs() {
        // Teszteljük, hogy a CONNECTROOM parancs összekapcsolja-e a megfelelő szobákat
        CONNECTROOM connectRoomCommand = new CONNECTROOM();
        connectRoomCommand.execute(new String[]{"CONNECTROOM", "Tesztszoba", "Tesztszoba", "Tesztajtó"}, tester);
        assertTrue(room.getDoors().contains(door));
    }
}