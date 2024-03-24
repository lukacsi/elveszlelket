package skeleton.elveszlelket.tester.Commands;

import java.util.Random;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.tester.Tester;

public class SPLIT implements skeleton.elveszlelket.tester.Commands.Command {
    public void execute(String[] params, Tester t) {
        Room r1 = t.getRoom(params[1]);
        if(r1 != null) {
            boolean answer = t.askBoolean("Van oktato/hallgato " + params[1] + "-ben?");
            if(!answer) {
                String base = "SplitedRoom";
                String elsoNev, masodikNev;
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
                t.getCommand("MAKE").execute(makeParams, t);
                Room egyik = t.getRoom(roomNev);
                elsoNev = roomNev;
                i = 0;
                while(t.getRoom(roomNev) != null) {
                    i++;
                    roomNev = base.concat(String.valueOf(i));
                    if(i > 1000) {
                        roomNev = String.valueOf(i) + base;
                    }
                }
                makeParams[2] = roomNev;
                masodikNev = roomNev;
                t.getCommand("MAKE").execute(makeParams, t);
                Room masodik = t.getRoom(roomNev);
    
                if(r1.containsGas()) {
                    Random r = new Random();
                    float value = r.nextFloat();
                    if(value <= 0.5) {
                        egyik.setGas();
                    } else {
                        masodik.setGas();
                    }
                }
                if(r1.isCursed()) {
                    if(egyik.containsGas()) {
                        masodik.setCursed();
                    } else if(masodik.containsGas()) {
                        egyik.setCursed();
                    } else {
                        Random r = new Random();
                        float value = r.nextFloat();
                        if(value <= 0.5) {
                            egyik.setCursed();
                        } else {
                            masodik.setCursed();
                        }
                    }
                }
                for(i = 0; i < r1.getItems().size(); i++) {
                    Random r = new Random();
                    float value = r.nextFloat();
                    if(value <= 0.5) {
                        egyik.addItem(r1.getItems().get(i));
                    } else {
                        masodik.addItem(r1.getItems().get(i));
                    }
                }
                System.out.println(params[1] + " szetbomlott " + elsoNev + " es " + masodikNev + "-re");
            } else {
                System.out.println(params[1] + " nem bomlott szet, mert tartozkodik valaki benne.");
            }
        } else {
            System.out.println("Parameterkent megadott szoba meg nincs feljegyezve.");
        }
    }
}