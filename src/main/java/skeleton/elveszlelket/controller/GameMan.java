package skeleton.elveszlelket.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.CleaningLady;
import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.gui.DoorView;
import skeleton.elveszlelket.gui.Screen;

/**
 * A játék fő irányító osztálya, kezeli
 * a szereplők mozgatását és a képernyő frissítését.
 */
public class GameMan {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<CleaningLady> cleaners;
    private List<Room> map;
    private Queue<Student> sQueue;
    public int round;
    private Stage stage;
    private Random rand;
    private Student jelenlegiJatekos;

    public Student getCurrentPlayer() {
        return this.jelenlegiJatekos;
    }

    /**
     * Létrehoz egy új GameMan példányt a megadott beállításokkal.
     *
     * @param settings A játék beállításai.
     * @param stage    A pálya, amelyen a jelenetek megjelennek.
     */
    public GameMan(Settings settings, Stage stage) {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.cleaners = new ArrayList<>();
        this.sQueue = new LinkedList<>();
        rand = new Random();
        this.stage = stage;
        round = 0;
        for (int i = 0; i < settings.studentNum; i++) {
            students.add(new Student());
        }
        for (int i = 0; i < settings.teacherNum; i++) {
            teachers.add(new Teacher());
        }
        for (int i = 0; i < settings.cleanerNum; i++) {
            cleaners.add(new CleaningLady());
        }

        MapMan m = new MapMan(settings.size, settings.curse, settings.gas, settings.fals, settings.item, settings.door,
                settings.oneway, teachers, students,
                cleaners);
        map = m.init();

        round = 1;
        sQueue.addAll(students);
    }

    /**
     * Lejátssza a következő kört a játékban.
     */
    public void playRound() {
        if (sQueue.isEmpty()) {
            moveTeachers();
            moveCleaners();
            round++;
            for (int i = 0; i < Math.floor(map.size() / 3); i++) {
                map.get(rand.nextInt(map.size())).changeDoorStatus();
            }
            for (Student s : students) {
                s.decreaseGasProtection();
                s.decreaseImmunity();
                s.decreaseStun(1);
            }
            for (Teacher t : teachers) {
                t.decreaseStun(1);
            }
            sQueue.addAll(students);
        }
        Student s = sQueue.poll();
        DrawScene(s);
    }

    /**
     * A tanárok mozgatását végzi a következő szobába.
     */
    private void moveTeachers() {
        for (Teacher gajdos : teachers) {
            Door door = gajdos.getRoom().getDoorAt(rand.nextInt(0, 11));
            if (door != null) {
                door.accept(gajdos);
            }
        }
    }

    /**
     * A takarítónők mozgatását végzi a következő szobába.
     */
    private void moveCleaners() {
        for (CleaningLady gizi : cleaners) {
            Door door = gizi.getRoom().getDoorAt(rand.nextInt(0, 11));
            if (door != null) {
                door.accept(gizi);
            }
        }
    }

