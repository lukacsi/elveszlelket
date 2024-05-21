package skeleton.elveszlelket.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javafx.scene.Scene;
import javafx.stage.Stage;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.CleaningLady;
import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.gui.Screen;

public class GameMan {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<CleaningLady> cleaners;
    private List<Room> map;
    private Queue<Student> sQueue;
    public int round;
    private Stage stage;
    private Random r;

    public GameMan(Settings settings, Stage stage) {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.cleaners = new ArrayList<>();
        this.sQueue = new LinkedList<>();
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

    public void playRound() {
        if (sQueue.isEmpty()) {
            moveTeachers();
            moveCleaners();
            round++;
            sQueue.addAll(students);
        }
        Student s = sQueue.poll();
        DrawScene(s);
    }

    private void moveTeachers() {
        for (Teacher gajdos : teachers) {
            Door door = gajdos.getRoom().getDoorAt(r.nextInt(0, 20));
            if (door != null) {
                door.accept(gajdos);
                door.putMeThrough(gajdos);
            }
        }
    }

    private void moveCleaners() {
        for (CleaningLady gizi : cleaners) {
            Door door = gizi.getRoom().getDoorAt(r.nextInt(0, 20));
            if (door != null) {
                door.accept(gizi);
                door.putMeThrough(gizi);
            }
        }
    }

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
        r.setView(WIDTH, HEIGHT);
        for (Item i : r.getItems()) {
            // VALMIT CSINÁLNI KELL HA SIZEW == SIZEH
            float w = abs(i.hashCode()) % (sizew - 2);
            float h = (abs(i.hashCode() * sizew)) % (sizeh - 2);
            i.setView((w + 1) * 40, (h + 1) * 40);
            System.out.println(i.getClass().toString() + " " + (w + 1) * 40 + " " + (h + 1) * 40);
        }

        for (Student st : r.getStudents()) {
            if (st == s) {
                continue;
            }
            float w = abs(st.hashCode()) % (sizew - 2);
            float h = (abs(st.hashCode() * sizew)) % (sizeh - 2);
            st.setView((w + 1) * 40, (h + 1) * 40);
        }
        for (Teacher t : r.getTeacher()) {
            float w = abs(t.hashCode()) % (sizew - 2);
            float h = (abs(t.hashCode() * sizew)) % (sizeh - 2);
            t.setView((w + 1) * 40, (h + 1) * 40);
        }
        for (CleaningLady c : r.getCleaningLadies()) {
            float w = abs(c.hashCode()) % (sizew - 2);
            float h = (abs(c.hashCode() * sizew)) % (sizeh - 2);
            c.setView((w + 1) * 40, (h + 1) * 40);
        }

        s.setView(40, 40);
        Screen screen = new Screen(WIDTH, HEIGHT);
        screen.Update(s);

        Scene fScene = new Scene(screen, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(fScene);
        stage.show();
    }

    private int abs(int num) {
        return (num < 0) ? -num : num;
    }
}
