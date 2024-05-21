package skeleton.elveszlelket.controller;


/**
 * A Settings osztály a játék beállításait tárolja, mint például a diákok, tanárok és
 * takarítónők számát, a pálya méretét, valamint különböző játékelemek valószínűségeit.
 */
public class Settings {
    public int studentNum, teacherNum, cleanerNum, size;
    public float curse, gas, fals, item, door, oneway;

     /**
     * Létrehoz egy új Settings példányt a megadott paraméterekkel.
     *
     * @param studentNum A diákok száma.
     * @param teacherNum A tanárok száma.
     * @param cleanerNum A takarítónők száma.
     * @param size A pálya mérete.
     * @param curse Az átok valószínűsége.
     * @param gas A gáz valószínűsége.
     * @param fals A hamis tárgyak valószínűsége.
     * @param item A tárgyak valószínűsége.
     * @param door Az ajtók valószínűsége.
     * @param oneway Az egyirányú ajtók valószínűsége.
     */
    public Settings(int studentNum, int teacherNum, int cleanerNum, int size,
            float curse, float gas, float fals, float item,
            float door, float oneway) {
        this.studentNum = studentNum;
        this.teacherNum = teacherNum;
        this.cleanerNum = cleanerNum;
        this.size = size;
        this.curse = curse;
        this.gas = gas;
        this.fals = fals;
        this.item = item;
        this.door = door;
        this.oneway = oneway;
    }
}
