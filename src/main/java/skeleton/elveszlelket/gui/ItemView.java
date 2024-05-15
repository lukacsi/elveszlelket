package skeleton.elveszlelket.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemView extends View {
	
	private ImageView texture;
	
	public ItemView(float x, float y, String texturePath) {
		super(x,y);
		texture = new ImageView(new Image(texturePath));
	}
	
	public void Draw(GameView jatekmegjelenito) {
		texture.setLayoutX(x);
		texture.setLayoutY(y);
		jatekmegjelenito.getChildren().add(texture);
	}

	@Override
	public void normalizeTexture(float tileX, float tileY) {
		texture.setFitHeight(tileY);
		texture.setFitWidth(tileX);
	}
}