    /**
     * Kirajzolja a megadott diák számára a jelenetet.
     *
     * @param s A diák, akinek a jelenet kirajzolásra kerül.
     */
    // Na ez azt csinálja hogy hasheli a roomot és aszerint pakolja a cuccokat.
    // AZ ELDOBOTT ITEMEKET NEM TUDOM HOGYAN KÉNE
    private void DrawScene(Student s) {
        Room r = s.getRoom();
        // MAX 10 MIN 5 (EBBŐL 2 FAL)
        int sizew = (abs(r.hashCode()) % 6) + 5;
        int sizeh = (abs(sizew * r.hashCode()) % 6) + 5;
        float WIDTH = sizew * 40.0f;
        float HEIGHT = sizeh * 40.0f;
        System.out.println(r.hashCode());
        System.out.println("width: " + WIDTH + " height: " + HEIGHT);

        if (r.getView() == null) {
            for (Item i : r.getItems()) {
                if (i.getView() != null) {
                    continue;
                }
                float w = abs(i.hashCode()) % (sizew - 2);
                float h = (abs(i.hashCode() * sizew)) % (sizeh - 2);
                i.setView((w + 1) * 40, (h + 1) * 40);
                System.out.println(i.getClass().toString() + " " + (w + 1) * 40 + " " + (h + 1) * 40);
            }

            List<Pair<Integer, Integer>> indx = new ArrayList<>();
            System.out.println(r.getDoors().size());
            for (Door door : r.getDoors()) {
                if (door.getView() != null) {
                    continue;
                }
                boolean vege = false;
                while (!vege) {
                    int x;
                    int y;
                    if (rand.nextFloat() > 0.5f) {
                        if (rand.nextFloat() > 0.5f) {
                            x = 0;
                        } else {
                            x = sizew - 1;
                        }
                        y = rand.nextInt(1, sizeh - 1);
                    } else {
                        if (rand.nextFloat() > 0.5f) {
                            y = 0;
                        } else {
                            y = sizeh - 1;
                        }
                        x = rand.nextInt(1, sizew - 1);
                    }
                    if (!indx.contains(new Pair<>(x, y))) {
                        vege = true;
                        door.setView(x * 40, y * 40);
                        System.out.println("width: " + x * 40 + " height: " + y * 40);
                    }
                }
            }

            for (Student st : r.getStudents()) {
                if (st.getView() != null) {
                    continue;
                }
                float w = abs(st.hashCode()) % (sizew - 2);
                float h = (abs(st.hashCode() * sizew)) % (sizeh - 2);
                st.setView((w + 1) * 40, (h + 1) * 40);
            }
            for (Teacher t : r.getTeacher()) {
                if (t.getView() != null) {
                    continue;
                }
                float w = abs(t.hashCode()) % (sizew - 2);
                float h = (abs(t.hashCode() * sizew)) % (sizeh - 2);
                t.setView((w + 1) * 40, (h + 1) * 40);
            }
            for (CleaningLady c : r.getCleaningLadies()) {
                if (c.getView() != null) {
                    continue;
                }
                float w = abs(c.hashCode()) % (sizew - 2);
                float h = (abs(c.hashCode() * sizew)) % (sizeh - 2);
                c.setView((w + 1) * 40, (h + 1) * 40);
            }
            r.setView(WIDTH, HEIGHT);
        }

        this.jelenlegiJatekos = s;
    }

    public void mergeRooms(Room regi1, Room regi2) {
        Room uj = new Room();
        regi1.merge(regi2, uj);
        uj.setView(regi1.getView().getX() + regi2.getView().getX(), regi1.getView().getY() + regi2.getView().getY());

        int sizew = (abs(uj.hashCode()) % 6) + 5;
        int sizeh = (abs(sizew * uj.hashCode()) % 6) + 5;
        for (Item i : uj.getItems()) {
            float w = abs(i.hashCode()) % (sizew - 2);
            float h = (abs(i.hashCode() * sizew)) % (sizeh - 2);
            i.setView((w + 1) * 40, (h + 1) * 40);
        }

        float nextXJobb = uj.getView().getTileWidth(), nextXBal = uj.getView().getTileWidth();
        float nextYAlul = uj.getView().getTileHeight(), nextYFelul = uj.getView().getTileHeight();

        // Eloszor csak oldalara rakjuk
        for (Door d : uj.getDoors()) {
            if (d.getView().getX() % (uj.getView().getX() - uj.getView().getTileWidth()) == 0) {
                if (d.getView().getX() == 0) {
                    d.getView().setPos(nextXBal, d.getView().getY());
                    nextXBal += uj.getView().getTileWidth();
                } else {
                    d.getView().setPos(nextXJobb, d.getView().getY());
                    nextXJobb += uj.getView().getTileWidth();
                }
            } else if (d.getView().getY() % (uj.getView().getY() - uj.getView().getTileHeight()) == 0) {
                if (d.getView().getY() == 0) {
                    d.getView().setPos(d.getView().getX(), nextYFelul);
                    nextYFelul += uj.getView().getTileHeight();
                } else {
                    d.getView().setPos(d.getView().getX(), nextYAlul);
                    nextYAlul += uj.getView().getTileHeight();
                }
            }
        }
        Random r = new Random();
        // Randomizaljuk az ajtok poziciojat
        for (Door d : uj.getDoors()) {
            if (d.getView().getX() % (uj.getView().getX() - uj.getView().getTileWidth()) == 0) {
                float randomy = d.getView().getY()
                        * r.nextInt((int) (uj.getView().getY() / uj.getView().getTileHeight()));
                d.getView().setPos(d.getView().getX(), randomy);
            } else if (d.getView().getY() % (uj.getView().getY() - uj.getView().getTileHeight()) == 0) {
                float randomx = d.getView().getX()
                        * r.nextInt((int) (uj.getView().getX() / uj.getView().getTileWidth()));
                d.getView().setPos(randomx, d.getView().getY());
            }
        }
    }

    /**
     * Visszaadja a megadott szám abszolút értékét.
     *
     * @param num A szám, amelynek az abszolút értékét ki akarjuk számolni.
     * @return A megadott szám abszolút értéke.
     */
    private int abs(int num) {
        return (num < 0) ? -num : num;
    }
}
