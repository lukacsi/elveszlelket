package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Tester;

public class TOGGLEFAKE implements Command {

    @Override
    public void execute(String[] params, Tester t) {
        Item i;
        if(params.length != 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }
        else if(t.getItem(params[1]) != null) {
            i = t.getItem(params[1]);
            boolean fals;
            if(params[2].equals("TRUE"))
                fals = true;
            else if(params[2].equals("FALSE"))
                fals = false;
            else {
                System.out.println("Nem igaz hamis érték");
                return;
            }
            i.setFalse(fals);
        }
        else {
            System.out.println("Tárgy nem létezik!");
        }
    }
    
}