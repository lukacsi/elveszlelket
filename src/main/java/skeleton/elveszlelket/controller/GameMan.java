package skeleton.elveszlelket.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

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
import skeleton.elveszlelket.gui.Screen;

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
            Door door = gajdos.getRoom().getDoorAt(rand.nextInt(0, 20));
            if (door != null) {
                door.accept(gajdos);
                door.putMeThrough(gajdos);
            }
        }
    }

    private void moveCleaners() {
        for (CleaningLady gizi : cleaners) {
            Door door = gizi.getRoom().getDoorAt(rand.nextInt(0, 20));
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
            if (i.getView() != null) {
                continue;
            }
            float w = abs(i.hashCode()) % (sizew - 2);
            float h = (abs(i.hashCode() * sizew)) % (sizeh - 2);
            i.setView((w + 1) * 40, (h + 1) * 40);
            System.out.println(i.getClass().toString() + " " + (w + 1) * 40 + " " + (h + 1) * 40);
        }

        for (Door door : r.getDoors()) {
            if(door.getView() != null) {
                continue;
            }
            int hash = Math.abs(door.hashCode());
            // Ensure x and y are within bounds and not on the border
            int x = (hash % (sizew - 2)) + 1; // 1 <= x < h-1
            int y = (hash % (sizeh - 2)) + 1; // 1 <= y < w-1
            door.setView(x * 40, y * 40); // Assuming a scale factor of 40
        }

        for (Student st : r.getStudents()) {
            if(st.getView() != null) {
                continue;
            }
            if (st == s) {
                continue;
            }
            float w = abs(st.hashCode()) % (sizew - 2);
            float h = (abs(st.hashCode() * sizew)) % (sizeh - 2);
            st.setView((w + 1) * 40, (h + 1) * 40);
        }
        for (Teacher t : r.getTeacher()) {
            if(t.getView() != null) {
                continue;
            }
            float w = abs(t.hashCode()) % (sizew - 2);
            float h = (abs(t.hashCode() * sizew)) % (sizeh - 2);
            t.setView((w + 1) * 40, (h + 1) * 40);
        }
        for (CleaningLady c : r.getCleaningLadies()) {
            if(c.getView() != null) {
                continue;
            }
            float w = abs(c.hashCode()) % (sizew - 2);
            float h = (abs(c.hashCode() * sizew)) % (sizeh - 2);
            c.setView((w + 1) * 40, (h + 1) * 40);
        }

        s.setView(40, 40);
        this.jelenlegiJatekos = s;
    }

    private int abs(int num) {
        return (num < 0) ? -num : num;
    }

    private void placeDoors(int sizew, int sizeh, Room r) {
        List<Door> d = r.getDoors();

        List<List<Door>> walls = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            walls.add(new ArrayList<>());
        }
    }
}
