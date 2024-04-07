package skeleton.elveszlelket.tester.Commands;

import java.util.Iterator;

import skeleton.elveszlelket.*;
import skeleton.elveszlelket.door.Door;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

public class INFO implements Command {

    public void hallgatoInfo(String neve, Student s, Tester t) {
        if (s != null) {
            System.out.println("\nHallgato feljegyzett neve: " + neve);
            System.out.println("Eletben van-e?: " + String.valueOf(!s.isDead()));
            System.out.println("Megszerezte-e a logart?: " + String.valueOf(s.isLogarObtained()));

            System.out.print("Kabult-e?: ");
            if (s.getStunDuration() > 0) {
                System.out.println("true - Ennyi idore: " + String.valueOf(s.getStunDuration()));
            } else {
                System.out.println("false");
            }

            System.out.print("Immunis-e oktatok ellen?: ");
            if (s.getImmunityDuration() > 0) {
                System.out.println("true - Ennyi idore: " + String.valueOf(s.getImmunityDuration()));
            } else {
                System.out.println("false");
            }

            System.out.print("Immunis-e gaz ellen?: ");
            if (s.getGasProtectionDuration() > 0) {
                System.out.println("true - Ennyi idore: " + String.valueOf(s.getGasProtectionDuration()));
            } else {
                System.out.println("false");
            }

            System.out.println("Jelenlegi szobaja: " + t.getRoomName(s.getRoom()));
            System.out.print("Blokkolt-e utolso ajtaja?: ");
            if (s.isLastDoorBlocked()) {
                System.out.println("true - A blokkolt ajto neve: " + t.getDoorName(s.getLastDoor()));
            } else {
                System.out.println("false");
            }

            System.out.print(String.valueOf(s.getItems().size()) + " db jelenleg birtokolt targya van");
            if (s.getItems().size() > 0) {
                System.out.println(", ezek sorra:");
                for (int i = 0; i < s.getItems().size(); i++) {
                    System.out.println((i + 1) + ". Nev: " + t.getItemName(s.getItems().get(i)) + " - Targy tipusa: "
                            + s.getItems().get(i).getName());
                }
            }
        }
    }

    public void oktatoInfo(String neve, Teacher o, Tester t) {
        if (o != null) {
            System.out.println("\nOktato feljegyzett neve: " + neve);
            System.out.print("Kabult-e?: ");
            if (o.getStunDuration() > 0) {
                System.out.println("true - Ennyi idore: " + String.valueOf(o.getStunDuration()));
            } else {
                System.out.println("false");
            }

            System.out.println("Jelenlegi szobaja: " + t.getRoomName(o.getRoom()));

            System.out.print(String.valueOf(o.getItems().size()) + " db jelenleg birtokolt targya van");
            if (o.getItems().size() > 0) {
                System.out.println(", ezek sorra:");
                for (int i = 0; i < o.getItems().size(); i++) {
                    System.out.println((i + 1) + ". Nev: " + t.getItemName(o.getItems().get(i)) + " - Targy tipusa: "
                            + o.getItems().get(i).getName());
                }
            }
        }

    }

    public void szobaInfo(String neve, Room r, Tester t) {
        if (r != null) {
            System.out.println("\nSzoba feljegyzett neve: " + neve);
            System.out.println("Elatkozott-e a szoba?: " + String.valueOf(r.isCursed()));
            System.out.println("Gazos-e a szoba?: " + String.valueOf(r.containsGas()));

            int targyakszama = r.getItems().size();
            System.out.println("Szobaban talalhato targyak szama:" + String.valueOf(targyakszama));

            if (targyakszama > 0) {
                System.out.println("Targyak listaja: ");
                for (int i = 0; i < targyakszama; i++) {
                    System.out.println((i + 1) + ". neve: " + t.getItemName(r.getItems().get(i)));
                }
            }

            int ajtokszama = r.getDoors().size();
            System.out.println("Szobaban talalhato ajtok szama:" + String.valueOf(ajtokszama));

            if (ajtokszama > 0) {
                System.out.println("Ajtok listaja: ");
                for (int i = 0; i < ajtokszama; i++) {
                    System.out.println((i + 1) + ". neve: " + t.getDoorName(r.getDoorAt(i)));
                }
            }

            int tartozkodokszama = r.getStudents().size() + r.getTeacher().size();
            System.out.println("Szobaban tartozkodo emberek szama:" + String.valueOf(tartozkodokszama));

            if (tartozkodokszama > 0) {
                System.out.println("Emberek listaja: ");
                for (int i = 0; i < r.getStudents().size(); i++) {
                    System.out.println((i + 1) + ". neve: " + t.getStudentName(r.getStudents().get(i)));
                }
                for (int i = 0; i < r.getTeacher().size(); i++) {
                    System.out.println((i + 1) + ". neve: " + t.getTeacherName(r.getTeacher().get(i)));
                }
            }
        }
    }

