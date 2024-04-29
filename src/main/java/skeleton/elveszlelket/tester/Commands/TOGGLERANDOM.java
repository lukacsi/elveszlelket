package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.tester.Tester;

public class TOGGLERANDOM implements Command{
    
    @Override
    public void execute(String[] params, Tester t) {
        t.r.toggleRandom();
        System.out.println("Randomness " + ((t.r.random)?"enabled.":"disabled."));
    }
}