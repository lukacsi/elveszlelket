package skeleton.elveszlelket.gui;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class RoomView extends View {
	
	float egysegNegyzetWidth = 40;
	float egysegNegyzetHeight = 40;
	
	ArrayList<ImageView> textures;
	
	public RoomView(float x, float y) {
		super(x,y);
		if(x % egysegNegyzetWidth != 0) {
			System.out.println("Megadott szoba szelessege nem oszthatobe egysegnegyzetekre");
			System.out.println("Megadott x: " + x + " egyseg negyzet szelessege: " + this.egysegNegyzetWidth);
		} else if(y % egysegNegyzetHeight != 0) {
			System.out.println("Megadott szoba hosszusaga nem oszthatobe egysegnegyzetekre");
			System.out.println("Megadott y: " + y + " egyseg negyzet hosszusaga: " + this.egysegNegyzetHeight);
		}
		textures = new ArrayList<>();
	}
	
	public void loadTextures() {
		for(int sor = 0; sor < this.x; sor += this.egysegNegyzetWidth) {
			for(int oszlop = 0; oszlop < this.x; oszlop += this.egysegNegyzetHeight) {
				if(sor == 0 || oszlop == 0 || sor == (x-egysegNegyzetWidth) || oszlop == (y-egysegNegyzetHeight)) {
					ImageView uj = new ImageView(new Image("file:wall.png"));
					uj.setLayoutX(sor);
					uj.setLayoutY(oszlop);
					textures.add(uj);
				} else {
					ImageView uj = new ImageView(new Image("file:ground.png"));
					uj.setLayoutX(sor);
					uj.setLayoutY(oszlop);
					textures.add(uj);
				}
			}
		}
	}
	
	public void Draw(GameView jatekmegjelenito) {
		for(ImageView tex : textures) {
			jatekmegjelenito.getChildren().add(tex);
		}
	}
	
	
	/*
	 * return int
	 * Minden szoba egység területű, adott x (float) koordinátára kiszámolja,
	 * hogy hányadik szobán belüli egységnégyzetbe esik a koordináta bele.
	 * int cast mindig lefele kerekit.
	 */
	public float xToTileX(float x) {
		int normalized = (int) (x / this.egysegNegyzetWidth);
		return normalized * this.egysegNegyzetWidth;
	}
	
	public float yToTileY(float y) {
		int normalized = (int) (y / this.egysegNegyzetHeight);
		return normalized * this.egysegNegyzetHeight;
	}
	
	public float getTileWidth() {
		return this.egysegNegyzetWidth;
	}
	
	public float getTileHeight() {
		return this.egysegNegyzetHeight;
	}

	@Override
	public void normalizeTexture(float tileX, float tileY) {
		for(ImageView tex : textures) {
			tex.setFitWidth(tileX);
			tex.setFitHeight(tileY);
		}
	}
	
}
