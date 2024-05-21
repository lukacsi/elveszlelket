package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.gui.ItemView;
import skeleton.elveszlelket.strategy.NeutralizeGasStrategy;

public class AirFreshener extends ProtectionItem {
    
    /**
     * Az AirFreshener konstruktora.
     * Beállítja a nevét "Air Freshener"-re,
     * és a strategy-t NeutralizeGasStrategy-re.
     */
    public AirFreshener() {
        name = "Air Freshener";
        uses = 1;
        strategy = new NeutralizeGasStrategy();
    }

    /**
     * Az AirFreshener használatát implementáló függvény.
     * Meghívja a saját strategy-jét.
     * 
     * @param student A tárgyat használó hallgató.
     */
    @Override
    public void use(Student student) {
        strategy.execute(student, this);
    }

    @Override
    public void setFalse(boolean fals) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFalse'");
    }

    @Override
    public void setView(float x, float y) {
    	view = new ItemView(x, y, "textures/airfreshener.png");
    }
}
