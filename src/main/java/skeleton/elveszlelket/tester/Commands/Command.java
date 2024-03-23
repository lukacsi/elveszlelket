package skeleton.elveszlelket.tester.Commands;
import skeleton.elveszlelket.tester.Tester;

public interface Command {
    public void execute(String[] params, Tester t);
}