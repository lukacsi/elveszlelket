package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.tester.Tester;

/**
 * Egy parancsot implementál, amely lehetővé teszi egy diák vagy tanár
 * hozzáadását egy megadott szobához.
 */
public class ADDHTOROOM implements skeleton.elveszlelket.tester.Commands.Command {
    /**
     * Végrehajtja a diák vagy tanár hozzáadását egy szobához.
     *
     * @param params A parancs paraméterei, amelyek tartalmazzák a diák/tanár nevét
     *               és a szoba nevét.
     * @param t      A Tester objektum, amely a parancsot végrehajtja, és
     *               tartalmazza a diákok, tanárok és szobák gyűjteményét.
     */
    public void execute(String[] params, Tester t) {
        if (params.length != 3) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }

        Student keresettStudent = t.getStudent(params[1]);
        Teacher keresettTeacher = t.getTeacher(params[1]);
        CleaningLady keresettCleaningLady = t.getCleaningLady(params[1]);
        Room keresettR = t.getRoom(params[2]); // hasonlóan ha keresettR nem null, akkor van már ilyen nevű
        if (keresettTeacher != null) {
            if (keresettTeacher.getRoom() != null) {
                System.out.println("Adott oktato mar megtalalhato egy szobaban.");
                return;
            }

            if (keresettR != null) {
                keresettR.addHuman(keresettTeacher);
                keresettTeacher.setCurrentRoom(keresettR);
                System.out.println("Sikeresen hozzaadta az oktatot a szobahoz.");
            } else {
                System.out.println("Parameterkent kapott szoba nincs meg feljegyezve.");
            }
        } else if (keresettStudent != null) { // felvett szoba a testernél
            if (keresettStudent.getRoom() != null) {
                System.out.println("Adott hallgato mar megtalalhato egy szobaban.");
                return;
            }

            if (keresettR != null) {
                keresettR.addHuman(keresettStudent);
                keresettStudent.setCurrentRoom(keresettR);
                System.out.println("Sikeresen hozzaadta a hallgatot a szobahoz.");
            } else {
                System.out.println("Parameterkent kapott Szoba nincs meg feljegyezve.");
            }
        } else if (keresettCleaningLady != null) { // felvett szoba a testernél
            if (keresettCleaningLady.getRoom() != null) {
                System.out.println("Adott takarito mar megtalalhato egy szobaban.");
                return;
            }

            if (keresettR != null) {
                keresettR.addHuman(keresettCleaningLady);
                keresettCleaningLady.setCurrentRoom(keresettR);
                System.out.println("Sikeresen hozzaadta a takaritot a szobahoz.");
            } else {
                System.out.println("Parameterkent kapott Szoba nincs meg feljegyezve.");
            } 
        } else {
            System.out.println("Parameterkent kapott human entitas nincs meg feljegyezve.");
        }
    }
}
