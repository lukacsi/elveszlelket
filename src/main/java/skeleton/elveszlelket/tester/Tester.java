package skeleton.elveszlelket.tester;

import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Commands.*;
import skeleton.elveszlelket.tester.Tests.*;

/**
 * Ez az osztály egy tesztelő keretrendszert biztosít, amely lehetővé teszi a
 * felhasználó
 * számára, hogy különböző parancsokat hajtson végre, befolyásolva ezzel a
 * diákokat,
 * tanárokat, szobákat, ajtókat és tárgyakat a szimulációs környezetben.
 */
public class Tester {

    private static HashMap<String, Command> commands;
    private static HashMap<String, Student> hallgatok;
    private static HashMap<String, Teacher> oktatok;
    private static HashMap<String, CleaningLady> takaritok;
    private static HashMap<String, Room> szobak;
    private static HashMap<String, Door> ajtok;
    private static HashMap<String, Item> targyak;
    public Scanner sc = new Scanner(System.in);
    public static TRandom r = new TRandom();

    /**
     * Konstruktor, amely inicializálja a parancsokat és a szimulációs objektumokat
     * tároló
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
        commands.put("TOGGLEGAS", new TOGGLEGAS());
        commands.put("TOGGLECURSE", new TOGGLECURSE());
        commands.put("INFO", new INFO());
        commands.put("SETUP", new SETUP());
        commands.put("CLEAR", new CLEAR());
        commands.put("INCREMENTTIME", new INCREMENTTIME());
        commands.put("LIST", new LIST());
        commands.put("REMOVEIFROMHUMAN", new REMOVEIFROMHUMAN());
        commands.put("REMOVEIFROMROOM", new REMOVEIFROMROOM());
        commands.put("TOGGLEFAKE", new TOGGLEFAKE());
        commands.put("T_CONNECTION", new T_CONNECTION());

        hallgatok = new HashMap<>();
        oktatok = new HashMap<>();
        takaritok = new HashMap<>();
        szobak = new HashMap<>();
        ajtok = new HashMap<>();
        targyak = new HashMap<>();
    }

    /**
     * @param Student. Keresett hallgató.
     * @return Keresett hallgató neve.
     *         Ha nincs a paraméternek megfelelő hallgató, üres sztringgel tér
     *         vissza.
     */
    public String getStudentName(Student s) {
        for (String st : hallgatok.keySet()) {
            if (hallgatok.get(st).equals(s)) {
                return st;
            }
        }
        return "";
    }
    public String getCleaningLadyName(CleaningLady c) {
        for (String st : takaritok.keySet()) {
            if(takaritok.get(st).equals(c)) {
                return st;
            }
        }
        return "";
    }

    /**
     * @param Room. Keresett szoba.
     * @return Keresett szoba neve.
     *         Ha nincs a paraméternek megfelelő szoba, üres sztringgel tér
     *         vissza.
     */
    public String getRoomName(Room r) {
        for (String st : szobak.keySet()) {
            if (szobak.get(st).equals(r)) {
                return st;
            }
        }
        return "";
    }

    /**
     * @param Door. Keresett ajtó.
     * @return Keresett ajtó neve.
     *         Ha nincs a paraméternek megfelelő ajtó, üres sztringgel tér
     *         vissza.
     */
    public String getDoorName(Door d) {
        for (String st : ajtok.keySet()) {
            if (ajtok.get(st).equals(d)) {
                return st;
            }
        }
        return "";
    }

    /**
     * @param Item. Keresett tárgy.
     * @return Keresett tárgy neve.
     *         Ha nincs a paraméternek megfelelő tárgy, üres sztringgel tér
     *         vissza.
     */
    public String getItemName(Item t) {
        for (String st : targyak.keySet()) {
            if (targyak.get(st).equals(t)) {
                return st;
            }
        }
        return "";
    }

    /**
     * @param Teacher. Keresett oktató.
     * @return Keresett oktató neve.
     *         Ha nincs a paraméternek megfelelő oktató, üres sztringgel tér
     *         vissza.
     */
    public String getTeacherName(Teacher t) {
        for (String st : oktatok.keySet()) {
            if (oktatok.get(st).equals(t)) {
                return st;
            }
        }
        return "";
    }

