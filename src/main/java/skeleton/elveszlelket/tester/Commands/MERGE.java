package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.*;
import skeleton.elveszlelket.tester.Tester;

public class MERGE implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Room r1 = t.getRoom(params[1]);
        Room r2 = t.getRoom(params[2]);
        if(r1 != null && r2 != null) {
            boolean answer = t.askBoolean("Van-e hallgato/oktato "+ params[1] + " vagy " + params[2] + " szobaban?");
            if(!answer) {
                String base = "MergedRoom";
                String roomNev = base;
                int i = 0;
                while(t.getRoom(roomNev) != null) {
                    i++;
                    roomNev = base.concat(String.valueOf(i));
                    if(i > 1000) {
                        roomNev = String.valueOf(i) + base;
                    }
                }
                String[] makeParams = { "MAKE", "ROOM", roomNev };
                t.getCommand("MAKE").execute(params, t);
                Room uj = t.getRoom(roomNev);
                r1.merge(r2, uj);
                t.removeRoom(r2);
                t.removeRoom(r1);
                System.out.println("Szobak osszemergelodtek.");
            } else {
                System.out.println("Szobak nem mergelhetoek.");
            }
        } else {
            System.out.println("Parameterkent megadott szoba valamelyike meg nincs feljegyezve.");
        }
    }
}