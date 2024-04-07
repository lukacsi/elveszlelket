package skeleton.elveszlelket.tester.Tests;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import skeleton.elveszlelket.Room;
import skeleton.elveszlelket.tester.Tester;
import skeleton.elveszlelket.tester.Commands.Command;

public class SETUP implements Command {

    /*
     * @param String. Keresett tipus szovegesen.
     * Ilyen tipus lehet peldaul:
     * STUDENT, TEACHER, ROOM...
     */
    private boolean isTypeCorrect(String type) {
        if (type.equals("STUDENT") || type.equals("TEACHER") || type.equals("ROOM") || type.equals("ONEWAYDOOR")
                || type.equals("TWOWAYDOOR") || type.equals("CAMEMBERT") || type.equals("FFP2MASK")
                || type.equals("BEER")
                || type.equals("RAG") || type.equals("TRANSISTOR") || type.equals("TVSZ") || type.equals("LOGAR")) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String[] params, Tester t) {
        List<String[]> makeparams = new ArrayList<>();
        List<String> eddigfelvettNevek = new ArrayList<>();
        for (int i = 1; i < params.length; i++) {
            String szetvalasztott[] = params[i].split(":");
            if (isTypeCorrect(szetvalasztott[0])) {
                for (int u = 0; u < Integer.valueOf(szetvalasztott[1]); u++) {
                    String objektumNeve = t.getAvailableName(eddigfelvettNevek, szetvalasztott[0]);
                    eddigfelvettNevek.add(objektumNeve);
                    String paramForMake[] = { "MAKE", szetvalasztott[0], objektumNeve };
                    makeparams.add(paramForMake);
                }
            } else {
                System.out.println((i + 1) + ". parameterul kapott tipus '" + params[i] + "' rossz formatumu.");
                return;
            }
        }

        for (int i = 0; i < makeparams.size(); i++) {
            t.getCommand("MAKE").execute(makeparams.get(i), t);
        }
    }
}