    /**
     * @param Human. Keresett ember.
     * @return Keresett ember neve.
     *         Ha nincs a paraméternek megfelelő ember, üres sztringgel tér
     *         vissza.
     */
    public String getHumanName(Human t) {
        for (String st : oktatok.keySet()) {
            if (oktatok.get(st).equals(t)) {
                return st;
            }
        }
        for (String st : hallgatok.keySet()) {
            if (hallgatok.get(st).equals(t)) {
                return st;
            }
        }
        for (String st : takaritok.keySet()) {
            if(takaritok.get(st).equals(t)) {
                return st;
            }
        }
        return "";
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

    public void removeHuman(CleaningLady c) {
        takaritok.remove(c);
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
     * Eltávolít egy ajtót az ajtók gyűjteményéből.
     *
     * @param r Az eltávolítandó ajtó.
     */
    public void removeDoor(Door d) {
        ajtok.remove(d);
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
     * @param s    A hozzáadandó diák objektum.
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
     * @param t    A hozzáadandó tanár objektum.
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

    public void addCleaningLady(String name, CleaningLady c) {
        takaritok.put(name, c);
    }

    public CleaningLady getCleaningLady(Object key) {
        return takaritok.get(key);
    }

    /**
     * Hozzáad egy szobát a szobák gyűjteményéhez.
     *
     * @param name A szoba neve, amely kulcsként szolgál a gyűjteményben.
     * @param r    A hozzáadandó szoba objektum.
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
     * @param d    A hozzáadandó ajtó objektum.
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
     * @param i    A hozzáadandó tárgy objektum.
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

    // /**
    //  * Egész számot kér be a felhasználótól.
    //  *
    //  * @param uzenet Az a szöveg, amit a felhasználónak megjelenítünk a bekérés
    //  *               előtt.
    //  * @return A felhasználó által beírt egész szám.
    //  */
    // public int askInt(String uzenet) {
    //     System.out.println(uzenet);
    //     int vissza = sc.nextInt();
    //     return vissza;
    // }

    // /**
    //  * Logikai értéket kér be a felhasználótól.
    //  *
    //  * @param uzenet Az a szöveg, amit a felhasználónak megjelenítünk a bekérés
    //  *               előtt.
    //  * @return A felhasználó által beírt logikai érték: igaz vagy hamis.
    //  */
    // public boolean askBoolean(String uzenet) {
    //     System.out.println(uzenet);
    //     String vissza = sc.nextLine();
    //     if (vissza.toLowerCase().equals("true")) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

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

    public void removeCleaningLady(String name) {
        takaritok.remove(name);
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
     * Lekérdezi a oktatók gyűjteményét.
     *
     * @return A jelenlegi oktatók gyűjteménye.
     */
    public Collection<Teacher> getTeachers() {
        return oktatok.values();
    }

    public Collection<CleaningLady> getCleaningLadies() {
        return takaritok.values();
    }

    /**
     * Lekérdezi a tárgyak gyűjteményét.
     *
     * @return A jelenlegi tárgyak gyűjteménye.
     */
    public Collection<Item> getItems() {
        return targyak.values();
    }

    /**
     * Lekérdezi a szobák gyűjteményét.
     *
     * @return A jelenlegi szobák gyűjteménye.
     */
    public Collection<Room> getRooms() {
        return szobak.values();
    }

    /**
     * Lekérdezi a ajtók gyűjteményét.
     *
     * @return A jelenlegi ajtók gyűjteménye.
     */
    public Collection<Door> getDoors() {
        return ajtok.values();
    }

    public List<String> getKeys(int type) {
        List<String> keys = new ArrayList<>();
        if(type == 0 || type == 1) {
            keys.addAll(new ArrayList<>(hallgatok.keySet()));
        }
        if(type == 0 || type == 2) {
            keys.addAll(new ArrayList<>(oktatok.keySet()));
        }
        if(type == 0 || type == 3) {
            keys.addAll(new ArrayList<>(takaritok.keySet()));
        }
        if(type == 0 || type == 4) {
            keys.addAll(new ArrayList<>(szobak.keySet()));
        }
        if(type == 0 || type == 5) {
            keys.addAll(new ArrayList<>(ajtok.keySet()));
        }
        if(type == 0 || type == 6) {
            keys.addAll(new ArrayList<>(targyak.keySet()));
        }
        if(type == 7) {
            keys.addAll(new ArrayList<>(hallgatok.keySet()));
            keys.addAll(new ArrayList<>(oktatok.keySet()));
            keys.addAll(new ArrayList<>(takaritok.keySet()));
        }
        return keys;
    }

    /**
     * Ellenőrzi, hogy a megadott diáknak van-e Logar tárgya.
     *
     * @param hallgato A vizsgálandó diák.
     * @return Igaz, ha a diáknak van Logar tárgya, egyébként hamis.
     */
    public boolean hasLogar(Student hallgato) {
        ///// NOTE TO SELF: WINCONDITONRA KÉNE CSEKKELNI HOGY LEKEZELJÜK A HAMIS LOGART
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
     * A fő hallgatási ciklus, amely várja a felhasználói bemenetet és végrehajtja a
     * megfelelő parancsokat.
     */
    public void listen() {
        String row = sc.nextLine();
        while(!row.toLowerCase().equals("exit")){
            if(row.equals("TOGGLERANDOM")) {
                r.toggleRandom();
                System.out.println("Randomness " + ((r.random)?"enabled.":"disabled."));
                row = sc.nextLine();
            } else {
                executeCommand(row.split(" "));
                row = sc.nextLine();
            }
        }
    }

    /**
     * Egy parancsot hajt végre a megadott paraméterekkel.
     *
     * @param params A parancs paraméterei.
     */
    public void executeCommand(String[] params) {
        // a parameterek elso mezoje adja meg hogy milyen parancs lett meghivva(pl:
        // MAKE)
        // kikeressuk, hogy a commands tombben adott parancshoz melyik parancs osztaly
        // tartozik,
        // majd annak az osztalynak futtatjuk az execute fuggvenyet, ez minden parancs
        // osztalynak mashogy van
        // definialva -> prog 3ból volt ez a megoldás az ilyen parancssoros programok
        // keszitesere
        // a veremautomatas reszek korul.
        Command c = commands.get(params[0]);
        if (c != null) {
            c.execute(params, this);
        }
    }

    /*
     * @param0 String[]. Olyan sztringeket tartalmaz, amelyekkel nem terhet vissza a
     * függény.
     * 
     * @param1 String. Esetlegesen meglehet adni hogy milyen targynak keresunk
     * nevet,
     * ekkor a nev prefix(base) a targy tipusanak a neve lesz.
     * 
     * @return String. Vissza ad egy olyan nevet, ami meg
     * egy objektumhoz sincs rendelve a jatekban.
     * A while ciklus ellenorzi, hogy adott "nev" valtozoban
     * tarolt nevvel rendelkezik-e mar valamilyen objektum,
     * ha igen, a "nev" valtozot megvaltoztatjuk az alap nev prefixre(base) plusz
     * egy szamra.
     * Ha tulleptunk 100000-en, akkor a nev prefixhez hozza adunk egy random
     * karaktert( base = base.concat(String.valueOf(karakterek.charAt(i))) ) es ujra
     * kezdjuk az ellenorzest.
     * Ha 1000-szer ujra kezdtuk az ellenorzest hibat irunk ki es ures stringgel
     * terunk vissza.
     */
    public String getAvailableName(List<String> tiltottNevek, String targyTipusa) {
        String base = "";
        if (!targyTipusa.equals("")) {
            base = targyTipusa;
        } else {
            base = "nev";
        }
        String karakterek = "abcdefghijklmnopqrstuvwxyz";
        String nev = base;
        int i = 0;
        int hanyszorIndultUjra = 0;
        boolean fusstovabb = true;
        while (fusstovabb) {
            if (i <= 100000) {
                i++;
            } else {
                int randomertek = r.nextInt(karakterek.length());
                base = base.concat(String.valueOf(karakterek.charAt(i)));
                i = 0;
                hanyszorIndultUjra++;
            }
            if (hanyszorIndultUjra == 1000) {
                System.out.println("Nem sikerult automatikusan nevet talalni. Ures nev vissza adva.");
                return "";
            }
            nev = base.concat(String.valueOf(i));

            if (getStudent(nev) != null || getTeacher(nev) != null || getItem(nev) != null || getRoom(nev) != null
                    || getDoor(nev) != null) {
                continue;
            } else {
                boolean voltEgyezes = false;
                for (int u = 0; u < tiltottNevek.size(); u++) {
                    if (tiltottNevek.get(u).equals(nev)) {
                        voltEgyezes = true;
                    }
                }
                if (!voltEgyezes) {
                    fusstovabb = false;
                }
            }
        }

        return nev;
    }

    public void clear() {
        hallgatok.clear();
        oktatok.clear();
        targyak.clear();
        ajtok.clear();
        szobak.clear();
        
    }
}