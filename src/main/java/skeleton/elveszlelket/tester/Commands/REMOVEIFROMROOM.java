package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.tester.Tester;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.Room;

public class REMOVEIFROMROOM implements Command {
    @Override
    public void execute(String[] params, Tester t) {
        if (params.length == 3) {
            Item item = t.getItem(params[1]);
            Room room = t.getRoom(params[2]);

            if (item == null) {
                System.out.println("Parameterul kapott "+params[1]+" nincs feljegyezve.");
            }
            if (room == null) {
                System.out.println("Parameterul kapott "+params[2]+" nincs feljegyezve.");
            }
            boolean hasItem = false;
            for (Item it : room.getItems()) {
                if (it == item) {
                    hasItem = true;
                }
            }
            if (!hasItem) {
                System.out.println(params[1]+"nem lett eltavolitva "+params[2]+" targyai kozul. Nincs benne.");
            }
            if (item != null && room != null && hasItem) {
                room.removeItem(item);
                item.setRoom(null);
                System.out.println(params[1]+" eltavolitva "+params[2]+" targyai kozul.");
            }
        } else {
            System.out.println("A parameterek szama nem megfelelo.");
        }

    }
}
