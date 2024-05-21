package skeleton.elveszlelket.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.scene.Scene;
import javafx.stage.Stage;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.CleaningLady;
import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.Teacher;
import skeleton.elveszlelket.gui.Screen;

public class GameMan {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<CleaningLady> cleaners;
    private List<Room> map;
    private Queue<Student> sQueue;
    public int round;
    private Stage stage;

    public GameMan(int studentNum, int teacherNum, int cleanerNum, Stage stage) {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.cleaners = new ArrayList<>();
        this.sQueue = new LinkedList<>();
        this.stage = stage;
        round = 0;
        for (int i = 0; i < studentNum; i++) {
            students.add(new Student());
        }
        for (int i = 0; i < teacherNum; i++) {
            teachers.add(new Teacher());
        }
        for (int i = 0; i < cleanerNum; i++) {
            cleaners.add(new CleaningLady());
        }

        MapMan m = new MapMan(1, 0.1f, 0.1f, 0.1f, 0.5f, 0.1f, 0.1f, teachers, students, cleaners);
        map = m.init();

        round = 1;
        sQueue.addAll(students);
    }

    public void playRound() {
        while (!sQueue.isEmpty()) {
            Student s = sQueue.poll();
            DrawScene(s);
        }

        moveTeachers();
        moveCleaners();

        round++;
        sQueue.addAll(students);
    }

    private void moveTeachers() {

    }

    private void moveCleaners() {

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
        s.setView(40, 40);
        Screen screen = new Screen(WIDTH, HEIGHT);
        screen.Update(s);

        Scene fScene = new Scene(screen, 400.0f, 400.0f);
        stage = new Stage();
        stage.setScene(fScene);
        stage.show();
    }

    private int abs(int num) {
        return (num < 0) ? -num : num;
    }
}
