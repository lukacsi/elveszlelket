package skeleton.elveszlelket.tester.Tests;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import skeleton.elveszlelket.tester.Tester;
import skeleton.elveszlelket.tester.Commands.Command;
public class TEST implements  Command {
    ArrayList<String[]> commandList;
    ArrayList<String> asrt;


    @Override
    public void execute(String[] params, Tester t) {
        if(params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }
        commandList = new ArrayList<>();
        asrt = new ArrayList<>();

        readFileContent(params[1], 0);
        readFileContent(params[2], 1);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        for (String[] cPrarams : commandList) {
            t.getCommand(cPrarams[0]).execute(cPrarams, t);
        }
        System.out.flush();
        System.setOut(old);

        String[] outputLines = baos.toString().split("\\R"); // "\\R" matches any line separator
        ArrayList<String> outputList = new ArrayList<>(Arrays.asList(outputLines));
        
        if (compareOutput(asrt, outputList)) {
            System.out.println("A teszt sikeres!");
        } else {
            System.out.println("A teszt sikertelen!");
        }
    }
    private void readFileContent(String filePath, int type) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(type == 0) {
                    String[] cParams = line.split(" ");
                    commandList.add(cParams);
                }
                else {
                    asrt.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
    }

    private boolean compareOutput(ArrayList<String> expected, ArrayList<String> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }
    
}
