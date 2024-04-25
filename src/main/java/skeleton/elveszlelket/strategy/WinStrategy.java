package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.item.Logar;

/**
 * A WinStrategy implementálja az ItemUseStrategy interfészt,
 * definiálva egy egyszerű stratégiát, amely beállítja a győzelmi feltételt
 * egy diák számára, amikor egy logart használ, mely felvételkor használódik.
 * Ez az osztály a játékokban használható, ahol egy logar használata
 * aktiválja a győzelmi forgatókönyvet a játékos számára.
 */
public class WinStrategy implements ItemUseStrategy {
    
    /**
     * Végrehajtja a győzelmi stratégiát egy adott diákon.
     * Amikor ez a metódus meghívódik, beállítja a győzelmi feltételt a diák számára.
     *
     * @param student A diák, aki a győzelmi feltétel beállításának alanyává válik.
     * @param item A tárgy, amelynek használatakor a győzelmi feltétel beállításra kerül.
     */
    public void execute(Student student, Item item) {
        Logar l = (Logar) item;
        if(l.getFalse()) {
            System.out.println("Átkozott labirintus! A logar hamis!");
        } else {
            student.setWinCondition();
        }
    }
}
