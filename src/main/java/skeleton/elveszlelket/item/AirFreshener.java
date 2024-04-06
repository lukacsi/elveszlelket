package skeleton.elveszlelket.item;

import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.strategy.NeutralizeGasStrategy;

public class AirFreshener extends Item {
    
    /**
     * Az AirFreshener konstruktora.
     * Beállítja a nevét "Air Freshener"-re,
     * és a strategy-t NeutralizeGasStrategy-re.
     */
    public AirFreshener() {
        name = "Air Freshener";
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
}
