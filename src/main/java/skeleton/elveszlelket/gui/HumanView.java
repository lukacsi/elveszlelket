package skeleton.elveszlelket.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HumanView extends View {
	

	/**
	 * A HumanView konstruktora.
	 * @param x Az ember x koordinátája.
	 * @param y Az ember y koordinátája.
	 * @param texturePath Az ember textúrájának elérési útvonala.
	 */
	public HumanView(float x, float y, String texturePath) {
		super(x,y, texturePath);
	}
	
	/**
	 * Kirajzolja az embert.
	 */
	public void Draw(GameView jatekmegjelenito) {
		texture.setLayoutX(x);
		texture.setLayoutY(y);
		jatekmegjelenito.getChildren().add(texture);
	}
	
	/**
	 * Ráilleszti a kinézetre a textúrát.
	 * @param tileX A textúra szélessége.
	 * @param tileY A textúra magassága.
	 */
	public void normalizeTexture(float tileX, float tileY) {
		texture.setFitHeight(tileY);
		texture.setFitWidth(tileX);
	}
}