    public void ajtoInfo(String neve, Door d, Tester t) {
        if (d != null) {
            System.out.println("\nAjto feljegyzett neve: " + neve);
            System.out.println("Lathato?: " + String.valueOf(d.isHidden()));
            if (d.getOwnerRoom() != null) {
                System.out
                        .println("Elso feljegyzett szoba(Ide vissza csak a ketiranyu ajton keresztul tudunk eljutni): "
                                + t.getRoomName(d.getOwnerRoom()));
                System.out.println("Masodik feljegyzett szoba(ide mindket tipusu ajton keresztul eltudunk jutni): "
                        + t.getRoomName(d.getSecondRoom()));
            } else {
                System.out.println("Nincs meg szoba rendelve hozza.");
            }
        }
    }

    public void targyInfo(String neve, Item i, Tester t) {
        if (i != null) {
            System.out.println("\nTargy feljegyzett neve: " + neve);
            System.out.println("Targy tipusa: " + i.getName());
            if (i.getName().equalsIgnoreCase("Rag")) {
                Rag temp = (Rag) i;
                System.out.println("Hanyszor hasznalhato meg: " + temp.getUses());
            } else if (i.getName().equalsIgnoreCase("TVSZ")) {
                TVSZ temp = (TVSZ) i;
                System.out.println("Hanyszor hasznalhato meg: " + temp.getUses());
            } else if (i.getName().equalsIgnoreCase("FFP2MASK")) {
                FFP2Mask temp = (FFP2Mask) i;
                System.out.println("Hanyszor hasznalhato meg: " + temp.getUses());
            } else if (i.getName().equalsIgnoreCase("BEER")) {
                Beer temp = (Beer) i;
                System.out.println("Hanyszor hasznalhato meg: " + temp.getUses());
            } else if (i.getName().equalsIgnoreCase("Camembert")) {
                Camember c = (Camember) i;
                System.out.println("Kivan nyitva?: " + String.valueOf(c.isOpened()));
            } else if (i.getName().equalsIgnoreCase("Transistor")) {
                Transistor c = (Transistor) i;
                System.out.println("Parositva van?: " + String.valueOf(c.hasPair()));
                if (c.hasPair()) {
                    System.out.println("Parjanak neve: " + t.getItemName(c.getPair()));
                }
            }

            if (i.getHuman() != null) {
                System.out.println("Kinel van?: " + t.getHumanName(i.getHuman()));
            } else if (i.getRoom() != null) {
                System.out.println("Melyik szobaban van?: " + t.getRoomName(i.getRoom()));
            } else {
                System.out.println("Meg nincs se embernel, se szobaban.");
            }
        }
    }

