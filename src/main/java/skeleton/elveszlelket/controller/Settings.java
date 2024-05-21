package skeleton.elveszlelket.controller;

public class Settings {
    public int studentNum, teacherNum, cleanerNum, size;
    public float curse, gas, fals, item, door, oneway;

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
