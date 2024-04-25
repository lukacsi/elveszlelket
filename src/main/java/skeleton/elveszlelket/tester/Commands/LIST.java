package skeleton.elveszlelket.tester.Commands;

import java.util.List;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.tester.Tester;

public class LIST implements skeleton.elveszlelket.tester.Commands.Command {

    @Override
    public void execute(String[] params, Tester t) {
        if(params.length > 3) {
            System.out.println("Parameterk szama nem megfelelo.");
            return;
        }
        int type = 0;
        switch (params[1]) {
            case "STUDENTS":
                type = 1;
                break;
            case "TEACHERS":
                type = 2;
                break;
            case "CLEANINGLADYS":
                type = 3;
                break;
            case "ROOMS":
                type = 4;
                break;
            case "DOORS":
                type = 5;
                break;
            case "ITEMS":
                type = 6;
                break;
            case "HUMANS":
                type = 7;
                break;
            case "ALL":
                type = 0;
                break;
            default:
                System.out.println("Típus nem megfelelő");
                return;
        }

        boolean info = false;

        if(params.length == 3) {
            info = Boolean.parseBoolean(params[2]);
        }

        List<String> keys = t.getKeys(type);
        
        for (String string : keys) {
            System.out.println(string);
            if(info) {
                t.executeCommand(("INFO " + string).split(" "));;
            }
        }
    } 
}
