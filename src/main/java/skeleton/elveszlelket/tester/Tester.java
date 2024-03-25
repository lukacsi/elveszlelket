package skeleton.elveszlelket.tester;
import java.util.Scanner;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Commands.*;
/**
 * Ez az osztály egy tesztelő keretrendszert biztosít, amely lehetővé teszi a felhasználó
 * számára, hogy különböző parancsokat hajtson végre, befolyásolva ezzel a diákokat,
 * tanárokat, szobákat, ajtókat és tárgyakat a szimulációs környezetben.
 */
public class Tester {

    private static HashMap<String, Command> commands;
    private static HashMap<String, Student> hallgatok;
    private static HashMap<String, Teacher> oktatok;
    private static HashMap<String, Room> szobak;
    private static HashMap<String, Door> ajtok;
    private static HashMap<String, Item> targyak;
    private Scanner sc = new Scanner(System.in);
    /**
     * Konstruktor, amely inicializálja a parancsokat és a szimulációs objektumokat tároló
     * gyűjteményeket.
     */
    public Tester() {
        commands = new HashMap<>();
        commands.put("MAKE", new MAKE());
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

    /**
     * Eltávolít egy tanárt az oktatók gyűjteményéből.
     *
     * @param t Az eltávolítandó tanár.
     */
    public void removeHuman(Teacher t) {
        oktatok.remove(t);
    }

    /**
     * Eltávolít egy diákot a hallgatók gyűjteményéből.
     *
     * @param s Az eltávolítandó diák.
     */

    public void removeHuman(Student s) {
        hallgatok.remove(s);
    }

    /**
     * Eltávolít egy szobát a szobák gyűjteményéből.
     *
     * @param r Az eltávolítandó szoba.
     */
    public void removeRoom(Room r) {
        szobak.remove(r);
    }

    /**
     * Visszaad egy parancsot a megadott kulcs alapján.
     *
     * @param key A parancs kulcsa.
     * @return A megfelelő parancs objektum.
     */
    public Command getCommand(Object key) {
        return commands.get(key);
    }

    /**
     * Hozzáad egy diákot a hallgatók gyűjteményéhez.
     *
     * @param name A diák neve, amely kulcsként szolgál a gyűjteményben.
     * @param s A hozzáadandó diák objektum.
     */
    public void addStudent(String name, Student s) {
        hallgatok.put(name, s);
    }

    /**
     * Lekérdez egy diákot a hallgatók gyűjteményéből a megadott név alapján.
     *
     * @param key A diák neve, amely kulcsként szolgál a gyűjteményben.
     * @return A lekérdezett diák objektum, vagy null, ha nincs ilyen nevű diák.
     */
    public Student getStudent(Object key) {
        return hallgatok.get(key);
    }

    /**
     * Hozzáad egy tanárt az oktatók gyűjteményéhez.
     *
     * @param name A tanár neve, amely kulcsként szolgál a gyűjteményben.
     * @param t A hozzáadandó tanár objektum.
     */
    public void addTeacher(String name, Teacher t) {
        oktatok.put(name, t);
    }

    /**
     * Lekérdez egy tanárt az oktatók gyűjteményéből a megadott név alapján.
     *
     * @param key A tanár neve, amely kulcsként szolgál a gyűjteményben.
     * @return A lekérdezett tanár objektum, vagy null, ha nincs ilyen nevű tanár.
     */
    public Teacher getTeacher(Object key) {
        return oktatok.get(key);
    }

    /**
     * Hozzáad egy szobát a szobák gyűjteményéhez.
     *
     * @param name A szoba neve, amely kulcsként szolgál a gyűjteményben.
     * @param r A hozzáadandó szoba objektum.
     */
    public void addRoom(String name, Room r) {
        szobak.put(name, r);
    }

    /**
     * Lekérdez egy szobát a szobák gyűjteményéből a megadott név alapján.
     *
     * @param key A szoba neve, amely kulcsként szolgál a gyűjteményben.
     * @return A lekérdezett szoba objektum, vagy null, ha nincs ilyen nevű szoba.
     */
    public Room getRoom(Object key) {
        return szobak.get(key);
    }

    /**
     * Hozzáad egy ajtót az ajtók gyűjteményéhez.
     *
     * @param name Az ajtó neve, amely kulcsként szolgál a gyűjteményben.
     * @param d A hozzáadandó ajtó objektum.
     */
    public void addDoor(String name, Door d) {
        ajtok.put(name, d);
    }

    /**
     * Lekérdez egy ajtót az ajtók gyűjteményéből a megadott név alapján.
     *
     * @param key Az ajtó neve, amely kulcsként szolgál a gyűjteményben.
     * @return A lekérdezett ajtó objektum, vagy null, ha nincs ilyen nevű ajtó.
     */
    public Door getDoor(Object key) {
        return ajtok.get(key);
    }

    /**
     * Hozzáad egy tárgyat a tárgyak gyűjteményéhez.
     *
     * @param name A tárgy neve, amely kulcsként szolgál a gyűjteményben.
     * @param i A hozzáadandó tárgy objektum.
     */
    public void addItem(String name, Item i) {
        targyak.put(name, i);
    }
    
    /**
     * Eltávolít egy tárgyat a tárgyak gyűjteményéből.
     *
     * @param item Az eltávolítandó tárgy objektuma.
     */
    public void removeItem(Item item) {
        // Megkeressük azon kulcsot (tárgy nevét), amelynek az értéke az adott tárgy.
        String itemKey = null;
        for (Map.Entry<String, Item> entry : targyak.entrySet()) {
            if (entry.getValue().equals(item)) {
                itemKey = entry.getKey();
                break;
            }
        }
        
        // Ha megtaláltuk a kulcsot, eltávolítjuk a tárgyat a gyűjteményből.
        if (itemKey != null) {
            targyak.remove(itemKey);
            System.out.println(itemKey + " tárgy sikeresen eltávolítva.");
        } else {
            System.out.println("A tárgy nem található a gyűjteményben.");
        }
    }

    /**
     * Lekérdez egy tárgyat a tárgyak gyűjteményéből a megadott név alapján.
     *
     * @param key A tárgy neve, amely kulcsként szolgál a gyűjteményben.
     * @return A lekérdezett tárgy objektum, vagy null, ha nincs ilyen nevű tárgy.
     */
    public Item getItem(Object key) {
        return targyak.get(key);
    }

    /**
     * Egész számot kér be a felhasználótól.
     *
     * @param uzenet Az a szöveg, amit a felhasználónak megjelenítünk a bekérés előtt.
     * @return A felhasználó által beírt egész szám.
     */
    public int askInt(String uzenet) {
        System.out.println(uzenet);
        int vissza = sc.nextInt();
        return vissza;
    }

    /**
     * Logikai értéket kér be a felhasználótól.
     *
     * @param uzenet Az a szöveg, amit a felhasználónak megjelenítünk a bekérés előtt.
     * @return A felhasználó által beírt logikai érték: igaz vagy hamis.
     */
    public boolean askBoolean(String uzenet) {
        System.out.println(uzenet);
        String vissza = sc.nextLine();
        if(vissza.toLowerCase().equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Eltávolít egy diákot a hallgatók gyűjteményéből.
     *
     * @param name A diák neve, amely kulcsként szolgál a gyűjteményben.
     */
    public void removeStudent(String name) {
        hallgatok.remove(name);
    }

    /**
     * Eltávolít egy tanárt az oktatók gyűjteményéből.
     *
     * @param name A tanár neve, amely kulcsként szolgál a gyűjteményben.
     */
    public void removeTeacher(String name) {
        oktatok.remove(name);
    }

    /**
     * Lekérdezi a hallgatók gyűjteményét.
     *
     * @return A jelenlegi hallgatók gyűjteménye.
     */
    public Collection<Student> getStudents() {
        return hallgatok.values();
    }

    /**
     * Ellenőrzi, hogy a megadott diáknak van-e Logar tárgya.
     *
     * @param hallgato A vizsgálandó diák.
     * @return Igaz, ha a diáknak van Logar tárgya, egyébként hamis.
     */
    public boolean hasLogar(Student hallgato) 
    {
        return hallgato.hasLogar();
    }

    /**
     * Ellenőrzi, hogy a megadott diák halott-e.
     *
     * @param hallgato A vizsgálandó diák.
     * @return Igaz, ha a diák halott, egyébként hamis.
     */
    public boolean halottE(Student hallgato) {
        return hallgato.halottE();
    }

    /**
     * A fő hallgatási ciklus, amely várja a felhasználói bemenetet és végrehajtja a megfelelő parancsokat.
     */
    public void listen() {
        while(true) {
            String sor = sc.nextLine(); // Read the line
    
            // Check if the user wants to exit
            if ("exit".equalsIgnoreCase(sor.trim())) {
                sc.close();
                break;
            }
    
            executeCommand(sor.split(" ")); // Split the line and execute the command
        }
    }
    /**
     * Egy parancsot hajt végre a megadott paraméterekkel.
     *
     * @param params A parancs paraméterei.
     */
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