    public void commandInfo(String command) {
        if (command.equalsIgnoreCase("MAKE")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [tipus] [nev]");
        } else if (command.equalsIgnoreCase("ADDHTOROOM")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [ember_neve] [szoba_neve]");
        } else if (command.equalsIgnoreCase("ADDITOROOM")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [targy_neve] [szoba_neve]");
        } else if (command.equalsIgnoreCase("ADDTOHUMAN")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [targy_neve] [ember_neve]");
        } else if (command.equalsIgnoreCase("CHECKLOSE")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase());
        } else if (command.equalsIgnoreCase("CHECKWIN")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase());
        } else if (command.equalsIgnoreCase("CLEAR")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase());
        } else if (command.equalsIgnoreCase("CONNECTROOM")) {
            System.out.println(
                    command + " hasznalata: " + command.toUpperCase() + " [ajto_neve] [szoba_neve] [szoba_neve]");
        } else if (command.equalsIgnoreCase("CONNECTTRANS")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase()
                    + " [hallgato_neve] [tranzisztor_neve] [tranzisztor_neve]");
        } else if (command.equalsIgnoreCase("DROP")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [targy_neve] [ember_neve]");
        } else if (command.equalsIgnoreCase("INFO")) {
            System.out.println(command + " hasznalata:\n\t- " + command.toUpperCase()
                    + " - Feljegyzett objektumok listaja.\n\t- " + command.toUpperCase()
                    + " [parancs_neve/objektum_neve] - Leirast ad egy objektumrol/parancsrol.");
        } else if (command.equalsIgnoreCase("MERGE")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [szoba_neve] [szoba_neve]");
        } else if (command.equalsIgnoreCase("MOVE")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [ajto_neve] [ember_neve]");
        } else if (command.equalsIgnoreCase("PICKUP")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [targy_neve] [ember_neve]");
        } else if (command.equalsIgnoreCase("REMOVE")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase()
                    + " [objektum_neve] ([objektum_neve] lehet ROOM/DOOR/STUDENT/TEACHER)");
        } else if (command.equalsIgnoreCase("REMOVEIFROMHUMAN")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [targy_neve] [ember_neve]");
        } else if (command.equalsIgnoreCase("REMOVEIFROMROOM")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [targy_neve] [szoba_neve]");
        } else if (command.equalsIgnoreCase("SPLIT")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [szoba_neve]");
        } else if (command.equalsIgnoreCase("TOGGLECURSE")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [szoba_neve] [ertek]");
        } else if (command.equalsIgnoreCase("TOGGLEGAS")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [szoba_neve] [ertek]");
        } else if (command.equalsIgnoreCase("USEITEM")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase() + " [ember_neve] [targy_neve]");
        } else if (command.equalsIgnoreCase("SETUP")) {
            System.out.println(command + " hasznalata: " + command.toUpperCase()
                    + " [objektum_tipus]:[objektumok_szama] [objektum_tipus]:[objektumok_szama] ....");
            System.out.println(
                    "Pelda: SETUP ONEWAYDOOR:1 ROOM:2 STUDENT:1 - letrehoz 1db egyiranyu ajtot, 2db szobat, 1 db hallgatot");
        }
    }

    @Override
    public void execute(String[] params, Tester t) {

        if (params.length == 1) {
            System.out.println("Jatekban jelenleg levo objektumok:");
            for (Student s : t.getStudents()) {
                System.out.println("Hallgato: " + t.getStudentName(s));
            }

            for (Teacher s : t.getTeachers()) {
                System.out.println("Oktato: " + t.getTeacherName(s));
            }

            for (Room s : t.getRooms()) {
                System.out.println("Szoba: " + t.getRoomName(s));
            }

            for (Door s : t.getDoors()) {
                System.out.println("Ajtó: " + t.getDoorName(s));
            }

            for (Item s : t.getItems()) {
                System.out.println("Targy " + s.getName() + ": " + t.getItemName(s));
            }

            return;
        }

        if (params.length != 2) {
            System.out.println("Parameterek szama nem megfelelo.");
            return;
        }

        commandInfo(params[1]);

        Student s = t.getStudent(params[1]);
        if (s != null) {
            hallgatoInfo(params[1], s, t);
        }

        Teacher o = t.getTeacher(params[1]);
        if (o != null) {
            oktatoInfo(params[1], o, t);
        }

        Room r = t.getRoom(params[1]);
        if (r != null) {
            szobaInfo(params[1], r, t);
        }

        Door d = t.getDoor(params[1]);
        if (d != null) {
            ajtoInfo(params[1], d, t);
        }

        Item i = t.getItem(params[1]);
        if (i != null) {
            targyInfo(params[1], i, t);
        }
    }
}
