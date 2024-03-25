package skeleton.elveszlelket;
import skeleton.elveszlelket.tester.Tester;

public class App 
{
    public static Tester t;

    /**
     * Az alkalmazás belépési pontja.
     * Létrehoz egy Tester példányt és elindítja a parancsok feldolgozását.
     *
     * @param args
     */
    public static void main( String[] args )
    {
        t = new Tester();
        t.listen();
    }
}
