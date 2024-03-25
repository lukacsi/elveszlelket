package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.IHaveTheRightStrategy;

public class TVSZ extends ProtectionItem{
    public TVSZ(){
        name = "TVSZ";
        uses = 3;
        strategy = new IHaveTheRightStrategy();
    }
    public void use(Student student) {
        
    }
}
