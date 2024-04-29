package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Tester;

public class REMOVEIFROMHUMAN implements Command {
    @Override
    public void execute(String[] params, Tester t) {
        if (params.length == 3) {
            Item item = t.getItem(params[1]);
            Human human = t.getStudent(params[2]);
            if (human == null) {
                human = t.getTeacher(params[2]);
            }
            if (item == null) {
                System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
            }
            if (human == null) {
                System.out.println("Parameterul kapott "+params[2]+" nincs feljegyezve.");
            }

            if (item != null && human != null) {
                if(human.dropItem(item)){
                    item.setHuman(null);
                    System.out.println(params[1]+" eltavolitva "+params[2]+" targyai kozul.");
                }else {
                    System.out.println(params[1]+" nem lett eltavolitva "+params[2]+" targyai kozul. Nincs nala.");
                    return;
                }
            }
        } else {
            System.out.println("A parameterek szama nem megfelelo.");
        }

    }
}
