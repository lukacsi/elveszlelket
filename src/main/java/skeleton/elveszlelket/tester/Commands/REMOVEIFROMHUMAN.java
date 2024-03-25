package skeleton.elveszlelket.tester.Commands;

import skeleton.elveszlelket.Human;
import skeleton.elveszlelket.item.Item;
import skeleton.elveszlelket.tester.Tester;

public class REMOVEIFROMHUMAN implements Command{
    @Override
    public void execute(String[] params, Tester t){
        if(params.length == 3){
            Item item = t.getItem(params[1]);
            Human human = t.getStudent(params[2]);
            if(human == null){
                human = t.getTeacher(params[2]);
            }
            if(item == null){
                System.out.println("Nincs ilyen targy.");
            }
            if(human == null){
                System.out.println("Nincs ilyen nevu ember.");
            }

            if(item != null && human != null){
                human.dropItem(item);
            }
        }else{
            System.out.println("A parameterek szama nem megfelelo.");
        }
        
    }
}
