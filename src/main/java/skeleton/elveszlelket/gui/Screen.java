package skeleton.elveszlelket.gui;

import javafx.scene.layout.Pane;
import skeleton.elveszlelket.Student;

public class Screen extends Pane {
	private GameView palyamegjelenito;
	
	public Screen(float WIDTH, float HEIGHT) {
		palyamegjelenito = new GameView(WIDTH, HEIGHT);
		this.getChildren().add(palyamegjelenito);
	}
	
	public void Update(Student jelenlegiJatekos) {
		palyamegjelenito.Update(jelenlegiJatekos);
	}
	
	// Csak a tesztelesnel kell.
	public void updateBecauseItemsHaveBeenManuallyAddedToStudent() {
		palyamegjelenito.updateItemsPos();
	}
}
