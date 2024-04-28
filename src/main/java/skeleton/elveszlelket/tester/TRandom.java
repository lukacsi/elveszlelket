package skeleton.elveszlelket.tester;

import java.util.Random;
import java.util.Scanner;


public class TRandom extends Random {
    public boolean random;
    private final Scanner sc;

    public TRandom() {
        super();
        sc = new Scanner(System.in);
        random = false;
    }

    public void toggleRandom() {
        random = !random;
    }

    @Override
    public float nextFloat() {
        if(random) {
            return super.nextFloat();
        } else {
            try {
                System.out.println("Please enter a float in [0-1] range: ");
                String in = sc.nextLine();
                return Float.parseFloat(in);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid float value entered. Defaulting to 0.");
            }
        }
        return 0;
    }
    @Override
    public int nextInt(int bound) {
        if(random) {
            return super.nextInt(bound);
        } else {
            try {
                System.out.println("Please enter a int in [0-" + bound + "] range: ");
                String in = sc.nextLine();
                return Integer.parseInt(in);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid float value entered. Defaulting to 0.");
            }
        }
        return 0;
    }
}
