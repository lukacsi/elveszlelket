package skeleton.elveszlelket.gui;

import javafx.scene.layout.Pane;
import skeleton.elveszlelket.Student;
import skeleton.elveszlelket.item.Item;

public class GameView extends Pane {
	public float WIDTH;
	public float HEIGHT;
	
	public GameView(float w, float h) {
		WIDTH = w;
		HEIGHT = h;
		
		this.setMinSize(w, h);
		this.setPrefSize(w, h);
	}
	
	public void Update(Student jelenlegiJatekos) {
		
		// Letorlunk mindent korabbi texturat.
		this.getChildren().clear();
		
		// Szoba rajz
		RoomView rv = jelenlegiJatekos.getRoom().getView();
		rv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
		rv.Draw(this);
		
		for(Item i : jelenlegiJatekos.getRoom().getItems()) {
			ItemView iv = i.getView();
			iv.setPos(rv.xToTileX(iv.getX()), rv.yToTileY(iv.getY()));
			iv.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
			iv.Draw(this);
		}
		
		// Student Rajz
		View v = jelenlegiJatekos.getView();
		v.setPos(rv.xToTileX(v.getX()), rv.yToTileY(v.getY()));
		v.normalizeTexture(rv.getTileWidth(), rv.getTileHeight());
		v.Draw(this);
	}
	
	public void normalize() {
		
	}
	
}
