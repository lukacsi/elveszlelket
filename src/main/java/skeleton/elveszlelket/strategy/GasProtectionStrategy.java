package skeleton.elveszlelket.strategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class GasProtectionStrategy implements ItemUseStrategy {
    public void execute(Student student, Item item) {
        System.out.println("Mask has uses? y/n");
        boolean result = askQuestion();
        if (result) {
            student.setGasProtection(30);
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
