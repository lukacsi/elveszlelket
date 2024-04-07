package skeleton.elveszlelket.tester.Tests;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.tester.Tester;
import skeleton.elveszlelket.tester.Commands.Command;

public class T_CONNECTION implements Command {
    @Override
    public void execute(String[] params, Tester t) {
        String param[] = { "SETUP", "ONEWAYDOOR:1", "ROOM:2", "STUDENT:1" };
        t.getCommand("SETUP").execute(param, t);
        String param1[] = { "ADDHTOROOM", "STUDENT1", "ROOM1" };
        t.getCommand("ADDHTOROOM").execute(param1, t);
        String param2[] = { "CONNECTROOM", "ONEWAYDOOR1", "ROOM1", "ROOM2" };
        t.getCommand("CONNECTROOM").execute(param2, t);
        String param3[] = { "MOVE", "ONEWAYDOOR1", "STUDENT1" };
        t.getCommand("MOVE").execute(param3, t);
    }
}