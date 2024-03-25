package skeleton.elveszlelket.strategy;

import skeleton.elveszlelket.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.item.Item;

public class GasStrategy implements ItemUseStrategy{
    public void execute(Student student, Item item) {
        Room r = student.getRoom();
        System.out.println("Is camembert unopened? y/n");
        boolean result = askQuestion();
        
        if (result) {
            student.dropItem(item);
            r.setGas();
        }
    }

    private boolean askQuestion() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean result;
        
        while (true) {
            try {
                char c = (char) reader.read();
                
                if(c == 'y' || c == 'n') {
                    result = (c == 'y') ? true : false;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
