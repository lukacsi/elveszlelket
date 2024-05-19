package skeleton.elveszlelket.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HumanView extends View {
	
	public HumanView(float x, float y, String texturePath) {
		super(x,y, texturePath);
	}
	
	public void Draw(GameView jatekmegjelenito) {
		texture.setLayoutX(x);
		texture.setLayoutY(y);
		jatekmegjelenito.getChildren().add(texture);
	}
	
	public void normalizeTexture(float tileX, float tileY) {
		texture.setFitHeight(tileY);
		texture.setFitWidth(tileX);
	}
}
