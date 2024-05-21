package skeleton.elveszlelket;
import skeleton.elveszlelket.controller.GameMan;
import skeleton.elveszlelket.controller.MapMan;
import skeleton.elveszlelket.gui.GameView;
import skeleton.elveszlelket.gui.Screen;
import skeleton.elveszlelket.item.*;
import skeleton.elveszlelket.tester.Tester;

import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	float WIDTH = 400;
	float HEIGHT = 400;
	
	// HashMap<String, String> paths;
	
	
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
        System.out.println("Fut a program!");
        //t.listen();
    }

	@Override
	public void start(Stage arg0) throws Exception {

		/// ÁTRAKTAM ŐSOSZTÁLYBA ///
		// paths = new HashMap<>();
		// paths.put("Sor", "file:textures/beer.png");
		// paths.put("Hallgato", "file:textures/student.png");
		// paths.put("Transistor", "file:textures/transistor.png");
		// paths.put("Camembert", "file:textures/camembert.png");
		// paths.put("Tvsz", "file:textures/tvsz.png");
		// paths.put("CleaningLady", "file:textures/cleaninglady.png");
		// paths.put("Mask", "file:textures/mask.png");
		// paths.put("FFP2Mask", "file:textures/mask.png");
		//t = new Tester();
		
		// Room r = new Room();
		// r.setView(WIDTH, HEIGHT);
		// Student s = new Student();
		// s.setCurrentRoom(r);
		// s.setView(40, 40);
		
		
		// Beer b = new Beer();
		// b.setView(160, 40);
		// TVSZ t = new TVSZ();
		// t.setView(240, 40);
		// Camember c = new Camember();
		// c.setView(240, 120);
		// FFP2Mask f = new FFP2Mask();
		// f.setView(320, 200);
		// Transistor t1 = new Transistor();
		// t1.setView(400, 80);
		
		// r.addItem(b);
		// s.pickupItem(b);
		// r.addItem(t);
		// r.addItem(c);
		// s.pickupItem(c);
		// r.addItem(f);
		// r.addItem(t1);
		
		// Screen screen = new Screen(WIDTH, HEIGHT);
		// screen.Update(s);
		// screen.updateBecauseItemsHaveBeenManuallyAddedToStudent();
		
		// Scene foscene = new Scene(screen, WIDTH, HEIGHT);
		// arg0 = new Stage();
		// arg0.setScene(foscene);
		// arg0.show();
		GameMan gm = new GameMan(1,0,0,arg0);
		gm.playRound();
	}
	
}
