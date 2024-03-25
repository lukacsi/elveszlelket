package skeleton.elveszlelket;
import skeleton.elveszlelket.tester.Tester;
/**
 * Hello world!
 *
 */
public class App 
{
    static Tester t;

    public static void main( String[] args )
    {
        t = new Tester();
        t.listen();
    }
}
