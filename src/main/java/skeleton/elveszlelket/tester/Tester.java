package skeleton.elveszlelket.tester;
import java.util.Scanner;
import java.util.Collection;
import java.util.HashMap;
import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Commands.*;

public class Tester {

    private static HashMap<String, Command> commands;
    private static HashMap<String, Student> hallgatok;
    private static HashMap<String, Teacher> oktatok;
    private static HashMap<String, Room> szobak;
    private static HashMap<String, Door> ajtok;
    private static HashMap<String, Item> targyak;

    public Tester() {
        commands = new HashMap<>();
        commands.put("MAKE", new Make());
        commands.put("ADDHTOROOM", new ADDHTOROOM());
        commands.put("ADDITOROOM", new ADDITOROOM());
        commands.put("ADDTOHUMAN", new ADDTOHUMAN());
        commands.put("CONNECTROOM", new CONNECTROOM());
        commands.put("CONNECTTRANS", new CONNECTTRANS());
        commands.put("REMOVE", new REMOVE());
        commands.put("CHECKWIN", new CHECKWIN());
        commands.put("CHECKLOSE", new CHECKLOSE());
        commands.put("MOVE", new MOVE());
        commands.put("MERGE", new MERGE());
        commands.put("SPLIT", new SPLIT());
        commands.put("DROP", new DROP());
        commands.put("PICKUP", new PICKUP());
        commands.put("USEITEM", new USEITEM());

        hallgatok = new HashMap<>();
        oktatok = new HashMap<>();
        szobak = new HashMap<>();
        ajtok = new HashMap<>();
        targyak = new HashMap<>();
    }

    public void removeHuman(Teacher t) {
        oktatok.remove(t);
    }

    public void removeHuman(Student s) {
        hallgatok.remove(s);
    }

    public void removeRoom(Room r) {
        szobak.remove(r);
    }

    public Command getCommand(Object key) {
        return commands.get(key);
    }

    public void addStudent(String name, Student s) {
        hallgatok.put(name, s);
    }

    public Student getStudent(Object key) {
        return hallgatok.get(key);
    }

    public void addTeacher(String name, Teacher t) {
        oktatok.put(name, t);
    }

    public Teacher getTeacher(Object key) {
        return oktatok.get(key);
    }

    public void addRoom(String name, Room r) {
        szobak.put(name, r);
    }

    public Room getRoom(Object key) {
        return szobak.get(key);
    }

    public void addDoor(String name, Door d) {
        ajtok.put(name, d);
    }

    public Door getDoor(Object key) {
        return ajtok.get(key);
    }

    public void addItem(String name, Item i) {
        targyak.put(name, i);
    }

    public Item getItem(Object key) {
        return targyak.get(key);
    }

    public int askInt(String uzenet) {
        System.out.println(uzenet);
        Scanner sc = new Scanner(System.in);
        int vissza = sc.nextInt();
        sc.close();
        return vissza;
    }

    public boolean askBoolean(String uzenet) {
        System.out.println(uzenet);
        Scanner sc = new Scanner(System.in);
        String vissza = sc.nextLine();
        sc.close();

        if(vissza.toLowerCase().equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public void removeStudent(String name) {
        hallgatok.remove(name);
    }

    public void removeTeacher(String name) {
        oktatok.remove(name);
    }

    public Collection<Student> getStudents() {
        return hallgatok.values();
    }

    // Method to check if a student has Logar item
    public boolean hasLogar(Student hallgato) 
    {
        return hallgato.hasLogar();
    }

    // Method to check if a student is dead
    public boolean halottE(Student hallgato) {
        return hallgato.halottE();
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        // egy ciklusban addig futunk ameddig end of line jel nincs
        while(sc.hasNextLine()) {
            String sor = sc.nextLine(); // kiolvassuk a sort
            executeCommand(sor.split(" ")); // szetszedjuk a space-k menten, majd meghivjuk az igy kapott
            // string tombbel az executeCommand-ot.
        }
        sc.close();
    }
    public void executeCommand(String[] params) {
        // a parameterek elso mezoje adja meg hogy milyen parancs lett meghivva(pl: MAKE)
        // kikeressuk, hogy a commands tombben adott parancshoz melyik parancs osztaly tartozik,
        // majd annak az osztalynak futtatjuk az execute fuggvenyet, ez minden parancs osztalynak mashogy van
        // definialva -> prog 3ból volt ez a megoldás az ilyen parancssoros programok keszitesere
        // a veremautomatas reszek korul.
        Command c = commands.get(params[0]);
        if(c != null) {
            c.execute(params, this);
        }
    }
}