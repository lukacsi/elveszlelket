package skeleton.elveszlelket.controller;

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
        this.stage = stage;
        round = 0;
        for(int i = 0; i < studentNum; i++) {
            students.add(new Student());
        }
        for(int i = 0; i < teacherNum; i++) {
            teachers.add(new Teacher());
        }
        for(int i = 0; i < cleanerNum; i++) {
            cleaners.add(new CleaningLady());
        }
        
        MapMan m = new MapMan(1, 1, 1, 1, 1, 1, 1, teachers, students, cleaners);
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

    private void DrawScene(Student s) {
        float WIDTH = 400;
        float HEIGHT = 400;
		Room r = s.getRoom();
		r.setView(WIDTH, HEIGHT);
        for (Item i : r.getItems()) {
            float w = (i.hashCode()) % 8;
            float h = (i.hashCode()/2) % 8 ;
            i.setView(w, h);
        }
        s.setView(40, 40);
        Screen screen = new Screen(WIDTH, HEIGHT);
        screen.Update(s);

        Scene fScene = new Scene(screen, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(fScene);
        stage.show();
	}
}
