package skeleton.elveszlelket;
import skeleton.elveszlelket.gui.GameView;
import skeleton.elveszlelket.gui.Screen;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Main extends Application
{
	float WIDTH = 400;
	float HEIGHT = 400;
	
	HashMap<String, String> paths;
	
	
    public static Tester t;

    /**
     * Az alkalmazás belépési pontja.
     * Létrehoz egy Tester példányt és elindítja a parancsok feldolgozását.
     *
     * @param args
     */
    public static void main( String[] args )
    {
        //t = new Tester();
    	launch(args);
        System.out.println("Lefutott a program!");
        //t.listen();
    }

	@Override
	public void start(Stage arg0) throws Exception {
		paths = new HashMap<>();
		paths.put("Sor", "file:beer.png");
		paths.put("Hallgato", "file:student.png");
		paths.put("Transistor", "file:transistor.png");
		paths.put("Camembert", "file:camembert.png");
		paths.put("Tvsz", "file:tvsz.png");
		paths.put("CleaningLady", "file:cleaninglady.png");
		paths.put("Mask", "file:mask.png");
		paths.put("FFP2Mask", "file:mask.png");
		//t = new Tester();
		
		Room r = new Room();
		r.setView(WIDTH, HEIGHT);
		Student s = new Student();
		s.setCurrentRoom(r);
		s.setView(40, 40, paths.get("Hallgato"));
		
		
		Beer b = new Beer();
		b.setView(160, 40, paths.get("Sor"));
		TVSZ t = new TVSZ();
		t.setView(240, 40, paths.get("Tvsz"));
		Camember c1 = new Camember();
		c1.setView(240, 40, paths.get("Camembert"));
		
		Camember c = new Camember();
		c.setView(240, 120, paths.get("Camembert"));
		FFP2Mask f = new FFP2Mask();
		f.setView(320, 200, paths.get("Mask"));
		Transistor t1 = new Transistor();
		t1.setView(320, 80, paths.get("Transistor"));
		
		r.addItem(b);
		r.addItem(c1);
		s.pickupItem(b);
		r.addItem(t);
		r.addItem(c);
		//s.pickupItem(c);
		r.addItem(f);
		r.addItem(t1);
		
		Screen screen = new Screen(WIDTH, HEIGHT);
		screen.Update(s);
		screen.updateBecauseItemsHaveBeenManuallyAddedToStudent();
		
		Scene foscene = new Scene(screen, WIDTH, HEIGHT);
		foscene.setOnKeyPressed(e -> {
			screen.keyPressed(e);
		});
		arg0 = new Stage();
		arg0.setScene(foscene);
		arg0.show();
	}
}
